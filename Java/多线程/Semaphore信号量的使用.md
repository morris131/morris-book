## Semaphore信号量的使用
Semaphore（信号量）是用来控制同时访问特定资源的线程数量，它通过协调各个线程，以
保证合理的使用公共资源。

模拟场景：30个线程同时存储数据到数据库，而数据库的连接数只有10个，这时我们必须控制只有10个线程同时获取数据库连接保存数据，否则会报错无法获取数据库连接。


```
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

```
