## synchronized类

### synchronized同步静态方法和class代码块

synchronized加到静态方法上是给Class对象上锁，而synchronized加到非静态方法上是给对象上锁。

```java
package com.morris.ch2;

public class SynchronizedClass {
	
	public static void main(String[] args) {		
		E e = new E();
		new Thread(() -> e.method1()).start();
		new Thread(() -> e.method2()).start();
		new Thread(() -> e.method3()).start();
		new Thread(() -> e.method4()).start();
	}

}

class E {
	
	public synchronized static void method1() {
		System.out.println("e method1 begin...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("e method1 end...");
	}
	
	public synchronized static void method2() {
		System.out.println("e method2 begin...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("e method2 end...");
	}
	
	public void method3() {
		synchronized (E.class) {
			System.out.println("e method3 begin...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("e method3 end...");
		}
	}
	
	public synchronized void method4() {
		System.out.println("e method4");
	}
	
}


```