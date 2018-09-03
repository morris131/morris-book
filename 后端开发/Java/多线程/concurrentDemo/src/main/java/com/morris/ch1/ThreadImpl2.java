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
