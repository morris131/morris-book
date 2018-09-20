<<<<<<< HEAD
package com.morris.ch1;

public class ThreadImpl2 extends Thread {
    @Override
    public void run() {
        System.out.println("This thread is extend Thread class.");
    }

    public static void main(String[] args) {
        ThreadImpl2 thread = new ThreadImpl2();
        thread.start();
    }
}
=======
package com.morris.ch1;

public class ThreadImpl2 extends Thread {
    @Override
    public void run() {
        System.out.println("This thread is extend Thread class.");
    }

    public static void main(String[] args) {
        ThreadImpl2 thread = new ThreadImpl2();
        thread.start();
    }
}
>>>>>>> 8d15f764d31a4751954af63ff5a72f44693230f4
