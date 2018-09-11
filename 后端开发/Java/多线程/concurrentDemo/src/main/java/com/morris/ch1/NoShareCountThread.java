<<<<<<< HEAD
package com.morris.ch1;

public class NoShareCountThread extends Thread {
    private int count = 5;

    public NoShareCountThread(String threadName) {
        super(threadName);
    }

    @Override
    public void run() {
        while (count > 0) {
            count--;
            System.out.println("Thread " + Thread.currentThread().getName() + " count:" + count);
        }
    }

    public static void main(String[] args) {
        NoShareCountThread a = new NoShareCountThread("A");
        NoShareCountThread b = new NoShareCountThread("B");
        NoShareCountThread c = new NoShareCountThread("C");
        a.start();
        b.start();
        c.start();
    }
}
=======
package com.morris.ch1;

public class NoShareCountThread extends Thread {
    private int count = 5;

    public NoShareCountThread(String threadName) {
        super(threadName);
    }

    @Override
    public void run() {
        while (count > 0) {
            count--;
            System.out.println("Thread " + Thread.currentThread().getName() + " count:" + count);
        }
    }

    public static void main(String[] args) {
        NoShareCountThread a = new NoShareCountThread("A");
        NoShareCountThread b = new NoShareCountThread("B");
        NoShareCountThread c = new NoShareCountThread("C");
        a.start();
        b.start();
        c.start();
    }
}
>>>>>>> 8d15f764d31a4751954af63ff5a72f44693230f4
