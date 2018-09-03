package com.morris.ch3;


public class JoinVsSleepTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		A a = new A();
		
		B b = new B(a);
		b.start();
		
		Thread.sleep(500);
		
		a.print();
		
	}
	
	static class A extends Thread {
		@Override
		public void run() {
			System.out.println("A run begin");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("A run end");
		}
		
		synchronized void print() {
			System.out.println("A print");
		}
	}
	
	static class B extends Thread {
		
		private A a;
		
		B(A a) {
			this.a = a;
		}
		
		@Override
		public void run() {
			synchronized (a) {				
				try {
					a.start();
					System.out.println("join start");
					a.join();
					//Thread.sleep(2000);
					System.out.println("join end");
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
}
