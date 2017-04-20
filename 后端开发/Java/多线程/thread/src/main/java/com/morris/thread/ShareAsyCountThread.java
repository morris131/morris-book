package com.morris.thread;

public class ShareAsyCountThread extends Thread {

	private int count = 5;

	@Override
	public void run() {
		count--;
		System.out.println("Thread " + Thread.currentThread().getName() + " count:" + count);
	}

}
