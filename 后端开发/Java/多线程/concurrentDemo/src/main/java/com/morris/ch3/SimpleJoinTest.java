package com.morris.ch3;

public class SimpleJoinTest {
	
	public static void main(String[] args) throws InterruptedException {
		A a  = new A();
		a.start();
		a.join();
		
		System.out.println("main thread is end");
	}
	
	static class A extends Thread {
		@Override
		public void run() {
			try {
				System.out.println("A is runing...");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
