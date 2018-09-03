package com.morris.ch4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionMoreProvider2MoreConsumer {
	
	private static Lock lock = new ReentrantLock();
	
	private static String value;
	
	private static Condition condition = lock.newCondition();
	
	public static void main(String[] args) {
		for(int i = 0; i < 3; i++) {
			new Provider().start();
			new Consumer().start();
		}
	}
	
	static class Provider extends Thread {
		@Override
		public void run() {
			lock.lock();
			try {
				while(true) {
					if(null == value) {
						value = Thread.currentThread().getName() + " " + System.currentTimeMillis();
						System.out.println(Thread.currentThread().getName() + " set value:" + value);
						condition.signalAll();
					} else {
						condition.await();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
	
	static class Consumer extends Thread {
		@Override
		public void run() {
			lock.lock();
			try {
				while(true) {
					if(null != value) {
						System.out.println(Thread.currentThread().getName() + " get value:" + value);
						value = null;
						condition.signalAll();
					} else {
						condition.await();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
	

}
