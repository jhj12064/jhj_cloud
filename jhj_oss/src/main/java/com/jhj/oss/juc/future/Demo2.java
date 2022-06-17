package com.jhj.oss.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Jeremy
 * @date 2022/6/17 15:58
 */
public class Demo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Object> objectCompletableFuture = new CompletableFuture<>();
        CompletableFuture<Integer> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "  run -void");
            return 500;
        });
        System.out.println("11111");
        System.out.println("22222    "+ voidCompletableFuture.get());
    }
}
