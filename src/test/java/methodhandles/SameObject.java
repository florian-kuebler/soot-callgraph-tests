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
package methodhandles;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

import org.opalj.ai.test.invokedynamic.annotations.CallSite;
import org.opalj.ai.test.invokedynamic.annotations.ResolvedMethod;

/**
 * Test cases for method handles.
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
public class SameObject {
	private final Lookup lookup = MethodHandles.lookup();

	public void noArgumentsPublic() {/* empty */
	}

	protected void noArgumentsProtected() {/* empty */
	}

	@SuppressWarnings("all")
	private void noArgumentsPrivate() {/* empty */
	}

	@CallSite(resolvedMethods = { @ResolvedMethod(receiverType = "methodhandles/SameObject") }, name = "noArgumentsPublic", isReflective = true, line = 72)
	@CallSite(resolvedMethods = { @ResolvedMethod(receiverType = "methodhandles/SameObject") }, name = "noArgumentsProtected", isReflective = true, line = 74)
	@CallSite(resolvedMethods = { @ResolvedMethod(receiverType = "methodhandles/SameObject") }, name = "noArgumentsPrivate", isReflective = true, line = 76)
	public void noArgumentsMethodHandles() throws Throwable {
		MethodType voidType = MethodType.methodType(Void.class);
		MethodHandle publicHandle = lookup.findVirtual(SameObject.class, "noArgumentsPublic", voidType);
		publicHandle.invokeExact(this);
		MethodHandle protectedHandle = lookup.findVirtual(SameObject.class, "noArgumentsProtected", voidType);
		protectedHandle.invokeExact(this);
		MethodHandle privateHandle = lookup.findVirtual(SameObject.class, "noArgumentsPrivate", voidType);
		privateHandle.invokeExact(this);
	}

	public void primitive(int i) {/* empty */
	}

	public int primitiveReturn(int i) {
		return i;
	}

	@CallSite(resolvedMethods = { @ResolvedMethod(receiverType = "methodhandles/SameObject") }, name = "primitive", parameterTypes = { int.class }, isReflective = true, line = 91)
	@CallSite(resolvedMethods = { @ResolvedMethod(receiverType = "methodhandles/SameObject") }, name = "primitiveReturn", parameterTypes = { int.class }, returnType = int.class, isReflective = true, line = 94)
	public void primitiveHandles() throws Throwable {
		MethodType voidType = MethodType.methodType(Void.class, int.class);
		MethodHandle voidHandle = lookup.findVirtual(SameObject.class, "primitive", voidType);
		voidHandle.invokeExact(this, 1);
		MethodType returnType = MethodType.methodType(int.class, int.class);
		MethodHandle returnHandle = lookup.findVirtual(SameObject.class, "primitiveReturn", returnType);
		returnHandle.invokeExact(this, 1);
	}

	public void object(Object o) {/* empty */
	}

	public Object objectReturn(Object o) {
		return o;
	}

	@CallSite(resolvedMethods = { @ResolvedMethod(receiverType = "methodhandles/SameObject") }, name = "object", parameterTypes = { Object.class }, isReflective = true, line = 109)
	@CallSite(resolvedMethods = { @ResolvedMethod(receiverType = "methodhandles/SameObject") }, name = "objectReturn", parameterTypes = { Object.class }, returnType = Object.class, isReflective = true, line = 112)
	public void objectHandles() throws Throwable {
		MethodType voidType = MethodType.methodType(Void.class, Object.class);
		MethodHandle voidHandle = lookup.findVirtual(SameObject.class, "object", voidType);
		voidHandle.invokeExact(this, new Object());
		MethodType returnType = MethodType.methodType(Object.class, Object.class);
		MethodHandle returnHandle = lookup.findVirtual(SameObject.class, "objectReturn", returnType);
		returnHandle.invokeExact(this, new Object());
	}

	public void array(int[] array) {/* empty */
	}

	public int[] arrayReturn(int[] array) {
		return array;
	}

	@CallSite(resolvedMethods = { @ResolvedMethod(receiverType = "methodhandles/SameObject") }, name = "array", parameterTypes = { int[].class }, isReflective = true, line = 127)
	@CallSite(resolvedMethods = { @ResolvedMethod(receiverType = "methodhandles/SameObject") }, name = "arrayReturn", parameterTypes = { int[].class }, returnType = int[].class, isReflective = true, line = 130)
	public void arrayHandles() throws Throwable {
		MethodType voidType = MethodType.methodType(Void.class, int[].class);
		MethodHandle voidHandle = lookup.findVirtual(SameObject.class, "array", voidType);
		voidHandle.invokeExact(this, new int[] { 1 });
		MethodType returnType = MethodType.methodType(int[].class, int[].class);
		MethodHandle returnHandle = lookup.findVirtual(SameObject.class, "arrayReturn", returnType);
		returnHandle.invokeExact(this, new int[] { 1 });
	}
}
