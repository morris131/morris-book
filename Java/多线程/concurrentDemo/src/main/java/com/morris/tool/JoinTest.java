package com.morris.tool;

public class JoinTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread a = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("parse sheet1");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread b = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("parse sheet2");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		a.start();
		b.start();
		a.join();
		b.join();
		
		System.out.println("deal over");
		
	}

}
