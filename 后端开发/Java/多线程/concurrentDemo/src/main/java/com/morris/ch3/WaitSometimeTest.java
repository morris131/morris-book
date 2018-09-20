package com.morris.ch3;

public class WaitSometimeTest {
	
static Object object = new Object();
	
	public static void main(String[] args) throws InterruptedException {
		new WaitThread().start();
	}
	
	static class WaitThread extends Thread {
		
		@Override
		public void run() {
			synchronized (object) {
				try {
					System.out.println(Thread.currentThread().getName() + " is waiting");
					object.wait(1000);
					System.out.println(Thread.currentThread().getName() + " is stop");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
