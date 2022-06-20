package com.jhj.oss.juc2.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CompletableFuture 核心4个方法
 * @author Jeremy
 * @date 2022/6/20 16:34
 */
public class CompletableFutureDemo1 {

    public static void main(String[] args) throws Exception, InterruptedException {
       // CompletableFuture completableFuture = new CompletableFuture();//不推荐
        //1
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
        });
        System.out.println(voidCompletableFuture.get());
        //2
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture<Void> voidCompletableFuture2 = CompletableFuture.runAsync(() -> {
        }, executorService);
        System.out.println(voidCompletableFuture2.get());
        //3
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return "123";
        });
        System.out.println(stringCompletableFuture.get());
        //4
        CompletableFuture<String> stringCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
            return "123";
        }, executorService);
        System.out.println(stringCompletableFuture2.get());
    }
}
