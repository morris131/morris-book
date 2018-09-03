package com.morris.ch3;

public class WaitNofityTest {

	private static Object lock = new Object();

	private static volatile boolean isA = true;

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new A("A" + i).start();
			new B("B" + i).start();
		}
	}

	static class A extends Thread {

		A(String name) {
			super(name);
		}

		@Override
		public void run() {
			synchronized (lock) {
				while (true) {
					if (isA) {
						System.out.println(Thread.currentThread().getName() + ": A");
						isA = false;
						lock.notifyAll();
						break;
					} else {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	static class B extends Thread {

		B(String name) {
			super(name);
		}

		@Override
		public void run() {
			synchronized (lock) {
				while (true) {
					if (isA) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						System.out.println(Thread.currentThread().getName() + ": B");
						isA = true;
						lock.notifyAll();
						break;
					}
				}
			}
		}
	}

}
