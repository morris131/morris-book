package com.morris.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

	private static AtomicInteger atomicInteger = new AtomicInteger();

	public static void main(String[] args) throws InterruptedException {
		final Thread[] threads = new Thread[10];
		for (int i = 0; i < 10; i++) {
			final int num = i;
			threads[i] = new Thread() {
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					int now = atomicInteger.incrementAndGet();
					System.out.println("我是线程：" + num + "，我得到值了，增加后的值为：" + now);
				}
			};
			threads[i].start();
		}
		for (Thread t : threads) {
			t.join();
		}
		System.out.println("最终运行结果：" + atomicInteger.get());
	}
}
