package com.morris.ch3;

public class MultiProviderAndMultiConsumerTest {
	
	static volatile String value = null;

	static Object lock = new Object();

	public static void main(String[] args) {
		new Provider("provider 1").start();
		new Provider("provider 2").start();
		new Consumer("consumer 1").start();
		new Consumer("consumer 2").start();
	}

	static class Provider extends Thread {
		
		Provider(String name) {
			super(name);
		}
		
		@Override
		public void run() {
			while (true) {
				synchronized (lock) {
					if (null == value) {
						value = System.nanoTime() + "";
						System.out.println(Thread.currentThread().getName() + " set value:" + value);
						//lock.notify();
						lock.notifyAll();
					} else {
						try {
							System.out.println(Thread.currentThread().getName() + " is waiting");
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
		
		Consumer(String name) {
			super(name);
		}
		
		@Override
		public void run() {
			while (true) {
				synchronized (lock) {
					if (null == value) {
						try {
							System.out.println(Thread.currentThread().getName() + " is waiting");
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {
						System.out.println(Thread.currentThread().getName() + " get value:" + value);
						value = null;
						//lock.notify();
						lock.notifyAll();
					}
				}
			}
		}
	}

}