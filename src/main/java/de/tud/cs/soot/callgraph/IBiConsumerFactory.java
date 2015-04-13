package de.tud.cs.soot.callgraph;

import java.util.function.BiConsumer;

import org.opalj.ai.test.invokedynamic.annotations.InvokedMethod;

import soot.SootMethod;

public interface IBiConsumerFactory {

	BiConsumer<SootMethod, InvokedMethod> create();
}
