<<<<<<< HEAD
package com.morris.ch2;

/**
 * 
 * 子类继承父类时,如果没有重写父类中的同步方法,子类同一对象,在不同线程并发调用该方法时,具有同步效果。
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


=======
package com.morris.ch2;

/**
 * 
 * 子类继承父类时,如果没有重写父类中的同步方法,子类同一对象,在不同线程并发调用该方法时,具有同步效果。
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


>>>>>>> 8d15f764d31a4751954af63ff5a72f44693230f4
