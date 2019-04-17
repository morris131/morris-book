package com.morris.ch3;

public class InterruptJoinTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		B b = new B();
		b.start();
		
		b.interrupt();
		
	}
	
	static class A extends Thread {
		@Override
		public void run() {
			System.out.println("A is runing...");
			while (true) {			
			}
		}
	}
	
	static class B extends Thread {
		
		@Override
		public void run() {
			try {
				A a = new A();
				a.start();
				a.join();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	static class C extends Thread {
		
		private B b;
		
		C(B b) {
			this.b = b;
		}
		
		@Override
		public void run() {
			b.interrupt();
		}
	}
}
