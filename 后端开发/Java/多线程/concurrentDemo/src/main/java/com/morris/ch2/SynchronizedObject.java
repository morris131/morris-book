package com.morris.ch2;

public class SynchronizedObject {

	public static void main(String[] args) {
		D d = new D();
		C c = new C(d);
		new Thread(() -> c.method1()).start();
		new Thread(() -> c.method2()).start();
		new Thread(() -> d.method1()).start();
		new Thread(() -> d.method2()).start();
		new Thread(() -> d.method3()).start();
	}

}

class C {

	private D d;

	C(D d) {
		this.d = d;
	}

	public void method1() {
		synchronized (d) {
			System.out.println("c method1 begin...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("c method1 end...");
		}
	}

	public void method2() {
		synchronized (d) {
			System.out.println("c method2 begin...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("c method2 end...");
		}
	}

}

class D {

	public synchronized void method1() {
		System.out.println("d method1 begin...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("d method1 end...");
	}

	public void method2() {
		synchronized (this) {
			System.out.println("d method2 begin...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("d method2 end...");
		}
	}

	public void method3() {
		System.out.println("d method3 ...");
	}

}
