package com.morris.jvm.oom;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;


/**
 * VM args： -XX:PermSize=2M -XX:MaxPermSize=2M 
 */
public class MethodAreaOOM {

	public static void main(String[] args) {
		URL url = null;

		List<ClassLoader> classLoaderList = new ArrayList<ClassLoader>();

		try {
			url = new File("/tmp").toURI().toURL();
			URL[] urls = {url};

			while (true){
				ClassLoader loader = new URLClassLoader(urls);
				classLoaderList.add(loader);
				loader.loadClass("com.morris.jvm.oom.OOMObject");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
