package de.tud.cs.soot.callgraph;

import static org.opalj.annotations.callgraph.CallGraphAlgorithmMode.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.opalj.annotations.callgraph.CallGraphAlgorithm;
import org.opalj.annotations.callgraph.CallGraphAlgorithmMode;
import org.opalj.annotations.callgraph.CallSite;
import org.opalj.annotations.callgraph.CallSites;
import org.opalj.annotations.callgraph.ResolvedMethod;
import org.opalj.annotations.callgraph.ResolvingCondition;

import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.jimple.toolkits.callgraph.Edge;
import soot.tagkit.AnnotationTag;
import soot.tagkit.Tag;
import soot.tagkit.VisibilityAnnotationTag;
import soot.util.annotations.AnnotationInstanceCreator;
import de.tud.cs.peaks.sootconfig.AnalysisTarget;
import de.tud.cs.peaks.sootconfig.FluentOptions;
import de.tud.cs.peaks.sootconfig.SootResult;
import de.tud.cs.peaks.sootconfig.SootRun;
import de.tud.cs.soot.callgraph.options.Options;
import de.tud.cs.soot.callgraph.result.DeclaredMethodCalled;
import de.tud.cs.soot.callgraph.result.DeclaredMethodNotCalled;
import de.tud.cs.soot.callgraph.result.NotDeclaredMethodCalled;
import de.tud.cs.soot.callgraph.result.Result;
import de.tud.cs.soot.callgraph.result.ResultCall;
import de.tud.cs.soot.callgraph.result.ResultClass;
import de.tud.cs.soot.callgraph.result.ResultMethod;

public class CorrectCallgraphAnalysis {
	private final static String CALL_SITES_CLASS = "L" + CallSites.class.getName().replace('.', '/') + ";";
	private final static String CALL_SITE_CLASS = "L" + CallSite.class.getName().replace('.', '/') + ";";
	
	private FluentOptions options;
	private AnalysisTarget target;
	private CallGraphAlgorithm cga;
	private CallGraphAlgorithmMode mode = CallGraphAlgorithmMode.Library;
	private IMethodMatcher matcher;
	private AnnotationInstanceCreator aic;

	public CorrectCallgraphAnalysis(CallGraphAlgorithm cga, CallGraphAlgorithmMode mode, AnalysisTarget target,
			IMethodMatcher matcher) {
		this.target = target;
		this.cga = cga;
		this.mode = mode;
		this.aic = new AnnotationInstanceCreator();
		this.matcher = matcher;
		this.options = Options.getFluentOptions(cga, mode);
	}

	public Result perform() {
		SootRun run = new SootRun(options, target);

		System.out.println("Starting Soot with target: " + target);
		System.out.println(options);

		SootResult res = run.perform();
		System.out.println("Soot finished: " + res.totalSootRuntime());
		Scene scene = res.getScene();
		return checkClasses(scene);
	}

	private Result checkClasses(Scene scene) {
		Result result = new Result();
		for (SootClass sc : scene.getApplicationClasses()) {
			Set<ResultMethod> methods = checkClass(scene, sc);
			if (!methods.isEmpty()) {
				ResultClass clazz = new ResultClass(sc);
				clazz.addMethods(methods);
				result.addClass(clazz);
			}
		}

		return result;
	}

	private Set<ResultMethod> checkClass(Scene scene, SootClass sootClass) {
		Set<ResultMethod> results = new HashSet<>();

		for (SootMethod sm : sootClass.getMethods()) {
			Set<ResultCall> calls = checkMethod(scene, sm);

			if (!calls.isEmpty()) {
				ResultMethod method = new ResultMethod(sm);
				method.addCalls(calls);
				results.add(method);
			}
		}

		return results;
	}

	private Set<ResultCall> checkMethod(Scene scene, SootMethod caller) {
		Set<ResultCall> results = new HashSet<>();

		for (Tag t : caller.getTags()) {
			if (t instanceof VisibilityAnnotationTag) {
				VisibilityAnnotationTag vat = (VisibilityAnnotationTag) t;

				// collect all call edges of this method
				Set<Edge> edges = new HashSet<>();
				Iterator<Edge> edgeIterator = scene.getCallGraph().edgesOutOf(caller);
				while (edgeIterator.hasNext()) {
					Edge edge = edgeIterator.next();
					String calleeName = edge.tgt().getName();

					// calls to constructors are not interesting
					if (!calleeName.equals("<clinit>") && !calleeName.equals("<init>")) {
						edges.add(edge);
					}
				}
				for (AnnotationTag at : vat.getAnnotations()) {
					if (at.getType().equals(CALL_SITES_CLASS)) {
						CallSites callSites = (CallSites) aic.create(at);

						for (CallSite callSite : callSites.value()) {
							results.addAll(handleCallSite(callSite, edges));
						}
					} else if (at.getType().equals(CALL_SITE_CLASS)) {
						CallSite callSite = (CallSite) aic.create(at);

						results.addAll(handleCallSite(callSite, edges));
					} else {
						edges.clear();
					}
				}

				for (Edge edge : edges) {
					results.add(new NotDeclaredMethodCalled(edge));
				}
			}
		}
		return results;
	}

	private Set<ResultCall> handleCallSite(CallSite callSite, Set<Edge> edges) {
		Set<ResultCall> results = new HashSet<>();
		Set<Edge> edgesToRemove = new HashSet<>();
		for (ResolvedMethod resolvedMethod : callSite.resolvedMethods()) {
			boolean check = false;
			for (ResolvingCondition cond : resolvedMethod.iff()) {
				CallGraphAlgorithm algo = cond.containedInMax();
				if (cga.hasSmallerOrEqualPrecision(algo)) {
					if (mode == LibraryWithNameResolution || (mode == Library && !(cond.mode() == LibraryWithNameResolution)) || cond.mode() == Application)  {
						check = true;
						break;
					}
				}
			}

			if (check) {
				for (Edge edge : edges) {
					SootMethod callee = edge.tgt();
					if (matcher.match(callee, callSite, resolvedMethod)) {
						results.add(new DeclaredMethodCalled(edge, callSite, resolvedMethod));
						edgesToRemove.add(edge);
						break;
					}
				}
				if (edgesToRemove.isEmpty()) {
					results.add(new DeclaredMethodNotCalled(callSite, resolvedMethod));
				}
			}

			edges.removeAll(edgesToRemove);
			edgesToRemove.clear();
		}
		return results;
	}
}
