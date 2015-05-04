/* BSD 2-Clause License:
 * Copyright (c) 2009 - 2014
 * Software Technology Group
 * Department of Computer Science
 * Technische Universität Darmstadt
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  - Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package lambdas;

import static org.opalj.ai.test.invokedynamic.annotations.TargetResolution.DYNAMIC;

import org.opalj.ai.test.invokedynamic.annotations.CallSite;
import org.opalj.ai.test.invokedynamic.annotations.ResolvedMethod;

/**
 * A few simple closures to test resolution of Java8 generated invokedynamic
 * instructions.
 *
 * DO NOT RECOMPILE SINCE LAMBDA METHODS ARE COMPILER GENERATED, SO THE GIVEN
 * NAMES MIGHT CHANGE!
 *
 * <!--
 * 
 * 
 * INTENTIONALLY LEFT EMPTY (THIS AREA CAN BE EXTENDED/REDUCED TO MAKE SURE THAT
 * THE SPECIFIED LINE NUMBERS ARE STABLE.
 * 
 * 
 * -->
 *
 * @author Arne Lottmann
 */
public class Lambdas {
	@CallSite(resolution = DYNAMIC, resolvedMethods = { @ResolvedMethod(receiverType = "lambdas/Lambdas") }, name = "lambda$plainLambda$0", isStatic = true, line = 54)
	public void plainLambda() {
		Runnable plainLambda = () -> System.out.println("Hello world!");
		plainLambda.run();
	}

	@CallSite(resolution = DYNAMIC, resolvedMethods = { @ResolvedMethod(receiverType = "lambdas/Lambdas") }, name = "lambda$localClosure$1", parameterTypes = { int.class }, isStatic = true, line = 61)
	public void localClosure() {
		int x = 0;
		Runnable localClosure = () -> System.out.println(x);
		localClosure.run();
	}

	private int x;

	@CallSite(resolution = DYNAMIC, resolvedMethods = { @ResolvedMethod(receiverType = "lambdas/Lambdas") }, name = "lambda$instanceClosure$2", line = 69)
	public void instanceClosure() {
		Runnable instanceClosure = () -> System.out.println(x);
		instanceClosure.run();
	}

	@CallSite(resolution = DYNAMIC, resolvedMethods = { @ResolvedMethod(receiverType = "lambdas/Lambdas") }, name = "lambda$localAndInstanceClosure$3", line = 76)
	public void localAndInstanceClosure() {
		int y = 0;
		Runnable localAndInstanceClosure = () -> System.out.println(x + y);
		localAndInstanceClosure.run();
	}
}
