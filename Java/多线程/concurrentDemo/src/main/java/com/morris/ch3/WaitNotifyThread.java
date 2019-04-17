package com.morris.ch3;

public class WaitNotifyThread {
	
	final static Object object = new Object();
	
	static boolean flag = true;
	
	public static void main(String[] args) throws InterruptedException {
		new WaitThread().start();
		Thread.sleep(1000);
		new NotifyThread().start();
	}
	
	static class WaitThread extends Thread {
		@Override
		public void run() {
			synchronized (object) {
				while(flag) {
					try {
						System.out.println("WaitThread is waiting...");
						object.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("WaitThread is stop.");
			}
		}
	}
	
	static class NotifyThread extends Thread {
		@Override
		public void run() {
			synchronized (object) {
				System.out.println("NotifyThread is notifyAll.");
				object.notifyAll();
				flag = false;
			}
		}
	}
	

}
