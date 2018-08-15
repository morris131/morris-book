package com.morris.ch4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OneConditionWaitNotifyTest {
	
	private static Lock lock = new ReentrantLock();
	
	private static Condition condition = lock.newCondition();
	
	public static void main(String[] args) throws InterruptedException {
		A a = new A();
		a.start();
		Thread.sleep(1000);
		a.signal();
	}
	
	static class A extends Thread {
		@Override
		public void run() {
			await();
		}
		
		public void await() {
			lock.lock();
			try {
				try {
					System.out.println("thread A is waiting...");
					condition.await();
					System.out.println("thread A is stoped");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} finally {
				lock.unlock();
			}
		}
		
		public void signal() {
			lock.lock();
			try {
				System.out.println("notify thread a...");
				condition.signal();
			} finally {
				lock.unlock();
			}
		}
		
	}

}
