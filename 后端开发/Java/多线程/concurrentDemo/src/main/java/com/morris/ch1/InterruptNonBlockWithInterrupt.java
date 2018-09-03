package com.morris.ch1;

public class InterruptNonBlockWithInterrupt extends Thread {

    @Override
    public void run() {
        while(!Thread.currentThread().interrupted()) {
            long beginTime = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " is running");
            // 当前线程每隔一秒钟检测一次线程共享变量是否得到通知
            while (System.currentTimeMillis() - beginTime < 1000) {}
        }
        if (!Thread.currentThread().interrupted()) {
            System.out.println(Thread.currentThread().getName() + " is interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptNonBlockWithInterrupt thread = new InterruptNonBlockWithInterrupt();
        thread.start();

        Thread.sleep(5000);
        thread.interrupt();

    }
}
