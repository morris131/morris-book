<<<<<<< HEAD
package com.morris.ch1;

public class CurrentThreadTest extends Thread{

    public CurrentThreadTest() {
        System.out.println("[CurrentThreadTest]Thread.currentThread().getName():" + Thread.currentThread().getName());
        System.out.println("[CurrentThreadTest]this.getName:" + this.getName());
    }

    @Override
    public void run() {
        System.out.println("[run]Thread.currentThread().getName():" + Thread.currentThread().getName());
        System.out.println("[run]this.getName:" + this.getName());
    }

    public static void main(String[] args) {
        CurrentThreadTest thread = new CurrentThreadTest();
        thread.start();

    }
}
=======
package com.morris.ch1;

public class CurrentThreadTest extends Thread{

    public CurrentThreadTest() {
        System.out.println("[CurrentThreadTest]Thread.currentThread().getName():" + Thread.currentThread().getName());
        System.out.println("[CurrentThreadTest]this.getName:" + this.getName());
    }

    @Override
    public void run() {
        System.out.println("[run]Thread.currentThread().getName():" + Thread.currentThread().getName());
        System.out.println("[run]this.getName:" + this.getName());
    }

    public static void main(String[] args) {
        CurrentThreadTest thread = new CurrentThreadTest();
        thread.start();

    }
}
>>>>>>> 8d15f764d31a4751954af63ff5a72f44693230f4
