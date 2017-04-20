package com.morris.thread;

public class NoShareCountThread extends Thread {

	private int count = 5;

	public NoShareCountThread(String threadName) {
		super(threadName);
	}

	@Override
	public void run() {
		while (count > 0) {
			count--;
			System.out.println("Thread " + Thread.currentThread().getName() + " count:" + count);
		}
	}

}
