package com.morris.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class ABATest {

	private static AtomicInteger atomicInteger = new AtomicInteger(0);

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			final int num = i;
			new Thread() {
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (atomicInteger.compareAndSet(0, 1)) {
						System.out.println("我是线程：" + num + "，我获得了锁进行了对象修改！");
					}
				}
			}.start();
		}

		new Thread() {
			public void run() {
				while (!atomicInteger.compareAndSet(1, 0))
					;
				System.out.println("已经改为原始值！");
			}
		}.start();
	}

}
