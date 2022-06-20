package com.jhj.oss.juc2.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture
  计算速度 applyToEither 比较两个线程谁快
 *
 */
public class CompletableFutureDemo8 {


    public static void main(String[] args) throws Exception, InterruptedException {
        //ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "1 号任务 comr in ");
            try {
                TimeUnit.MICROSECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2秒后结果123");
            return 1;
        });

        CompletableFuture<Integer> integerCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "1 号任务 comr in ");
            try {
                TimeUnit.MICROSECONDS.sleep(550);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2秒后结果123");
            return 4;
        });

        //TimeUnit.SECONDS.sleep(2);

        CompletableFuture<String> stringCompletableFuture = integerCompletableFuture.applyToEither(integerCompletableFuture2, f -> {
            return f + " win";
        });

        System.out.println(stringCompletableFuture.join());

    }


}
