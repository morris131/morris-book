package com.morris.ch4;

import java.util.concurrent.locks.ReentrantLock;

public class IsFairTest {
	
	private static ReentrantLock lock = new ReentrantLock(true);
	
	public static void main(String[] args) {
		new IsFairThread().start();
	}
	
	static class IsFairThread extends Thread {
		@Override
		public void run() {
			System.out.println("当期线程是否保持此锁定：" + lock.isHeldByCurrentThread());
			System.out.println("查询此锁定是否由线程保持：" + lock.isLocked());
			lock.lock();
			System.out.println("当期线程是否保持此锁定：" + lock.isHeldByCurrentThread());
			System.out.println("查询此锁定是否由线程保持：" + lock.isLocked());
			try {
				System.out.println("锁是否公平：" + lock.isFair());
			} finally {
				lock.unlock();
			}
		}
	}

}
