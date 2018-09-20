package com.morris.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayListTest {
	
	public static void main(String[] args) {
		
		List<Integer> list  = new ArrayList<>();
		
		for(int i = 0; i < 1000000; i++) {
			list.add(i);
		}
		
		testFor(list); // 1ms
		testIterator(list); // 7ms
		testForEach(list); // 8ms
		testListIterator(list); // 7ms
	}
	
	public static void testFor(List<Integer> list) {
		long begin = System.currentTimeMillis();
		for(int i = 0; i < list.size(); i++) {
		}
		long end = System.currentTimeMillis();
		System.out.println("testFor cost " + (end - begin));
	}
	
	public static void testIterator(List<Integer> list) {
		long begin = System.currentTimeMillis();
		Iterator<Integer> iterator=list.iterator();
		while (iterator.hasNext()) {
			iterator.next();
		}
		long end = System.currentTimeMillis();
		System.out.println("testIterator cost " + (end - begin));
	}
	
	public static void testForEach(List<Integer> list) {
		long begin = System.currentTimeMillis();
		for (Integer integer : list) {
		}
		long end = System.currentTimeMillis();
		System.out.println("testForEach cost " + (end - begin));
	}
	
	public static void testListIterator(List<Integer> list) {
		long begin = System.currentTimeMillis();
		ListIterator<Integer> listIterator = list.listIterator();
		while (listIterator.hasNext()) {
			listIterator.next();
		}
		long end = System.currentTimeMillis();
		System.out.println("testListIterator cost " + (end - begin));
	}

}
