package de.tud.cs.soot.callgraph;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import soot.SootMethod;
import de.tud.cs.soot.callgraph.result.DeclaredMethodCalled;
import de.tud.cs.soot.callgraph.result.DeclaredMethodNotCalled;
import de.tud.cs.soot.callgraph.result.IResultTraverser;
import de.tud.cs.soot.callgraph.result.NotDeclaredMethodCalled;
import de.tud.cs.soot.callgraph.result.Result;
import de.tud.cs.soot.callgraph.result.ResultCall;
import de.tud.cs.soot.callgraph.result.ResultClass;
import de.tud.cs.soot.callgraph.result.ResultMethod;
import de.tud.cs.soot.callgraph.util.MethodUtils;

public class CSVResultTraverser implements IResultTraverser {

	private static final String LINE_SEPERATOR = System.getProperty("line.separator");
	private static final String COMMA = ";";

	FileWriter fileWriter;

	public CSVResultTraverser(String fileName) throws IOException {
		File file = new File(fileName);
		file.getParentFile().mkdirs();
		file.createNewFile();
		this.fileWriter = new FileWriter(file);
	}

	@Override
	public void traverse(Result result) {
		try {
			fileWriter.append("Class;Caller;Callee;Declared;Called");
			fileWriter.append(LINE_SEPERATOR);
			for (ResultClass clazz : result.getClasses()) {
				for (ResultMethod method : clazz.getMethods()) {
					SootMethod caller = method.getSootMethod();
					for (ResultCall call : method.getCalls()) {
						
						fileWriter.append(clazz.getSootClass().toString());
						fileWriter.append(COMMA);
						fileWriter.append(caller.toString());
						fileWriter.append(COMMA);
						
						if (call instanceof DeclaredMethodCalled) {
							DeclaredMethodCalled call2 = (DeclaredMethodCalled) call;
							fileWriter.append(call2.getCallEdge().tgt().toString());
							fileWriter.append(COMMA);
							fileWriter.append("YES");
							fileWriter.append(COMMA);
							fileWriter.append("YES");
						} else if (call instanceof DeclaredMethodNotCalled) {
							DeclaredMethodNotCalled call2 = (DeclaredMethodNotCalled) call;
							fileWriter.append(MethodUtils.toSootMethodStyle(call2.getCallSite(), call2.getResolvedMethod()));
							fileWriter.append(COMMA);
							fileWriter.append("YES");
							fileWriter.append(COMMA);
							fileWriter.append("NO");
							
						} else if (call instanceof NotDeclaredMethodCalled) {
							fileWriter.append(((NotDeclaredMethodCalled) call).getCallEdge().tgt().toString());
							fileWriter.append(COMMA);
							fileWriter.append("NO");
							fileWriter.append(COMMA);
							fileWriter.append("YES");
						} else
							throw new RuntimeException("Unexpected ResultCall: " + call);
						fileWriter.append(LINE_SEPERATOR);
					}
				}
			}
			fileWriter.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
