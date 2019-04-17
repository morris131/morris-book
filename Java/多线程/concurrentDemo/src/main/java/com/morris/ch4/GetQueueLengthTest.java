package com.morris.ch4;

import java.util.concurrent.locks.ReentrantLock;

public class GetQueueLengthTest {
	
	private static ReentrantLock lock = new ReentrantLock();
	
	public static void main(String[] args) throws InterruptedException {
		
		GetQueueLengthThread t = new GetQueueLengthThread();
		
		Thread[] threads = new Thread[10];
		for(int i = 0; i < 10; i++) {
			threads[i] = new Thread(t);
		}
		
		for(int i = 0; i < 10; i++) {
			threads[i].start();
		}
		
		Thread.sleep(2000);
		
		System.out.println("当期等待获取锁的线程数：" + lock.getQueueLength());
		
	}
	
	static class GetQueueLengthThread implements Runnable {
		@Override
		public void run() {
			lock.lock();
			try {
				while (true) {
				}
			} finally {
				lock.unlock();
			}
		}
		
	}

}
