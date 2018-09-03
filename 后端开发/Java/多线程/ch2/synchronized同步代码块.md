## synchronized同步代码块

### synchronized(this)
当两个线程同时访问同一个对象object的synchronized(this)代码块时，一段时间内只有一个线程能执行，另一个线程必须等到当期线程执行完毕后才能执行。

synchronized(this)与synchronized同步方法一样，持有的是当前对象的锁。

```java
package com.morris.ch2;

/**
 * 1) 第一个线程先持有b对象的锁，第二个线程调用a对象的synchronized的方法或者调用其他synchronized (this)代码块需等待，也就是同步。 
 * 2) 第一个线程先持有b对象的锁，第三个线程可以以异步的形式调用a对象的非synchronized的方法。 
 *
 */
public class SynchronizedThis {

	public static void main(String[] args) {
		B a = new B();
		new Thread(() -> a.method1()).start();
		new Thread(() -> a.method2()).start();
		new Thread(() -> a.method3()).start();
	}

}

class B {

	public void method1() {
		synchronized (this) {			
			System.out.println("method1 begin...");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("method1 end...");
		}
	}

	public synchronized void method2() {
		System.out.println("method2");
	}

	public void method3() {
		System.out.println("method3");
	}

}


```

### synchronized(object)

当多个线程同时执行synchronized(object)代码块时呈同步效果。

当其他线程执行object对象中的synchronized方式时呈同步效果。

当其他线程执行object对象中的synchronized(this)代码块时呈同步效果。

其他线程可以异步调用object对象中的非synchronized方法。



```java
package com.morris.ch2;

public class SynchronizedObject {

	public static void main(String[] args) {
		D d = new D();
		C c = new C(d);
		new Thread(() -> c.method1()).start();
		new Thread(() -> c.method2()).start();
		new Thread(() -> d.method1()).start();
		new Thread(() -> d.method2()).start();
		new Thread(() -> d.method3()).start();
	}

}

class C {

	private D d;

	C(D d) {
		this.d = d;
	}

	public void method1() {
		synchronized (d) {
			System.out.println("c method1 begin...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("c method1 end...");
		}
	}

	public void method2() {
		synchronized (d) {
			System.out.println("c method2 begin...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("c method2 end...");
		}
	}

}

class D {

	public synchronized void method1() {
		System.out.println("d method1 begin...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("d method1 end...");
	}

	public void method2() {
		synchronized (this) {
			System.out.println("d method2 begin...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("d method2 end...");
		}
	}

	public void method3() {
		System.out.println("d method3 ...");
	}

}

```