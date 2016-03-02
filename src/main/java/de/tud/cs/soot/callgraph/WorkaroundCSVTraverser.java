package de.tud.cs.soot.callgraph;

import de.tud.cs.soot.callgraph.result.*;
import org.opalj.annotations.callgraph.CallGraphAlgorithm;
import org.opalj.annotations.callgraph.CallSite;
import soot.SootMethod;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WorkaroundCSVTraverser {
    private Map<CallSite, CSVEntry> results;

    private FileWriter fileWriter;

    public WorkaroundCSVTraverser(String fileName) throws IOException {
        File file = new File(fileName);
        file.getParentFile().mkdirs();
        file.createNewFile();
        this.fileWriter = new FileWriter(file);
        this.results = new HashMap<>();
    }

    public void traverse(Result result, CallGraphAlgorithm cga) {
        for (ResultClass clazz : result.getClasses()) {
            for (ResultMethod method : clazz.getMethods()) {
                SootMethod caller = method.getSootMethod();
                for (ResultCall call : method.getCalls()) {
                    if (call instanceof DeclaredMethodCalled) {
                        DeclaredMethodCalled call2 = (DeclaredMethodCalled) call;

                        CallSite callSite = call2.getCallSite();
                        CSVEntry csvEntry = results.get(callSite);

                        if (csvEntry == null) {
                            csvEntry = new CSVEntry(fileWriter, callSite);
                            results.put(callSite, csvEntry);
                        }

                        csvEntry.addCall(call2, cga);
                    } else if (call instanceof DeclaredMethodNotCalled) {
                    } else if (call instanceof NotDeclaredMethodCalled) {
                    } else
                        throw new RuntimeException("Unexpected ResultCall: " + call);
                }
            }
        }
    }

    public void write() throws IOException {

        fileWriter.append(String.join(CSVEntry.SEPARATOR,
                "receiverTypes_CHA",
                "receiverTypes_RTA",
                "receiverTypes_VTA",
                "receiverTypes_3_CFA",
                "resolution",
                "methodName",
                "returnType",
                "parameterTypes",
                "lineNumber",
                "isStatic",
                "isReflective",
                "receiverTypes"));

        for (CSVEntry csvEntry : results.values()) {
            csvEntry.writeLine();
        }

        fileWriter.flush();
    }
}
