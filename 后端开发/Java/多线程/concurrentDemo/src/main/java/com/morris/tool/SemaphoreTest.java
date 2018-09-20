package com.morris.tool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	
	private static Semaphore semaphore = new Semaphore(10);
	private static ExecutorService executor = Executors.newFixedThreadPool(30);
	
	public static void main(String[] args) {
		
		for(int i = 0; i < 30; i++) {
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						semaphore.acquire();
						System.out.println("save data");
						Thread.sleep(1000);
						semaphore.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			});
		}
		
		executor.shutdown();
		
	}

}
