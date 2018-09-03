package com.morris.ch4;

import java.util.concurrent.locks.ReentrantLock;

public class HasQueueThreadTest {
	
	private static ReentrantLock lock = new ReentrantLock();
	
	public static void main(String[] args) throws InterruptedException {
		
		HasQueueThread t = new HasQueueThread();
		
		Thread a = new Thread(t);
		a.start();
		
		Thread.sleep(1000);
		
		Thread b = new Thread(t);
		b.start();
		
		Thread.sleep(2000);
		
		System.out.println("等待获取锁的线程数：" + lock.getQueueLength());
		System.out.println("线程b是否等待获取锁：" + lock.hasQueuedThread(b));
		System.out.println("是否有线程等待获取锁：" + lock.hasQueuedThreads());
		
	}
	
	static class HasQueueThread implements Runnable {
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
