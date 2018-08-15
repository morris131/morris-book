## ReentrantLock的使用

### 简单使用
ReentrantLock实现synchronized代码块的功能。
```java
package com.morris.ch4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

	private static int count = 10;

	private static Lock lock = new ReentrantLock();

	public static void main(String[] args) {
		new A().start();
		new A().start();
		new A().start();
	}

	static class A extends Thread {
		@Override
		public void run() {
			while (true) {
				lock.lock();
				try {
					if (0 == count) {
						break;
					}
					System.out.println(count--);
				} finally {
					lock.unlock();
				}
			}
		}
	}

}

```

### Condition实现等待/通知
Condition类的await()方法相当于Object类的wait()方法。

Condition类的await(long time, TimeUnit unit)方法相当于Object类的wait(long timeout)方法。

Condition类的signal()方法相当于Object类的notify()方法。

Condition类的signalAll()方法相当于Object类的notifyAll()方法。

```java
package com.morris.ch4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OneConditionWaitNotifyTest {
	
	private static Lock lock = new ReentrantLock();
	
	private static Condition condition = lock.newCondition();
	
	public static void main(String[] args) throws InterruptedException {
		A a = new A();
		a.start();
		Thread.sleep(1000);
		a.signal();
	}
	
	static class A extends Thread {
		@Override
		public void run() {
			await();
		}
		
		public void await() {
			lock.lock();
			try {
				try {
					System.out.println("thread A is waiting...");
					condition.await();
					System.out.println("thread A is stoped");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} finally {
				lock.unlock();
			}
		}
		
		public void signal() {
			lock.lock();
			try {
				System.out.println("notify thread a...");
				condition.signal();
			} finally {
				lock.unlock();
			}
		}
		
	}

}

```

### 多个Condition通知部分线程

```java
package com.morris.ch4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MoreConditionWaitNotifyTest {
	
	private static Lock lock = new ReentrantLock();
	
	private static Condition conditionA = lock.newCondition();
	
	private static Condition conditionB = lock.newCondition();
	
	public static void main(String[] args) throws InterruptedException {
		A a = new A();
		a.start();
		B b = new B();
		b.start();
		Thread.sleep(1000);
		a.signal();
	}
	
	static class A extends Thread {
		@Override
		public void run() {
			await();
		}
		
		public void await() {
			lock.lock();
			try {
				try {
					System.out.println("thread A is waiting...");
					conditionA.await();
					System.out.println("thread A is stoped");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} finally {
				lock.unlock();
			}
		}
		
		public void signal() {
			lock.lock();
			try {
				System.out.println("notify thread A...");
				conditionA.signalAll();
			} finally {
				lock.unlock();
			}
		}
		
	}
	
	static class B extends Thread {
		@Override
		public void run() {
			await();
		}
		
		public void await() {
			lock.lock();
			try {
				try {
					System.out.println("thread B is waiting...");
					conditionB.await();
					System.out.println("thread B is stoped");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} finally {
				lock.unlock();
			}
		}
		
		public void signal() {
			lock.lock();
			try {
				System.out.println("notify thread B...");
				conditionB.signalAll();
			} finally {
				lock.unlock();
			}
		}
		
	}

}

```




