package com.morris.base;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListIteratorTest {
	
	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		//backwardIterator(list);
		//forwardIterator(list);
		
		//addItem(list);
		setItem(list);
		//removeItem(list);
		
	}
	
	/**
	 * 向后遍历
	 * @param list
	 */
	public static void backwardIterator(List<Integer> list)  {
		ListIterator<Integer> listIterator = list.listIterator();
		while (listIterator.hasNext()) {
			System.out.println(listIterator.nextIndex() + ":" + listIterator.next());
		}
	}
	
	/**
	 * 向前遍历
	 * @param list
	 */
	public static void forwardIterator(List<Integer> list)  {
		ListIterator<Integer> listIterator = list.listIterator(list.size()); // 将游标移至最后才能往前遍历
		while (listIterator.hasPrevious()) {
			System.out.println(listIterator.previousIndex() + ":" + listIterator.previous());
		}
	}
	
	/**
	 * 删除元素
	 * @param list
	 */
	public static void removeItem(List<Integer> list) {
		ListIterator<Integer> listIterator = list.listIterator();
		while (listIterator.hasNext()) {
			System.out.println(listIterator.next());
			listIterator.remove(); // 删除迭代器最后一次next()的元素
			System.out.println(list);
		}
		System.out.println(list.size());
	}
	
	/**
	 * 添加元素
	 * @param list
	 */
	public static void addItem(List<Integer> list) {
		ListIterator<Integer> listIterator = list.listIterator();
		listIterator.add(100); // 在游标前面插入一个元素 
		System.out.println(list);
	}
	
	/**
	 * 设置元素
	 * @param list
	 */
	public static void setItem(List<Integer> list) {
		ListIterator<Integer> listIterator = list.listIterator();
		while (listIterator.hasNext()) {
			System.out.println(listIterator.next());
			listIterator.set(100); // 设置迭代器最后一次next()的元素
			System.out.println(list);
		}
		System.out.println(list.size());
	}

}
