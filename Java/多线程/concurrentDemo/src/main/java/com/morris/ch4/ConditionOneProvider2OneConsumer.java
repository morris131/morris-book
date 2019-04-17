package com.morris.ch4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionOneProvider2OneConsumer {
	
	private static Lock lock = new ReentrantLock();
	
	private static Condition condition = lock.newCondition();
	
	private static String value;
	
	public static void main(String[] args) {
		
		new Provider().start();
		new Consumer().start();
		
	}
	
	static class Provider extends Thread {
		@Override
		public void run() {
			
			while(true) {
				lock.lock();
				try {
					if(null == value) {
						value = System.currentTimeMillis() + "";
						System.out.println("provider set :" + value);
						condition.signal();
					} else {
						condition.await();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
			
		}
	}
	
	static class Consumer extends Thread {
		@Override
		public void run() {
			while(true) {
				lock.lock();
				try {
					if(null == value) {
						condition.await();
					} else {
						System.out.println("consumer get value:" + value);
						value = null;
						condition.signal();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}
	}

}
