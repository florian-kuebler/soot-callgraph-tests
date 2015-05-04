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
 * Test class for resolving invokedynamic calls to methods set up using groovy's metaclass.
 * 
 * I'm not yet sure what class the receiverType / declaringType should be, either MyClass or whatever its metaclass is.
 * I find using MyClass more reasonable though, since that is the type we're invoking methods on.
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
 *
 */
public class WithMetaClass {
    private class MyClass {
        
    }

    private MyClass myObject;
        
    public WithMetaClass() {
        myObject = new MyClass();
        setupMethods(myObject);
        setupFields(myObject);
    }
    
    private void setupMethods(MyClass obj) {
        obj.metaClass.constructor << { int primitive -> }
        obj.metaClass.constructor << { Object object -> }
        obj.metaClass.constructor << { int...varargs -> }
        obj.metaClass.constructor << { Object...varargs -> }
        
        obj.metaClass.noParameters = { -> }
        obj.metaClass.primitiveParameter = { int primitive -> }
        obj.metaClass.objectParameter = { Object object -> }
        obj.metaClass.primitiveVarargs = { int...varargs -> }
        obj.metaClass.objectVarargs = { Object...varargs -> }
    }
    
    private void setupFields(MyClass obj) {
        obj.metaClass.primitiveField = 1;
        obj.metaClass.objectField = new Object();
    }
    
    @InvokedConstructor(receiverType=MyClass, line = 84)
    public void noArgumentsConstructor() {
        new MyClass();
    }
    
    @InvokedConstructor(receiverType=MyClass, parameterTypes=[int], line = 89)
    public void primitiveArgumentConstructor() {
        new MyClass(1);
    }
    
    @InvokedConstructor(receiverType=MyClass, parameterTypes=[Object], line = 94)
    public void objectArgumentConstructor() {
        new MyClass(new Object());
    }
    
    @InvokedConstructor(receiverType=MyClass, parameterTypes=[int[]], line = 99)
    public void primitiveVarargsConstructor() {
        new MyClass(1,2);
    }
    
    @InvokedConstructor(receiverType=MyClass, parameterTypes=[Object[]], line = 104)
    public void objectVarargsConstructor() {
        new MyClass(new Object(), new Object());
    }
    
    @CallSite(resolvedMethods=MyClass, name={ @ResolvedMethod( receiverType = "noParameters" ) }, parameterTypes=[], 
		line = 110, resolution = TargetResolution.DYNAMIC)
    public void runNoParameters() {
        myObject.noParameters();
    }
    
    @CallSite(resolvedMethods=MyClass, name={ @ResolvedMethod( receiverType = "primitiveParameter" ) }, parameterTypes=[int], 
		line = 116, resolution = TargetResolution.DYNAMIC)
    public void runPrimitiveParameter() {
        myObject.primitiveParameter(1);
    }
    
    @CallSite(resolvedMethods=MyClass, name={ @ResolvedMethod( receiverType = "objectParameter" ) }, parameterTypes=[Object], 
		line = 122, resolution = TargetResolution.DYNAMIC)
    public void runObjectParameter() {
        myObject.objectParameter(new Object());
    }
    
    @CallSite(resolvedMethods=MyClass, name={ @ResolvedMethod( receiverType = "primitiveVarargs" ) }, parameterTypes=[int[]], 
		line = 128, resolution = TargetResolution.DYNAMIC)
    public void runPrimitiveVarargs() {
        myObject.primitiveVarargs(1, 2);
    }
    
    @CallSite(resolvedMethods=MyClass, name={ @ResolvedMethod( receiverType = "objectVarargs" ) }, parameterTypes=[Object[]], 
		line = 134, resolution = TargetResolution.DYNAMIC)
    public void runObjectVarargs() {
        myObject.objectVarargs(new Object(), new Object());
    }
    
    @CallSite(resolvedMethods=MyClass, name={ @ResolvedMethod( receiverType = "primitiveParameter" ) }, parameterTypes=[int], 
		line = 140, resolution = TargetResolution.DYNAMIC)
    public void runPrimitiveParameterBoxed() {
        myObject.primitiveParameter(Integer.valueOf(1));
    }
    
    @CallSite(resolvedMethods=MyClass, name={ @ResolvedMethod( receiverType = "objectParameter" ) }, parameterTypes=[Object], 
		line = 146, resolution = TargetResolution.DYNAMIC)
    public void runObjectParameterUnboxed() {
        myObject.objectParameter(1);
    }
    
    @CallSite(resolvedMethods=MyClass, name={ @ResolvedMethod( receiverType = "primitiveVarargs" ) }, parameterTypes=[int[]], 
		line = 152, resolution = TargetResolution.DYNAMIC)
    public void runPrimitiveVarargsBoxed() {
        myObject.primitiveVarargs(Integer.valueOf(1), Integer.valueOf(2));
    }
    
    @CallSite(resolvedMethods=MyClass, name={ @ResolvedMethod( receiverType = "objectVarargs" ) }, parameterTypes=[Object[]], 
		line = 158, resolution = TargetResolution.DYNAMIC)
    public void runObjectVarargsUnboxed() {
        myObject.objectVarargs(1, 2);
    }
    
    @AccessedField(declaringType=MyClass, name="primitiveField", fieldType=int, line = 162)
    public int getPrimitiveField() { return primitiveField; }
    
    @AccessedField(declaringType=MyClass, fieldType=Object, name="objectField", line = 165)
    public Object getObjectField() { return objectField; }
}
