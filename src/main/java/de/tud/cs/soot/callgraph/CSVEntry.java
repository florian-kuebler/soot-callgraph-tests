package de.tud.cs.soot.callgraph;

import de.tud.cs.soot.callgraph.result.DeclaredMethodCalled;
import org.opalj.annotations.callgraph.CallGraphAlgorithm;
import org.opalj.annotations.callgraph.CallSite;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CSVEntry {

    private FileWriter fileWriter;

    private CallSite callSite;

    private List<DeclaredMethodCalled> resolvedMethodsCHA;

    private List<DeclaredMethodCalled> resolvedMethodsRTA;

    private List<DeclaredMethodCalled> resolvedMethodsVTA;

    public static final String SEPARATOR = "\t";

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");


    public CSVEntry(FileWriter fileWriter, CallSite callSite) {
        this.fileWriter = fileWriter;
        this.callSite = callSite;

        resolvedMethodsCHA = new ArrayList<>();
        resolvedMethodsRTA = new ArrayList<>();
        resolvedMethodsVTA = new ArrayList<>();
    }

    public void addCall(DeclaredMethodCalled call, CallGraphAlgorithm cga) {
        switch (cga) {
            case CHA:
                resolvedMethodsCHA.add(call);
                break;
            case RTA:
                resolvedMethodsRTA.add(call);
                break;
            case BasicVTA:
                resolvedMethodsVTA.add(call);
                break;
            default:
                throw new RuntimeException("Unexpected CallGraphAlgorithm");
        }
    }


    public void writeLine() throws IOException {
        writeItem(String.join(", ", resolvedMethodsCHA.stream().map(x -> x.getResolvedMethod().receiverType()).collect(Collectors.toList())));
        writeItem(String.join(", ", resolvedMethodsRTA.stream().map(x -> x.getResolvedMethod().receiverType()).collect(Collectors.toList())));
        writeItem(String.join(", ", resolvedMethodsVTA.stream().map(x -> x.getResolvedMethod().receiverType()).collect(Collectors.toList())));
        writeItem("");
        writeItem(callSite.resolution().name());
        writeItem(callSite.name());
        writeItem(callSite.returnType().getName().replace('.', '/'));
        writeItem(String.join(", ", Arrays.stream(callSite.parameterTypes()).map(x -> x.toString()).collect(Collectors.toList())));
        writeItem(callSite.line());
        writeItem(callSite.isStatic());
        writeItem(callSite.isReflective());
        fileWriter.append(String.join(", ", Arrays.stream(callSite.resolvedMethods()).map(x -> x.receiverType()).collect(Collectors.toList())));
        fileWriter.append(LINE_SEPARATOR);

    }

    private void writeItem(String item) throws IOException {
        fileWriter.append(item);
        fileWriter.append(SEPARATOR);
    }

    private void writeItem(int item) throws IOException {
        fileWriter.append(Integer.valueOf(item).toString());
        fileWriter.append(SEPARATOR);
    }

    private void writeItem(boolean item) throws IOException {
        fileWriter.append(Boolean.toString(item));
        fileWriter.append(SEPARATOR);
    }
}
