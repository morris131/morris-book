## ReentrantLock方法的使用

### getHoldCount()的使用
getHoldCount()方法获取当前线程保持此锁的次数。

```
package com.morris.ch4;

import java.util.concurrent.locks.ReentrantLock;

public class GetHoldCountTest {
	
	
	public static void main(String[] args) {
		new GetHoldCountThread().start();
	}
	
	static class GetHoldCountThread extends Thread {
		
		private ReentrantLock lock = new ReentrantLock();
		
		@Override
		public void run() {
			method1();
		}
		
		public void method1() {
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName() + " has hold count: " + lock.getHoldCount());
				method2();
			} finally {
				lock.unlock();
			}
		}
		
		public void method2() {
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName() + " has hold count: " + lock.getHoldCount());
			} finally {
				lock.unlock();
			}
		}
		
	}

}

```

### getQueueLength()的使用
getQueueLength()获取正等待获此锁的线程数。

```
package com.morris.ch4;

import java.util.concurrent.locks.ReentrantLock;

public class GetQueueLengthTest {
	
	private static ReentrantLock lock = new ReentrantLock();
	
	public static void main(String[] args) throws InterruptedException {
		
		GetQueueLengthThread t = new GetQueueLengthThread();
		
		Thread[] threads = new Thread[10];
		for(int i = 0; i < 10; i++) {
			threads[i] = new Thread(t);
		}
		
		for(int i = 0; i < 10; i++) {
			threads[i].start();
		}
		
		Thread.sleep(2000);
		
		System.out.println("当期等待获取锁的线程数：" + lock.getQueueLength());
		
	}
	
	static class GetQueueLengthThread implements Runnable {
		@Override
		public void run() {
			lock.lock();
			try {
				while (true) {
				}
			} finally {
				lock.unlock();
			}
		}
		
	}

}

```

### getWaitQueueLength()的使用
getWaitQueueLength()返回与等待此锁定相关的给定条件的Condition的线程数。

```
package com.morris.ch4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class GetWaitQueueLengthTest {
	
	private static ReentrantLock lock = new ReentrantLock();
	
	private static Condition condition = lock.newCondition();
	
	public static void main(String[] args) throws InterruptedException {
		
		GetWaitQueueLengthThread t = new GetWaitQueueLengthThread();
		
		Thread[] threads = new Thread[10];
		
		for(int i = 0; i < 10; i++) {
			threads[i] = new Thread(t);
		}
		
		for(int i = 0; i < 10; i++) {
			threads[i].start();
		}
		
		Thread.sleep(2000);
		
	}
	
	static class GetWaitQueueLengthThread implements Runnable {
		@Override
		public void run() {
			lock.lock();
			try {
				System.out.println("当期等待获取锁的线程数：" + lock.getWaitQueueLength(condition));
				condition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}	

}

```

### hasQueueThread()和hasQueueThreads()的使用
hasQueueThread()查询指定的线程是否正在等待获取此锁定。

hasQueueThreads()查询是否有线程是否正在等待获取此锁定。

```
package com.morris.ch4;

import java.util.concurrent.locks.ReentrantLock;

public class HasQueueThreadTest {
	
	private static ReentrantLock lock = new ReentrantLock();
	
	public static void main(String[] args) throws InterruptedException {
		
		HasQueueThread t = new HasQueueThread();
		
		Thread a = new Thread(t);
		a.start();
		
		Thread.sleep(1000);
		
		Thread b = new Thread(t);
		b.start();
		
		Thread.sleep(2000);
		
		System.out.println("等待获取锁的线程数：" + lock.getQueueLength());
		System.out.println("线程b是否等待获取锁：" + lock.hasQueuedThread(b));
		System.out.println("是否有线程等待获取锁：" + lock.hasQueuedThreads());
		
	}
	
	static class HasQueueThread implements Runnable {
		@Override
		public void run() {
			lock.lock();
			try {
				while (true) {
				}
			} finally {
				lock.unlock();
			}
		}
		
	}

}

```

### hasWaiters()的使用
hasWaiters()查询是否有线程在等待与此锁有关的condition条件。
```
package com.morris.ch4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class HasWaitersTest {
		
		private static ReentrantLock lock = new ReentrantLock();
		
		private static Condition condition = lock.newCondition();
		
		public static void main(String[] args) throws InterruptedException {
			
			GetWaitQueueLengthThread t = new GetWaitQueueLengthThread();
			
			Thread[] threads = new Thread[10];
			
			for(int i = 0; i < 10; i++) {
				threads[i] = new Thread(t);
			}
			
			for(int i = 0; i < 10; i++) {
				threads[i].start();
			}
			
			Thread.sleep(2000);
			
		}
		
		static class GetWaitQueueLengthThread implements Runnable {
			@Override
			public void run() {
				lock.lock();
				try {
					System.out.println("有没有线程在等待condition：" + lock.hasWaiters(condition) + " ,线程数：" + lock.getWaitQueueLength(condition));
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}	

	}


```

### isFair()、isHeldByCurrentThread()、isLocked()
isFair()：查询此锁是否公平。
isHeldByCurrentThread()：查询当期线程是否保持此锁定。
isLocked()：查询此锁定是否由线程保持。

```
package com.morris.ch4;

import java.util.concurrent.locks.ReentrantLock;

public class IsFairTest {
	
	private static ReentrantLock lock = new ReentrantLock(true);
	
	public static void main(String[] args) {
		new IsFairThread().start();
	}
	
	static class IsFairThread extends Thread {
		@Override
		public void run() {
			System.out.println("当期线程是否保持此锁定：" + lock.isHeldByCurrentThread());
			System.out.println("查询此锁定是否由线程保持：" + lock.isLocked());
			lock.lock();
			System.out.println("当期线程是否保持此锁定：" + lock.isHeldByCurrentThread());
			System.out.println("查询此锁定是否由线程保持：" + lock.isLocked());
			try {
				System.out.println("锁是否公平：" + lock.isFair());
			} finally {
				lock.unlock();
			}
		}
	}

}


```

