package de.tud.cs.soot.callgraph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithm;
import org.opalj.ai.test.invokedynamic.annotations.CallSite;
import org.opalj.ai.test.invokedynamic.annotations.CallSites;
import org.opalj.ai.test.invokedynamic.annotations.ResolvedMethod;

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
import de.tud.cs.soot.callgraph.result.ResultClass;
import de.tud.cs.soot.callgraph.result.ResultMethod;

public class CorrectCallgraphAnalysis {
	private FluentOptions options;
	private AnalysisTarget target;
	private CallGraphAlgorithm cga;
	private IMethodMatcher matcher;
	private AnnotationInstanceCreator aic;

	public CorrectCallgraphAnalysis(CallGraphAlgorithm cga, AnalysisTarget target, IMethodMatcher matcher) {
		this.target = target;
		this.cga = cga;
		this.aic = new AnnotationInstanceCreator();
		this.matcher = matcher;
		switch (cga) {
		case CHA:
			this.options = Options.getCHAFluentOptions();
			break;
		case BasicVTA:
			this.options = Options.getVTAFluentOptions();
			break;

		default:
			throw new RuntimeException("CallGraphAlgorithm: " + cga.name() + " is not yet implemented");
		}
	}

	public Result perform() {
		SootRun run = new SootRun(options, target);
		
		System.out.println("Starting Soot with target: " + target);
		SootResult res = run.perform();
		System.out.println("Soot finished: " + res.totalSootRuntime());
		Scene scene = res.getScene();
		return checkClasses(scene);
	}

	private Result checkClasses(Scene scene) {
		Result result = new Result();

		for (SootClass sc : scene.getApplicationClasses()) {
			ResultClass clazz = new ResultClass(sc);
			for (SootMethod sm : sc.getMethods()) {
				ResultMethod method = new ResultMethod(sm);
				checkMethod(scene, method);
				if(!method.getCalls().isEmpty()) {
					clazz.addMethod(method);
				}
			}
			if(!clazz.getMethods().isEmpty()){
				result.addClass(clazz);
			}
		}

		return result;
	}

	private void checkMethod(Scene scene, ResultMethod method) {
		SootMethod sm = method.getSootMethod();
		
		for (Tag t : sm.getTags()) {
			if (t instanceof VisibilityAnnotationTag) {
				VisibilityAnnotationTag vat = (VisibilityAnnotationTag) t;
				Set<SootMethod> methods = new HashSet<>();
				
				Iterator<Edge> edges = scene.getCallGraph().edgesOutOf(method.getSootMethod());
				while (edges.hasNext()) {
					Edge edge = edges.next();
					methods.add(edge.tgt());
				}
	
				for (AnnotationTag at : vat.getAnnotations()) {
					switch (at.getType()) {
					case "Lorg/opalj/ai/test/invokedynamic/annotations/CallSites;":
						System.out.println(at);
						CallSites callSites = (CallSites) aic.create(at);
						
						Set<SootMethod> methodsToRemove = new HashSet<>();
						
						for (CallSite callSite : callSites.value()) {
							resolvedMethodLoop:
							for (ResolvedMethod resolvedMethod : callSite.resolvedMethods()){
								methods.removeAll(methodsToRemove);
								for (SootMethod sootMethod : methods) {
									boolean check = false;
									for (CallGraphAlgorithm algo : resolvedMethod.containedInMax()){
										if (cga.hasSmallerOrEqualPrecision(algo)){
											check = true;
											break;
										}
									}
									if (check && matcher.match(sootMethod, callSite, resolvedMethod)) {
										method.addCall(new DeclaredMethodCalled(sootMethod, callSite, resolvedMethod));
										methodsToRemove.add(sootMethod);
										continue resolvedMethodLoop;
									}
								}
								method.addCall(new DeclaredMethodNotCalled(callSite, resolvedMethod));
							}
							methodsToRemove.clear();
						}
						
						for (SootMethod sootMethod : methods){
							method.addCall(new NotDeclaredMethodCalled(sootMethod));
						}

						break;
					default:
						System.out.println("Unknown Annotation: " + at.getType());
						break;
					}
				}
			}
		}
	}
}
