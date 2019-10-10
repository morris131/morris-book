package com.morris.java8.lamdba;

public class RunnableExample {

    public static void main(String[] args) {

        // 匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is running.");
            }
        }).start();

        // lamdba
        new Thread(() -> {
           System.out.println(Thread.currentThread().getName() + " is running.");
        }).start();

        // 进一步简化
        new Thread(() -> System.out.println(Thread.currentThread().getName() + " is running.")).start();

    }

}