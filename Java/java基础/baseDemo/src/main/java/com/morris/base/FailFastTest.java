package com.morris.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class FailFastTest {
	
	public static void main(String[] args) {
		List<Integer> list  = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		System.out.println("以下三种遍历会抛出异常java.util.ConcurrentModificationException");
		
		for (Integer integer : list) {
			System.out.println(integer);
			list.remove(1);
		}
		
		Iterator<Integer> iterator=list.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
			list.remove(2);
		}
		
		ListIterator<Integer> listIterator = list.listIterator();
		while (listIterator.hasNext()) {
			System.out.println(listIterator.next());
			list.remove(1);
		}
		
	}

}
