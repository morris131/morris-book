package com.morris.java8.future;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author chenlian
 * @date 2019/9/24 15:40
 */
public class CustomFutureExample {

    public static void main(String[] args) throws InterruptedException {

        Callable<String> callable = new Callable<String>() {
            AtomicReference<String> atomicReference = new AtomicReference<>();
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            @Override
            public Future<String> action() {


                Thread t = new Thread(() -> {

                    try {
                        TimeUnit.SECONDS.sleep(10);
                        atomicReference.set("Finished");
                        atomicBoolean.set(true);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        atomicReference.set("Error");
                        atomicBoolean.set(true);
                    }

                });
                t.start();

                Future<String> future = () -> atomicReference.get();

                return future;
            }

            @Override
            public boolean isDone() {
                return atomicBoolean.get();
            }
        };

        Future<String> action = callable.action();

        System.out.println(action.get());
        System.out.println(action.get());
        System.out.println(action.get());

        while(!callable.isDone()) {
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println(action.get());

    }

    private interface Future<T> {
        T get();
    }


    private interface Callable<T> {
        Future<T> action();

        boolean isDone();
    }
}

