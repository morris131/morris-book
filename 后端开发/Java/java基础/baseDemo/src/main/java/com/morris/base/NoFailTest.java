package com.morris.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NoFailTest {
	
	public static void main(String[] args) {
		
		List<Integer> list  = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		Iterator<Integer> iterator = list.iterator();
		while(iterator.hasNext()) {
			if(3 == iterator.next()) {
				iterator.remove();
			}
		}
		
		System.out.println(list);
	}

}
