package de.tud.cs.soot.callgraph.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import de.tud.cs.peaks.sootconfig.AnalysisTarget;

public class TargetClassLoader {

	AnalysisTarget target;

	public TargetClassLoader(AnalysisTarget target) {
		this.target = target;
	}

	public Class<?> loadClass(String name) {

		try {
			File file = new File(target.getProcessPath());

			URL[] urls = null;
			URL url = file.toURI().toURL();
			urls = new URL[] { url };
			
			@SuppressWarnings("resource")
			ClassLoader cl = new URLClassLoader(urls);

			return cl.loadClass(name);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

}
