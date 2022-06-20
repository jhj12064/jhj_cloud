package com.jhj.oss.juc2.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture
 * 对计算结果进行处理
 * thenapply 对结果进行计算或处理，有异常叫停
 * handle 有异常可以走下一步
 *
 *
 */
public class CompletableFutureDemo5 {


    public static void main(String[] args) throws Exception, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture<Integer> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "comr in ");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2秒后结果123" );
            return 1;
        }, executorService).handle((f,e)->{
            int i = 10/0;
            System.out.println("22");
            return f +2;
        }).thenApply((f)->{
            System.out.println("33");
            return f +2;
        }).whenComplete((v,e)->{
            if (e == null) {
                System.out.println("计算结果：" + v);
            }else {
                System.out.println("报错啦， 结果" + v);
            }

        }).exceptionally(e->{
            e.printStackTrace();
            System.out.println(e.getMessage());;
            return null;
        });
        System.out.println("main");
        executorService.shutdown();
    }


}
