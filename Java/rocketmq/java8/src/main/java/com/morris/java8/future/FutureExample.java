package com.morris.java8.future;

import java.util.concurrent.*;

public class FutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                return "Finished";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "Error";
            }
        });

        System.out.println("do other thing.");

        System.out.println(future.get());

        executorService.shutdown();


    }
}
