## ScheduledThreadPoolExecutor详解
ScheduledThreadPoolExecutor的执行主要分为两大部分。
1. 当调用ScheduledThreadPoolExecutor的scheduleAtFixedRate()方法或者scheduleWithFixedDelay()方法时，会向ScheduledThreadPoolExecutor的DelayQueue添加一个实现了RunnableScheduledFutur接口的ScheduledFutureTask。
2. 线程池中的线程从DelayQueue中获取ScheduledFutureTask，然后执行任务。

### ScheduledThreadPoolExecutor的使用
scheduleAtFixedRate：是以上一个任务开始的时间计时，period时间过去后，检测上一个任务是否执行完毕，如果上一个任务执行完毕，则当前任务立即执行，如果上一个任务没有执行完毕，则需要等上一个任务执行完毕后立即执行。

scheduleWithFixedDelay：是以上一个任务结束时开始计时，period时间过去后，立即执行。


```
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

```

```
package com.morris.ch5;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleWithFixedDelayTest {
	
	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		
		service.scheduleWithFixedDelay(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(System.currentTimeMillis());
				try {
					Thread.sleep(9000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("stop..." + System.currentTimeMillis());
			}
		}, 1, 5, TimeUnit.SECONDS);
	}

}

```
