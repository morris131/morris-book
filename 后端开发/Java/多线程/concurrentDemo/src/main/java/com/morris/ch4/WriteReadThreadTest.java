package com.morris.ch4;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WriteReadThreadTest {
	
	private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	public static void main(String[] args) {
		
		new WriteThread().start();
		new ReadThread().start();
		
	}
	
	static class ReadThread extends Thread {
		@Override
		public void run() {
			
			lock.readLock().lock();
			try {
				System.out.println("获取读锁：" + System.currentTimeMillis());
				Thread.sleep(10000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.readLock().unlock();
			}
			
		}
	}
	
	static class WriteThread extends Thread {
		@Override
		public void run() {
			
			lock.writeLock().lock();
			try {
				System.out.println("获取写锁：" + System.currentTimeMillis());
				Thread.sleep(10000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.writeLock().unlock();
			}
			
		}
	}
}