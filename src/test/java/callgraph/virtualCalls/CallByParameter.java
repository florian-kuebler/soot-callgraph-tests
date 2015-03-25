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
package callgraph.virtualCalls;

import callgraph.base.AbstractBase;
import callgraph.base.AlternateBase;
import callgraph.base.Base;
import callgraph.base.ConcreteBase;
import callgraph.base.SimpleBase;
import org.opalj.ai.test.invokedynamic.annotations.InvokedMethod;
import org.opalj.ai.test.invokedynamic.annotations.InvokedMethods;

/**
 * This class was used to create a class file with some well defined attributes. The
 * created class is subsequently used by several tests.
 * 
 * NOTE<br />
 * This class is not meant to be (automatically) recompiled; it just serves documentation
 * purposes.
 * 
 * <!--
 * 
 * 
 * 
 * 
 * INTENTIONALLY LEFT EMPTY (THIS AREA CAN BE EXTENDED/REDUCED TO MAKE SURE THAT THE
 * SPECIFIED LINE NUMBERS ARE STABLE.
 * 
 * 
 * 
 * 
 * -->
 * 
 * @author Marco Jacobasch
 */
public class CallByParameter {

    @InvokedMethods({
            @InvokedMethod(receiverType = "callgraph/base/SimpleBase", name = "interfaceMethod", line = 68),
            @InvokedMethod(receiverType = "callgraph/base/AbstractBase", name = "interfaceMethod", line = 68) })
    void callByInterface(Base object) {
        object.interfaceMethod();
    }

    @InvokedMethod(receiverType = "callgraph/base/AbstractBase", name = "interfaceMethod", line = 73)
    void callByInterface(AbstractBase object) {
        object.interfaceMethod();
    }

    @InvokedMethod(receiverType = "callgraph/base/AbstractBase", name = "interfaceMethod", line = 78)
    void callByInterface(ConcreteBase object) {
        object.interfaceMethod();
    }

    @InvokedMethod(receiverType = "callgraph/base/AbstractBase", name = "interfaceMethod", line = 83)
    void callByInterface(AlternateBase object) {
        object.interfaceMethod();
    }

    @InvokedMethod(receiverType = "callgraph/base/SimpleBase", name = "interfaceMethod", line = 88)
    void callByInterface(SimpleBase object) {
        object.interfaceMethod();
    }

}
