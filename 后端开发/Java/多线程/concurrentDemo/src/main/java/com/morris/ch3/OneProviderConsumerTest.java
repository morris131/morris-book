package com.morris.ch3;

public class OneProviderConsumerTest {

	static volatile String value = null;

	static Object lock = new Object();

	public static void main(String[] args) {
		new Provider().start();
		new Consumer().start();
	}

	static class Provider extends Thread {
		@Override
		public void run() {
			while (true) {
				synchronized (lock) {
					if (null == value) {
						value = System.nanoTime() + "";
						System.out.println("provider set value:" + value);
						lock.notify();
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

	static class Consumer extends Thread {
		@Override
		public void run() {
			while (true) {
				synchronized (lock) {
					if (null == value) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						System.out.println("consumer get value:" + value);
						value = null;
						lock.notify();
					}
				}
			}
		}
	}

}
