## synchronizedͬ�������

### synchronized(this)
�������߳�ͬʱ����ͬһ������object��synchronized(this)�����ʱ��һ��ʱ����ֻ��һ���߳���ִ�У���һ���̱߳���ȵ������߳�ִ����Ϻ����ִ�С�

synchronized(this)��synchronizedͬ������һ�������е��ǵ�ǰ���������

```java
package com.morris.ch2;

/**
 * 1) ��һ���߳��ȳ���b����������ڶ����̵߳���a�����synchronized�ķ������ߵ�������synchronized (this)�������ȴ���Ҳ����ͬ���� 
 * 2) ��һ���߳��ȳ���b����������������߳̿������첽����ʽ����a����ķ�synchronized�ķ����� 
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

������߳�ͬʱִ��synchronized(object)�����ʱ��ͬ��Ч����

�������߳�ִ��object�����е�synchronized��ʽʱ��ͬ��Ч����

�������߳�ִ��object�����е�synchronized(this)�����ʱ��ͬ��Ч����

�����߳̿����첽����object�����еķ�synchronized������



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