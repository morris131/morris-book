package com.morris.thread;

public class ShareSynCountThread extends Thread {

	private int count = 5;

	@Override
	public synchronized void run() {
		count--;
		System.out.println("Thread " + Thread.currentThread().getName() + " count:" + count);
	}

}
