## CountDownLatch
假如有这样一个需求：我们需要解析一个Excel里多个sheet的数据，此时可以考虑使用多线程，每个线程解析一个sheet里的数据，等到所有的sheet都解析完之后，程序需要提示解析完成。下面分别用join和CountDownLatch实现。

### join实现
join用于让当前执行线程等待join线程执行结束。

```
package com.morris.tool;

public class JoinTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread a = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("parse sheet1");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread b = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("parse sheet2");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		a.start();
		b.start();
		a.join();
		b.join();
		
		System.out.println("deal over");
		
	}

}

```

### CountDownLatch

CountDownLatch的构造函数接收一个int类型的参数作为计数器，如果你想等待N个点完成，这里就传入N。当我们调用CountDownLatch的countDown方法时，N就会减1，CountDownLatch的await方法会阻塞当前线程，直到N变成零。由于countDown方法可以用在任何地方，所以这里说的N个点，可以是N个线程，也可以是1个线程里的N个执行步骤。用在多个线程时，只需要把这个
CountDownLatch的引用传递到线程里即可。

```
package com.morris.tool;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

	private static CountDownLatch countDownLatch = new CountDownLatch(2);

	public static void main(String[] args) throws InterruptedException {

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("parse sheet1");
				countDownLatch.countDown();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("parse sheet2");
				countDownLatch.countDown();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		countDownLatch.await();

		System.out.println("deal over");
	}

}

```
