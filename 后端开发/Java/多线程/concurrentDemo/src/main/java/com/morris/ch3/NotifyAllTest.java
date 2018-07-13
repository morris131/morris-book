package com.morris.ch3;

public class NotifyAllTest {
	
	static Object object = new Object();

	public static void main(String[] args) throws InterruptedException {
		new WaitThread("wait1").start();
		new WaitThread("wait2").start();
		WaitThread.sleep(1000);
		new NotifyThread().start();
	}

	static class WaitThread extends Thread {

		WaitThread(String name) {
			super(name);
		}

		@Override
		public void run() {
			synchronized (object) {
				try {
					System.out.println(Thread.currentThread().getName() + " is waiting");
					object.wait();
					System.out.println(Thread.currentThread().getName() + " is stop");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static class NotifyThread extends Thread {
		@Override
		public void run() {
			System.out.println("notity one thread");
			synchronized (object) {
				object.notifyAll();
			}
		}
	}

}
