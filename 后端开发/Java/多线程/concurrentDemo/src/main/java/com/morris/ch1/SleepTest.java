package com.morris.ch1;

public class SleepTest extends Thread {

    @Override
    public void run() {
        System.out.println("run beigin:" + System.currentTimeMillis());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("run end:" + System.currentTimeMillis());
    }

    public static void main(String[] args) {
        SleepTest thread = new SleepTest();
        thread.start();
    }
}
