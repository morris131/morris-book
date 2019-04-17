package com.morris.ch3;

import java.util.Random;

public class SimpleThreadLocalTest {
	
	static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
	
	public static void main(String[] args) {
		new A().start();
		new A().start();
		new A().start();
	}
	
	static class A extends Thread {
		@Override
		public void run() {
			for(int i = 0; i < 10; i++) {
				long l = new Random().nextLong();
				System.out.println(Thread.currentThread().getName() +" set: " + l);
				threadLocal.set(l);
				System.out.println(Thread.currentThread().getName() +" get: " + threadLocal.get());
			}
		}
	}	
}
