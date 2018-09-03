package com.morris.tool;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

	private static CountDownLatch countDownLatch = new CountDownLatch(2);

	public static void main(String[] args) throws InterruptedException {

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("parse sheet1");
				countDownLatch.countDown();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("parse sheet2");
				countDownLatch.countDown();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		countDownLatch.await();

		System.out.println("deal over");
	}

}
