## synchronizedͬ������
### synchronized������
```java
package com.morris.ch2;

/**
 * 1) ��һ���߳��ȳ���a����������ڶ����̵߳���a�����synchronized�ķ�����ȴ���Ҳ����ͬ���� 
 * 2) ��һ���߳��ȳ���a����������������߳̿������첽����ʽ����a����ķ�synchronized�ķ����� 
 *
 */
public class SynchronizedMethod {

	public static void main(String[] args) {
		A a = new A();
		new Thread(() -> a.method1()).start();
		new Thread(() -> a.method2()).start();
		new Thread(() -> a.method3()).start();
	}

}

class A {

	public synchronized void method1() {
		System.out.println("method1 begin...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("method1 end...");
	}

	public synchronized void method2() {
		System.out.println("method2");
	}

	public void method3() {
		System.out.println("method3");
	}

}


```

### synchronized�ļ̳���

����̳и���ʱ,���û����д�����е�ͬ������,����ͬһ����,�ڲ�ͬ�̲߳������ø÷���ʱ,����ͬ��Ч����

����̳и���,������д�����е�ͬ������,��û����ӹؼ���synchronized,����ͬһ����,�ڲ�ͬ�̲߳������ø÷���ʱ,���پ���ͬ��Ч����

#### �̳в���д
```java
package com.morris.ch2;

/**
 * 
 * ����̳и���ʱ,���û����д�����е�ͬ������,����ͬһ����,�ڲ�ͬ�̲߳������ø÷���ʱ,����ͬ��Ч����
 *
 */
public class SynchronizedHaveInheritance {
	
	public static void main(String[] args) throws InterruptedException {
		Sub2 sub = new Sub2();
		//Father father = new Father();
		ThreadOne2 threadOne = new ThreadOne2(sub);
		Thread thread = new Thread(threadOne);
		thread.start();
		ThreadTwo2 threadTwo = new ThreadTwo2(sub);
		Thread thread2 = new Thread(threadTwo);
		thread2.start();
	}
	
}

class ThreadOne2 implements Runnable {

	private Father2 father;

	public ThreadOne2(Father2 sub) {
		this.father = sub;
	}

	@Override
	public void run() {
		father.service();
	}
}

class ThreadTwo2 implements Runnable {

	private Father2 father;

	public ThreadTwo2(Father2 father) {
		this.father = father;
	}

	@Override
	public void run() {
		father.service();
	}
}

class Father2 {
	
	protected int count = 0;

	public synchronized void service() {
		for (int i = 0; i < 5; i++) {
			System.out.println("Thread name:" + Thread.currentThread().getName() + " count:" + count++);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Sub2 extends Father2 {
}



```

#### �̳в���д
```java
package com.morris.ch2;

/**
 * ����̳и���,������д�����е�ͬ������,��û����ӹؼ���synchronized,����ͬһ����,�ڲ�ͬ�̲߳������ø÷���ʱ,���پ���ͬ��Ч��
 *
 */
public class SynchronizedNotHaveInheritance {
	
	public static void main(String[] args) throws InterruptedException {
		Sub1 sub = new Sub1();
		//Father father = new Father();
		ThreadOne1 threadOne = new ThreadOne1(sub);
		Thread thread = new Thread(threadOne);
		thread.start();
		ThreadTwo1 threadTwo = new ThreadTwo1(sub);
		Thread thread2 = new Thread(threadTwo);
		thread2.start();
	}
	
}

class ThreadOne1 implements Runnable {

	private Father1 father;

	public ThreadOne1(Father1 sub) {
		this.father = sub;
	}

	@Override
	public void run() {
		father.service();
	}
}

class ThreadTwo1 implements Runnable {

	private Father1 father;

	public ThreadTwo1(Father1 father) {
		this.father = father;
	}

	@Override
	public void run() {
		father.service();
	}
}

class Father1 {
	
	protected int count = 0;

	public synchronized void service() {
		for (int i = 0; i < 5; i++) {
			System.out.println("Thread name:" + Thread.currentThread().getName() + " count:" + count++);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Sub1 extends Father1 {
	@Override
	public void service() {
		for (int i = 0; i < 5; i++) {
			System.out.println("Thread name:" + Thread.currentThread().getName() + " count:" + count++);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

```