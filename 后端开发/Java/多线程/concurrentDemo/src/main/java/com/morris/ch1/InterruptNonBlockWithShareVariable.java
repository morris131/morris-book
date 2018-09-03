package com.morris.ch1;

public class InterruptNonBlockWithShareVariable extends Thread {

    // 设置线程共享变量
    volatile boolean isStop = false;

    @Override
    public void run() {
        while(!isStop) {
            long beginTime = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " is running");
            // 当前线程每隔一秒钟检测一次线程共享变量是否得到通知
            while (System.currentTimeMillis() - beginTime < 1000) {}
        }
        if (isStop) {
            System.out.println(Thread.currentThread().getName() + " is interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptNonBlockWithShareVariable thread = new InterruptNonBlockWithShareVariable();
        thread.start();

        Thread.sleep(5000);
        thread.isStop = true;

    }
}
