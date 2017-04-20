package com.morris.jvm;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author lian.chen
 * @createTime 2016年9月6日 下午4:33:28
 */
public class LambaTest {
	
	public static void main(String[] args) {
		
		Integer[] a = {1, 8, 3, 9, 2, 0, 5};
		Arrays.sort(a, (o1, o2) -> o1 - o2);
		
		System.out.println(Arrays.toString(a));
		
		List<Integer> list = Arrays.asList(a);
		
		list.forEach(e->System.out.println(e));
		
	}

}
