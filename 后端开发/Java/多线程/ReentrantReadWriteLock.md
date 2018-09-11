## ReentrantReadWriteLock
synchronized和ReentrantLock都是排他锁，这些锁在同一时刻只允许一个线程进行访问，而读写锁在同一时刻可以允许多个读线程访问，但是在写线程访问时，所有的读线程和其他写线程均被阻塞。读写锁维护了一对锁，一个读锁和一个写锁，通过分离读锁和写锁，使得并发性相比一般的排他锁有了很大提升。

### 读读共享

```
package com.morris.ch4;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadReadThreadTest {
	
	private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	public static void main(String[] args) {
		
		new ReadThread().start();
		new ReadThread().start();
		
	}
	
	static class ReadThread extends Thread {
		@Override
		public void run() {
			
			lock.readLock().lock();
			try {
				System.out.println("获取读锁：" + System.currentTimeMillis());
				Thread.sleep(10000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.readLock().unlock();
			}
			
		}
	}

}

```

### 读写互斥

```
package com.morris.ch4;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteThreadTest {
	
	private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	public static void main(String[] args) {
		
		new ReadThread().start();
		new WriteThread().start();
		
	}
	
	static class ReadThread extends Thread {
		@Override
		public void run() {
			
			lock.readLock().lock();
			try {
				System.out.println("获取读锁：" + System.currentTimeMillis());
				Thread.sleep(10000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.readLock().unlock();
			}
			
		}
	}
	
	static class WriteThread extends Thread {
		@Override
		public void run() {
			
			lock.writeLock().lock();
			try {
				System.out.println("获取写锁：" + System.currentTimeMillis());
				Thread.sleep(10000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.writeLock().unlock();
			}
			
		}
	}
}
```

### 写读互斥

```
package com.morris.ch4;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WriteReadThreadTest {
	
	private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	public static void main(String[] args) {
		
		new WriteThread().start();
		new ReadThread().start();
		
	}
	
	static class ReadThread extends Thread {
		@Override
		public void run() {
			
			lock.readLock().lock();
			try {
				System.out.println("获取读锁：" + System.currentTimeMillis());
				Thread.sleep(10000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.readLock().unlock();
			}
			
		}
	}
	
	static class WriteThread extends Thread {
		@Override
		public void run() {
			
			lock.writeLock().lock();
			try {
				System.out.println("获取写锁：" + System.currentTimeMillis());
				Thread.sleep(10000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.writeLock().unlock();
			}
			
		}
	}
}
```

### 写写互斥

```
package com.morris.ch4;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WriteWriteThreadTest {
	
	private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	public static void main(String[] args) {
		
		new WriteThread().start();
		new WriteThread().start();
		
	}
	
	static class WriteThread extends Thread {
		@Override
		public void run() {
			
			lock.writeLock().lock();
			try {
				System.out.println("获取写锁：" + System.currentTimeMillis());
				Thread.sleep(10000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.writeLock().unlock();
			}
			
		}
	}

}

```


