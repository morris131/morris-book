package com.morris.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args: -XX:PermSize=2m -XX:MaxPermSize=2m
 * jdk 7运行
 *
 */
public class ConstantPoolOOM {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		int i = 0;
		while (true) {
			list.add(String.valueOf(i++).intern());
		}
	}

}
