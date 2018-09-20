<<<<<<< HEAD
package com.morris.ch1;

public class IsAliveTest extends Thread {

    public IsAliveTest() {
        System.out.println("[CurrentThreadTest]Thread.currentThread():" + Thread.currentThread().getName() + " " + Thread.currentThread().isAlive());
        System.out.println("[CurrentThreadTest]this:" + this.getName() + " " + this.isAlive());
    }

    @Override
    public void run() {
        System.out.println("[run]Thread.currentThread():" + Thread.currentThread().getName() + " " + Thread.currentThread().isAlive());
        System.out.println("[run]this:" + this.getName() + " " + this.isAlive());
    }

    public static void main(String[] args) throws InterruptedException {
        IsAliveTest c = new IsAliveTest();
        Thread thread = new Thread(c);
        System.out.println("start begin: " + thread.isAlive());
        thread.start();
        System.out.println("start end:" + thread.isAlive());
        Thread.sleep(1000);
        System.out.println("after sleep 1000:" + thread.isAlive());
    }
}
=======
package com.morris.ch1;

public class IsAliveTest extends Thread {

    public IsAliveTest() {
        System.out.println("[CurrentThreadTest]Thread.currentThread():" + Thread.currentThread().getName() + " " + Thread.currentThread().isAlive());
        System.out.println("[CurrentThreadTest]this:" + this.getName() + " " + this.isAlive());
    }

    @Override
    public void run() {
        System.out.println("[run]Thread.currentThread():" + Thread.currentThread().getName() + " " + Thread.currentThread().isAlive());
        System.out.println("[run]this:" + this.getName() + " " + this.isAlive());
    }

    public static void main(String[] args) throws InterruptedException {
        IsAliveTest c = new IsAliveTest();
        Thread thread = new Thread(c);
        System.out.println("start begin: " + thread.isAlive());
        thread.start();
        System.out.println("start end:" + thread.isAlive());
        Thread.sleep(1000);
        System.out.println("after sleep 1000:" + thread.isAlive());
    }
}
>>>>>>> 8d15f764d31a4751954af63ff5a72f44693230f4
