package com.morris.ch4;

import java.util.concurrent.locks.ReentrantLock;

public class GetHoldCountTest {
	
	
	public static void main(String[] args) {
		new GetHoldCountThread().start();
	}
	
	static class GetHoldCountThread extends Thread {
		
		private ReentrantLock lock = new ReentrantLock();
		
		@Override
		public void run() {
			method1();
		}
		
		public void method1() {
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName() + " has hold count: " + lock.getHoldCount());
				method2();
			} finally {
				lock.unlock();
			}
		}
		
		public void method2() {
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName() + " has hold count: " + lock.getHoldCount());
			} finally {
				lock.unlock();
			}
		}
		
	}

}
