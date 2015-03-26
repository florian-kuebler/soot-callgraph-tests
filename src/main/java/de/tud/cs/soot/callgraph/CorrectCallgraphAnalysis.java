package de.tud.cs.soot.callgraph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.BiConsumer;

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
import de.tud.cs.peaks.sootconfig.AnalysisTarget;
import de.tud.cs.peaks.sootconfig.FluentOptions;
import de.tud.cs.peaks.sootconfig.SootResult;
import de.tud.cs.peaks.sootconfig.SootRun;
import de.tud.cs.soot.callgraph.options.Options;
import de.tud.cs.soot.callgraph.util.InvokedMethodCreator;
import de.tud.cs.soot.callgraph.util.InvokedMethodsCreator;
import de.tud.cs.soot.callgraph.util.TargetClassLoader;

public class CorrectCallgraphAnalysis {
	private InvokedMethodCreator imc;
	private InvokedMethodsCreator imsc;
	private FluentOptions options;
	private AnalysisTarget target;
	private CallGraphAlgorithm cga;
	private BiConsumer<SootMethod, InvokedMethod> pass;
	private BiConsumer<SootMethod, InvokedMethod> miss;
	private IMethodMatcher matcher;
	

	public CorrectCallgraphAnalysis(CallGraphAlgorithm cga, AnalysisTarget target, IMethodMatcher matcher,
			BiConsumer<SootMethod, InvokedMethod> pass, BiConsumer<SootMethod, InvokedMethod> miss) {
		this.target = target;
		this.cga = cga;
		this.imc = new InvokedMethodCreator(new TargetClassLoader(target));
		this.imsc = new InvokedMethodsCreator(imc);
		this.matcher = matcher;
		this.pass = pass;
		this.miss = miss;
		switch (cga) {
		case CHA:
			this.options = Options.getCHAFluentOptions();
			break;
		case BasicVTA:
			this.options = Options.getRTAFluentOptions();
			break;

		default:
			throw new RuntimeException("CallGraphAlgorithm: " + cga.name() + " is not yet implemented");
		}
	}

	public void perform() {
		SootRun run = new SootRun(options, target);
		SootResult res = run.perform();

		Scene scene = res.getScene();
		checkClasses(scene);
	}

	private void checkClasses(Scene scene) {
		for (SootClass sc : scene.getApplicationClasses()) {
			for (SootMethod sm : sc.getMethods()) {
				checkMethod(scene, sm);
			}
		}
	}

	private void checkMethod(Scene scene, SootMethod sm) {
		for (Tag t : sm.getTags()) {
			if (t instanceof VisibilityAnnotationTag) {
				VisibilityAnnotationTag vat = (VisibilityAnnotationTag) t;

				for (AnnotationTag at : vat.getAnnotations()) {

					switch (at.getType()) {
					case "Lorg/opalj/ai/test/invokedynamic/annotations/InvokedMethod;":
						checkCall(scene, sm, imc.create(at));
						break;
					case "Lorg/opalj/ai/test/invokedynamic/annotations/InvokedMethods;":
						InvokedMethods invokedMethods = imsc.create(at);
						for (InvokedMethod invokedMethod : invokedMethods.value()) {
							checkCall(scene, sm, invokedMethod);
						}
						break;
					default:
						break;
					}
				}
			}
		}
	}

	private void checkCall(Scene scene, SootMethod sm, InvokedMethod invokedMethod) {
		if (!Arrays.asList(invokedMethod.isContainedIn()).contains(cga) || invokedMethod.isReflective()) {
			return;
		}
		Iterator<Edge> edges = scene.getCallGraph().edgesOutOf(sm);
		while (edges.hasNext()) {
			Edge edge = edges.next();
			if (matcher.match(edge.tgt(), invokedMethod)) {
				pass.accept(sm, invokedMethod);
				return;
			}
		}
		miss.accept(sm, invokedMethod);
	}
}
