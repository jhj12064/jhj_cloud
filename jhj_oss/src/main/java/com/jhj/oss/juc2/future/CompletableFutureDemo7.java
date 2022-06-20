package com.jhj.oss.juc2.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture
  和线程池说明
 *TODO 听不太懂
 *
 */
public class CompletableFutureDemo7 {


    public static void main(String[] args) throws Exception, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "1 号任务 comr in ");
            try {
                TimeUnit.MICROSECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2秒后结果123");
            return 1;
        }, executorService).thenRunAsync(() -> {
            try {
                TimeUnit.MICROSECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "2 号任务 comr in ");
        }).thenRunAsync(() -> {
            try {
                TimeUnit.MICROSECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "3号任务 comr in ");
        }).thenRunAsync(() -> {
            try {
                TimeUnit.MICROSECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "4 号任务 comr in ");
        });
        System.out.println(voidCompletableFuture.get(2, TimeUnit.SECONDS));
        System.out.println("main");
        executorService.shutdown();


    }


}
