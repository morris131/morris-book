## join的使用
如果一个线程A执行了thread.join()语句，其含义是：当前线程A等待thread线程终止之后才从thread.join()返回。线程Thread除了提供join()方法之外，还提供了join(long millis)和join(long millis,int nanos)两个具备超时特性的方法。这两个超时方法表示，如果线程thread在给定的超时时间里没有终止，那么将会从该超时方法中返回。

### 简单使用
```java
package com.morris.ch3;

public class SimpleJoinTest {
	
	public static void main(String[] args) throws InterruptedException {
		A a  = new A();
		a.start();
		a.join();
		
		System.out.println("main thread is end");
	}
	
	static class A extends Thread {
		@Override
		public void run() {
			try {
				System.out.println("A is runing...");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

```

### 中断join
在join过程中，如果当前线程被中断，会抛出异常。
```java
package com.morris.ch3;

public class InterruptJoinTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		B b = new B();
		b.start();
		
		b.interrupt();
		
	}
	
	static class A extends Thread {
		@Override
		public void run() {
			System.out.println("A is runing...");
			while (true) {
			}
		}
	}
	
	static class B extends Thread {
		
		@Override
		public void run() {
			try {
				A a = new A();
				a.start();
				a.join();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
	
}

```

### join(long)与sleep(long)的区别
join底层使用wait()方法实现的，会释放锁，sleep不会释放锁。
```java
package com.morris.ch3;


public class JoinVsSleepTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		A a = new A();
		
		B b = new B(a);
		b.start();
		
		Thread.sleep(500);
		
		a.print();
		
	}
	
	static class A extends Thread {
		@Override
		public void run() {
			System.out.println("A run begin");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("A run end");
		}
		
		synchronized void print() {
			System.out.println("A print");
		}
	}
	
	static class B extends Thread {
		
		private A a;
		
		B(A a) {
			this.a = a;
		}
		
		@Override
		public void run() {
			synchronized (a) {				
				try {
					a.start();
					System.out.println("join start");
					a.join();
					//Thread.sleep(2000);
					System.out.println("join end");
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
}

```
