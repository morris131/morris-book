## CyclicBarrier关卡的使用

### CyclicBarrier
CyclicBarrier默认的构造方法是CyclicBarrier（int parties），其参数表示屏障拦截的线程数量，每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞。


```
package com.morris.tool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 模拟场景：公交车，坐满3人即可发车。
 *
 */
public class CyclicBarrierTest {
	
	private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
	
	public static void main(String[] args) {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					System.out.println("passenger1 get on bus");
					cyclicBarrier.await();
					System.out.println("bus is full");
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		}).start();;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					System.out.println("passenger2 get on bus");
					cyclicBarrier.await();
					System.out.println("bus is full");
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		}).start();;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					System.out.println("passenger3 get on bus");
					cyclicBarrier.await();
					System.out.println("bus is full");
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		}).start();;
		
	}

}

```

### CountDownLatch与CyclicBarrier的区别

CountDownLatch：一个或者多个线程，等待其他多个线程完成某件事情之后才能执行；调用countDown()方法计数减一,计算为0时释放所有等待的线程，无法重置。

CyclicBarrier：多个线程互相等待，直到到达同一个同步点，再继续一起执行。计数达到指定值时释放所有等待线程，计数置为0重新开始，可重复利用。