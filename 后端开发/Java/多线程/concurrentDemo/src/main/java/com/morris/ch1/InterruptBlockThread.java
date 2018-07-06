package com.morris.ch1;

public class InterruptBlockThread extends Thread {

    @Override
    public void run() {
        // 这里调用的是非清除中断标志位的isInterrupted方法
        while(!Thread.currentThread().interrupted()) {
            System.out.println(Thread.currentThread().getName() + " is running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // 由于调用sleep()方法清除状态标志位 所以这里需要再次重置中断标志位 否则线程会继续运行下去
                Thread.currentThread().interrupt();
            }
        }
        if (!Thread.currentThread().interrupted()) {
            System.out.println(Thread.currentThread().getName() + " is interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptBlockThread thread = new InterruptBlockThread();
        thread.start();

        Thread.sleep(5000);
        thread.interrupt();

    }
}
