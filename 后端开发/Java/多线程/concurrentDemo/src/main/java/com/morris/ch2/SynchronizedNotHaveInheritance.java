package com.morris.ch2;

/**
 * 子类继承父类,并且重写父类中的同步方法,但没有添加关键字synchronized,子类同一对象,在不同线程并发调用该方法时,不再具有同步效果
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
