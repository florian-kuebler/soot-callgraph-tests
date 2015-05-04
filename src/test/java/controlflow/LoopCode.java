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
package controlflow;

public class LoopCode {

    int simpleLoop(int a) {
        int res = 0;
        int i = 0;

        while (i < a) {
            res++;
        }

        return res;
    }

    @SuppressWarnings("unused")
    int nestedLoop(int a, int b) {
        int res = 0;

        for (int i = 1; i < a; i++) {
            for (int j = b; b > 0; b--) {
                res += i * j;
            }
        }

        return 0;
    }

    @SuppressWarnings("unused")
    void endlessLoop(int a, int b, int c) {
        int d = a * b;
        int i = 0;

        while (true) {
            i++;
        }
    }

    int loopWithBranch(int a, int b) {
        int a1 = a;
        int b1 = b;

        if (a1 == 0)
            return b;

        while (b1 != 0) {
            if (a1 > b1)
                a1 = a1 - b1;
            else
                b1 = b1 - a1;
        }

        return a1;
    }
}
