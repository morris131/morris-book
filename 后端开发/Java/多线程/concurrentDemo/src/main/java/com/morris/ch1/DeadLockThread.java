<<<<<<< HEAD
package com.morris.ch1;

public class DeadLockThread {
	
	public static void main(String[] args) {
		
		A a = new A();
		new Thread(() -> a.method1()).start();
		new Thread(() -> a.method2()).start();
	}
	

}

class A {
	
	Object object1 = new Object();
	Object object2 = new Object();
	
	public void method1() {
		synchronized (object1) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (object2) {
			}
		}
	}
	
	public void method2() {
		synchronized (object2) {
			synchronized (object1) {
			}
		}
	}
	
}
=======
package com.morris.ch1;

public class DeadLockThread {
	
	public static void main(String[] args) {
		
		A a = new A();
		new Thread(() -> a.method1()).start();
		new Thread(() -> a.method2()).start();
	}
	

}

class A {
	
	Object object1 = new Object();
	Object object2 = new Object();
	
	public void method1() {
		synchronized (object1) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (object2) {
			}
		}
	}
	
	public void method2() {
		synchronized (object2) {
			synchronized (object1) {
			}
		}
	}
	
}
>>>>>>> 8d15f764d31a4751954af63ff5a72f44693230f4
