package com.morris.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * VM args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * -Xms20m：设置Java堆的最小值为20m
 * -Xmx20m:设置Java堆的最大值为20m,不可扩展
 * -XX:+HeapDumpOnOutOfMemoryError：设置内存溢出时Dump内存快照
 * @author Morris
 *
 */
public class HeapOOM {
	
	public static void main(String[] args) {
		List<HeapOOM> list = new ArrayList<HeapOOM>();
		
		while(true) {
			list.add(new HeapOOM());
		}
	}

}
