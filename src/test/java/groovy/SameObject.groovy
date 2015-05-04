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
package groovy;

import org.opalj.ai.test.invokedynamic.annotations.*;

/**
 * Test class for resolving invokedynamic calls to constructor calls, instance methods and instance field accesses within the same object.
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
public class SameObject {
    protected int primitiveField = 1;
    protected Object objectField = new Object();
    
    public SameObject() {}
    public SameObject(int primitive) {}
    public SameObject(Object object) {}
    public SameObject(int...varargs) {}
    public SameObject(Object...varargs) {}
    
    @InvokedConstructor(receiverType=SameObject, line = 59)
    public static void noArgumentsConstructor() {
        new SameObject();
    }

    @InvokedConstructor(receiverType=SameObject, parameterTypes=[int], line = 64)
    public static void primitiveArgumentConstructor() {
        new SameObject(1);
    }
    
    @InvokedConstructor(receiverType=SameObject, parameterTypes=[Object], line = 69)
    public static void objectArgumentConstructor() {
        new SameObject(new Object());
    }
    
    @InvokedConstructor(receiverType=SameObject, parameterTypes=[int[]], line = 74)
    public static void primitiveVarargsConstructor() {
        new SameObject(1,2);
    }
    
    @InvokedConstructor(receiverType=SameObject, parameterTypes=[Object[]], line = 79)
    public static void objectVarargsConstructor() {
        new SameObject(new Object(), new Object());
    }
    
    public void noParameters() {}
    
    public void primitiveParameter(int primitive) {}
    
    public void objectParameter(Object object) {}
    
    public void primitiveVarargs(int...varargs) {}
    
    public void objectVarargs(Object...varargs) {}
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "noParameters" ) }, parameterTypes=[], 
        line = 95, resolution = TargetResolution.DYNAMIC)
    public void runNoParameters() {
        noParameters();
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "primitiveParameter" ) }, parameterTypes=[int], 
        line = 101, resolution = TargetResolution.DYNAMIC)
    public void runPrimitiveParameter() {
        primitiveParameter(1);
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "objectParameter" ) }, parameterTypes=[Object], 
        line = 107, resolution = TargetResolution.DYNAMIC)
    public void runObjectParameter() {
        objectParameter(new Object());
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "primitiveVarargs" ) }, parameterTypes=[int[]], 
        line = 113, resolution = TargetResolution.DYNAMIC)
    public void runPrimitiveVarargs() {
        primitiveVarargs(1, 2);
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "objectVarargs" ) }, parameterTypes=[Object[]], 
        line = 119, resolution = TargetResolution.DYNAMIC)
    public void runObjectVarargs() {
        objectVarargs(new Object(), new Object());
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "primitiveParameter" ) }, parameterTypes=[int], 
        line = 125, resolution = TargetResolution.DYNAMIC)
    public void runPrimitiveParameterBoxed() {
        primitiveParameter(Integer.valueOf(1));
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "objectParameter" ) }, parameterTypes=[Object], 
        line = 131, resolution = TargetResolution.DYNAMIC)
    public void runObjectParameterUnboxed() {
        objectParameter(1);
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "primitiveVarargs" ) }, parameterTypes=[int[]], 
        line = 137, resolution = TargetResolution.DYNAMIC)
    public void runPrimitiveVarargsBoxed() {
        primitiveVarargs(Integer.valueOf(1), Integer.valueOf(2));
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "objectVarargs" ) }, parameterTypes=[Object[]], 
        line = 143, resolution = TargetResolution.DYNAMIC)
    public void runObjectVarargsUnboxed() {
        objectVarargs(1, 2);
    }
    
    @AccessedField(declaringType=SameObject, name="primitiveField", fieldType=int, line = 147)
    public int getPrimitiveField() { return primitiveField; }
    
    @AccessedField(declaringType=SameObject, fieldType=Object, name="objectField", line = 150)
    public Object getObjectField() { return objectField; }
}
