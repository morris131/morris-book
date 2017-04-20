package com.morris.thread;

public class RunnableTest implements Runnable {

	public void run() {
		System.out.println("This thread is implement Runnable interface");
	}

	public static void main(String[] args) {
		Thread thread = new Thread(new RunnableTest());
		thread.start();
	}

}
