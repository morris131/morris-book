package com.morris.thread;

public class ThreadTest extends Thread{
	
	@Override
	public void run() {
		System.out.println("This thread is extends Thread class");
	}
	
	public static void main(String[] args) {
		ThreadTest threadTest = new ThreadTest();
		threadTest.start();
	}

}
