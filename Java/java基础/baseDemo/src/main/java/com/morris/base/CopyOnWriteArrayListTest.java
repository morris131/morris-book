package com.morris.base;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest {
	public static void main(String[] args) {

		List<Integer> list = new CopyOnWriteArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		Iterator<Integer> iterator=list.iterator();
		while (iterator.hasNext()) {
			
			if(3 == iterator.next()) {
				list.remove(2);
			}
		}
		
		System.out.println(list);
	}

}
