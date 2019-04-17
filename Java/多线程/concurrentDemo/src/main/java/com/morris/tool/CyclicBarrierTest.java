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
