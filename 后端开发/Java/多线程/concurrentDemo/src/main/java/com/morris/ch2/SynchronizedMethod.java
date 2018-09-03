package com.morris.ch2;

/**
 * 1) 第一个线程先持有a对象的锁，第二个线程调用a对象的synchronized的方法需等待，也就是同步。 
 * 2) 第一个线程先持有a对象的锁，第三个线程可以以异步的形式调用a对象的非synchronized的方法。 
 *
 */
public class SynchronizedMethod {

	public static void main(String[] args) {
		A a = new A();
		new Thread(() -> a.method1()).start();
		new Thread(() -> a.method2()).start();
		new Thread(() -> a.method3()).start();
	}

}

class A {

	public synchronized void method1() {
		System.out.println("method1 begin...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("method1 end...");
	}

	public synchronized void method2() {
		System.out.println("method2");
	}

	public void method3() {
		System.out.println("method3");
	}

}
