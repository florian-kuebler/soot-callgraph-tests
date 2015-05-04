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
 * Test class for resolving invokedynamic calls to constructors, instance methods and instance field accesses on another object.
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
public class AnotherObject {
    private SameObject o = new SameObject();
    
    @InvokedConstructor(receiverType=SameObject, line = 52)
    public void noArgumentsConstructor() {
        new SameObject();
    }

    @InvokedConstructor(receiverType=SameObject, parameterTypes=[int], line = 57)
    public void primitiveArgumentConstructor() {
        new SameObject(1);
    }
    
    @InvokedConstructor(receiverType=SameObject, parameterTypes=[Object], line = 62)
    public void objectArgumentConstructor() {
        new SameObject(new Object());
    }
    
    @InvokedConstructor(receiverType=SameObject, parameterTypes=[int[]], line = 67)
    public void primitiveVarargsConstructor() {
        new SameObject(1,2);
    }
    
    @InvokedConstructor(receiverType=SameObject, parameterTypes=[Object[]], line = 72)
    public void objectVarargsConstructor() {
        new SameObject(new Object(), new Object());
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "noParameters" ) }, parameterTypes=[], 
        line = 78, resolution = TargetResolution.DYNAMIC)
    public void runNoParameters() {
        o.noParameters();
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "primitiveParameter" ) }, parameterTypes=[int], 
        line = 84, resolution = TargetResolution.DYNAMIC)
    public void runPrimitiveParameter() {
        o.primitiveParameter(1);
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "objectParameter" ) }, parameterTypes=[Object], 
        line = 90, resolution = TargetResolution.DYNAMIC)
    public void runObjectParameter() {
        o.objectParameter(new Object());
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "primitiveVarargs" ) }, parameterTypes=[int[]], 
        line = 96, resolution = TargetResolution.DYNAMIC)
    public void runPrimitiveVarargs() {
        o.primitiveVarargs(1, 2);
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "objectVarargs" ) }, parameterTypes=[Object[]], 
        line = 102, resolution = TargetResolution.DYNAMIC)
    public void runObjectVarargs() {
        o.objectVarargs(new Object(), new Object());
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "primitiveParameter" ) }, parameterTypes=[int], 
        line = 108, resolution = TargetResolution.DYNAMIC)
    public void runPrimitiveParameterBoxed() {
        o.primitiveParameter(Integer.valueOf(1));
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "objectParameter" ) }, parameterTypes=[Object], 
        line = 114, resolution = TargetResolution.DYNAMIC)
    public void runObjectParameterUnboxed() {
        o.objectParameter(1);
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "primitiveVarargs" ) }, parameterTypes=[int[]], 
        line = 120, resolution = TargetResolution.DYNAMIC)
    public void runPrimitiveVarargsBoxed() {
        o.primitiveVarargs(Integer.valueOf(1), Integer.valueOf(2));
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "objectVarargs" ) }, parameterTypes=[Object[]], 
        line = 126, resolution = TargetResolution.DYNAMIC)
    public void runObjectVarargsUnboxed() {
        o.objectVarargs(1, 2);
    }
    
    @AccessedField(declaringType=SameObject, name="primitiveField", fieldType=int, line = 130)
    public int getPrimitiveField() { return o.primitiveField; }
    
    @AccessedField(declaringType=SameObject, fieldType=Object, name="objectField", line = 133)
    public Object getObjectField() { return o.objectField; }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "noParameters" ) }, parameterTypes=[], 
        line = 138, resolution = TargetResolution.DYNAMIC)
    public void runNoParameters(SameObject o) {
        o.noParameters();
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "primitiveParameter" ) }, parameterTypes=[int], 
        line = 144, resolution = TargetResolution.DYNAMIC)
    public void runPrimitiveParameter(SameObject o) {
        o.primitiveParameter(1);
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "objectParameter" ) }, parameterTypes=[Object], 
        line = 148, resolution = TargetResolution.DYNAMIC)
    public void runObjectParameter(SameObject o) {
        o.objectParameter(new Object());
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "primitiveVarargs" ) }, parameterTypes=[int[]], 
        line = 154, resolution = TargetResolution.DYNAMIC)
    public void runPrimitiveVarargs(SameObject o) {
        o.primitiveVarargs(1, 2);
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "objectVarargs" ) }, parameterTypes=[Object[]], 
        line = 162, resolution = TargetResolution.DYNAMIC)
    public void runObjectVarargs(SameObject o) {
        o.objectVarargs(new Object(), new Object());
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "primitiveParameter" ) }, parameterTypes=[int], 
        line = 168, resolution = TargetResolution.DYNAMIC)
    public void runPrimitiveParameterBoxed(SameObject o) {
        o.primitiveParameter(Integer.valueOf(1));
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "objectParameter" ) }, parameterTypes=[Object], 
        line = 174, resolution = TargetResolution.DYNAMIC)
    public void runObjectParameterUnboxed(SameObject o) {
        o.objectParameter(1);
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "primitiveVarargs" ) }, parameterTypes=[int[]], 
        line = 180, resolution = TargetResolution.DYNAMIC)
    public void runPrimitiveVarargsBoxed(SameObject o) {
        o.primitiveVarargs(Integer.valueOf(1), Integer.valueOf(2));
    }
    
    @CallSite(resolvedMethods=SameObject, name={ @ResolvedMethod( receiverType = "objectVarargs" ) }, parameterTypes=[Object[]], 
        line = 186, resolution = TargetResolution.DYNAMIC)
    public void runObjectVarargsUnboxed(SameObject o) {
        o.objectVarargs(1, 2);
    }
    
    @AccessedField(declaringType=SameObject, name="primitiveField", fieldType=int, line = 190)
    public int getPrimitiveField(SameObject o) { return o.primitiveField; }
    
    @AccessedField(declaringType=SameObject, fieldType=Object, name="objectField", line = 193)
    public Object getObjectField(SameObject o) { return o.objectField; }
}
