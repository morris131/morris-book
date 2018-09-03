package com.morris.ch4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class HasWaitersTest {
		
		private static ReentrantLock lock = new ReentrantLock();
		
		private static Condition condition = lock.newCondition();
		
		public static void main(String[] args) throws InterruptedException {
			
			GetWaitQueueLengthThread t = new GetWaitQueueLengthThread();
			
			Thread[] threads = new Thread[10];
			
			for(int i = 0; i < 10; i++) {
				threads[i] = new Thread(t);
			}
			
			for(int i = 0; i < 10; i++) {
				threads[i].start();
			}
			
			Thread.sleep(2000);
			
		}
		
		static class GetWaitQueueLengthThread implements Runnable {
			@Override
			public void run() {
				lock.lock();
				try {
					System.out.println("有没有线程在等待condition：" + lock.hasWaiters(condition) + " ,线程数：" + lock.getWaitQueueLength(condition));
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}	

	}

