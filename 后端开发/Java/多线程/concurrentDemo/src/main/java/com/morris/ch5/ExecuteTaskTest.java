package com.morris.ch5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteTaskTest {
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(1);
		service.execute(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("running");
			}
		});
	}

}
