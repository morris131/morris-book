package com.morris.ch5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SubmitTaskTest {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newFixedThreadPool(1);
		
		Future<String> future = service.submit(new Task());
		
		System.out.println(future.get());
	}
	
	static class Task implements Callable<String> {

		@Override
		public String call() throws Exception {
			return "hello";
		}
		
	}

}
