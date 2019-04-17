package com.morris.base;

import java.util.ArrayList;
import java.util.List;

public class NoFailTest2 {
	
	public static void main(String[] args) {
		
		List<Integer> list  = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		for(int i = 0; i < list.size(); i++) {
			if(3 == list.get(i)) {
				list.remove(i);
			}
		}
		
		System.out.println(list);
	}

}
