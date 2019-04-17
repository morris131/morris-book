package com.morris.ch4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

	private static int count = 10;

	private static Lock lock = new ReentrantLock();

	public static void main(String[] args) {
		new A().start();
		new A().start();
		new A().start();
	}

	static class A extends Thread {
		@Override
		public void run() {
			while (true) {
				lock.lock();
				try {
					if (0 == count) {
						break;
					}
					System.out.println(count--);
				} finally {
					lock.unlock();
				}
			}
		}
	}

}
