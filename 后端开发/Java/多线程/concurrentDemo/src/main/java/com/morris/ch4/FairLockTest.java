package com.morris.ch4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairLockTest {

	public static void main(String[] args) {
		
		Service service = new Service(false);
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "is running");
				service.method();
			}
		};
		
		Thread[] threads = new Thread[10];
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(runnable);
		}

		for (int i = 0; i < 10; i++) {
			threads[i].start();
		}
	}

	static class Service {
		
		private Lock lock;

		Service(boolean isFair) {
			lock = new ReentrantLock(isFair);
		}
		
		public void method() {
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName() + "is get lock");
			} finally {
				lock.unlock();
			}
		}
	}

}
