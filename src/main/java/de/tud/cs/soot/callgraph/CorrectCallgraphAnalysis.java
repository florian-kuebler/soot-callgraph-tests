package de.tud.cs.soot.callgraph;

import java.util.Arrays;
import java.util.Iterator;

import org.opalj.ai.test.invokedynamic.annotations.CallGraphAlgorithm;
import org.opalj.ai.test.invokedynamic.annotations.InvokedMethod;
import org.opalj.ai.test.invokedynamic.annotations.InvokedMethods;

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
		SootResult res = run.perform();

		Scene scene = res.getScene();
		return checkClasses(scene);
	}

	private Result checkClasses(Scene scene) {
		Result result = new Result();

		for (SootClass sc : scene.getApplicationClasses()) {
			
			ResultClass clazz = result.addClass(sc);
			for (SootMethod sm : sc.getMethods()) {
				ResultMethod method = clazz.addMethod(sm);
				checkMethod(scene, method);
			}
		}

		return result;
	}

	private void checkMethod(Scene scene, ResultMethod method) {
		SootMethod sm = method.getSootMethod();
		for (Tag t : sm.getTags()) {
			if (t instanceof VisibilityAnnotationTag) {
				VisibilityAnnotationTag vat = (VisibilityAnnotationTag) t;

				for (AnnotationTag at : vat.getAnnotations()) {
					switch (at.getType()) {
					case "Lorg/opalj/ai/test/invokedynamic/annotations/InvokedMethod;":
						checkCall(scene, method, (InvokedMethod) aic.create(at));
						break;
					case "Lorg/opalj/ai/test/invokedynamic/annotations/InvokedMethods;":
						InvokedMethods invokedMethods = (InvokedMethods) aic.create(at);
						for (InvokedMethod invokedMethod : invokedMethods.value()) {
							checkCall(scene, method, invokedMethod);
						}
						break;
					default:
						break;
					}
				}
			}
		}
	}

	private void checkCall(Scene scene, ResultMethod method, InvokedMethod invokedMethod) {
		if (!Arrays.asList(invokedMethod.isContainedIn()).contains(cga) || invokedMethod.isReflective()) {
			return;
		}
		Iterator<Edge> edges = scene.getCallGraph().edgesOutOf(method.getSootMethod());
		while (edges.hasNext()) {
			Edge edge = edges.next();
			SootMethod callee = edge.tgt();
			if (matcher.match(callee, invokedMethod)) {
				// pass.accept(method, invokedMethod);
				method.addCall(new DeclaredMethodCalled(callee, invokedMethod));
				return;
			}
		}
		method.addCall(new DeclaredMethodNotCalled(invokedMethod));
		// miss.accept(method, invokedMethod);
	}
}
