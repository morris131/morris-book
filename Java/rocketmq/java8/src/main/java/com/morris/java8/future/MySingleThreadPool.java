package com.morris.java8.future;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MySingleThreadPool {

    private BlockingQueue<Runnable> blockingQueue;

    private Thread thread;

    public MySingleThreadPool(int queueSize) {
        blockingQueue = new ArrayBlockingQueue<>(queueSize);

        thread = new Thread(()->{
            while (isRunning || !blockingQueue.isEmpty()) {
                try {
                    Runnable r;
                    if(isRunning) {
                        r = blockingQueue.take();
                    } else {
                        r = blockingQueue.poll();
                    }
                    System.out.println("获取到一个任务");

                    Optional.ofNullable(r).ifPresent(t->t.run());
                    System.out.println("处理完一个任务");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public boolean submit(Runnable r) {
        if(isRunning) {
            return blockingQueue.offer(r);
        }
        return false;
    }

    private volatile boolean isRunning = true;

    public void shutdown() {
        isRunning = false;
    }

    public static void main(String[] args) {

        MySingleThreadPool mySingleThreadPool = new MySingleThreadPool(3);

        for(int i = 0; i < 5; i++) {
            Runnable r = ()-> {
                System.out.println("我是一个任务");
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };

            mySingleThreadPool.submit(r);

        }
        mySingleThreadPool.shutdown();
    }

}
