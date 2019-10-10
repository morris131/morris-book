package com.morris.java8.future;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.*;

public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        CountDownLatch countDownLatch = new CountDownLatch(1);

        CompletableFuture.supplyAsync(CompletableFutureExample::get, executorService)
                .whenComplete((x, y)-> {
                    Optional.ofNullable(x).ifPresent(System.out::println);
                    Optional.ofNullable(y).ifPresent(System.out::println);
                    countDownLatch.countDown();
                });

        countDownLatch.await();
        executorService.shutdown();

    }

    public static Double get() {

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("------------");

        return new Random().nextDouble();
    }
}
