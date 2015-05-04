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
 * This class contains a few simple examples for method references introduced in Java 8.
 *
 * <!--
 * 
 * 
 * INTENTIONALLY LEFT EMPTY (THIS AREA CAN BE EXTENDED/REDUCED TO MAKE SURE THAT THE
 * SPECIFIED LINE NUMBERS ARE STABLE.
 * 
 * 
 * -->
 *
 * @author Arne Lottmann
 */
public class MethodReferences {
    @CallSite(resolution = DYNAMIC, resolvedMethods = { @ResolvedMethod( receiverType = "lambdas/MethodReferences$Value" ) }, name = "isEmpty", line = 52)
	public void filterOutEmptyValues() {
		java.util.List<Value> values = java.util.Arrays.asList(new Value("foo"), new Value(""));
		values.stream().filter(Value::isEmpty);
	}

	@CallSite(resolution = DYNAMIC, resolvedMethods = { @ResolvedMethod( receiverType = "lambdas/MethodReferences$Value" ) }, name = "compare", line = 58, isStatic = true)
	public void compareValues() {
		java.util.Comparator<Value> comparator = Value::compare;
		System.out.println(comparator.compare(new Value("a"), new Value("b")));
	}
	
	public interface ValueCreator {
		Value newValue(String value);
	}
	
	@CallSite(resolution = DYNAMIC, resolvedMethods = { @ResolvedMethod( receiverType = "lambdas/MethodReferences$Value" ) }, name = "<init>", line = 68)
	public Value newValue(String value) {
		ValueCreator v = Value::new;
		return v.newValue(value);
	}

	public static class Value {
		private String value;

		public Value(String value) {
			this.value = value;
		}

		public boolean isEmpty() {
			return value.isEmpty();
		}

		public static int compare(Value a, Value b) {
			return a.value.compareTo(b.value);
		}
	}
}	
