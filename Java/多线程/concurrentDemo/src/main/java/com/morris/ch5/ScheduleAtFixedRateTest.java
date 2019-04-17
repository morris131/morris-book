package com.morris.ch5;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleAtFixedRateTest {
	
	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		
		service.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(System.currentTimeMillis());
				try {
					Thread.sleep(9000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("stop...");
			}
		}, 1, 5, TimeUnit.SECONDS);
	}

}
