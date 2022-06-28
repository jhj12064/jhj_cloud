package com.jhj.oss.juc2.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture
  结果合并 两个任务都完成后，最终把结果一起交给thenCombine处理
 *
 */
public class CompletableFutureDemo9 {


    public static void main(String[] args) throws Exception, InterruptedException {
        Thread thread = new Thread();
       // Thread.State;
        thread.run();
        //ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "1 号任务 comr in ");
            try {
                TimeUnit.MICROSECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2秒后结果123");
            return 10;
        });

        CompletableFuture<Integer> integerCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "1 号任务 comr in ");
            try {
                TimeUnit.MICROSECONDS.sleep(550);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2秒后结果123");
            return 20;
        });

        CompletableFuture<Integer> aa = integerCompletableFuture.thenCombine(integerCompletableFuture2, (x, y) -> {
            System.out.println("合并");
            return x + y;
        });
        System.out.println(aa.join());

    }


}
