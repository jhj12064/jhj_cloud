package com.jhj.oss.juc2.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture
 *计算结果进行消费
 * 接受任务的处理结果，并消费处理，无返回结果 thenAccept
 * thenRun 任务A执行完执行B，b不需要A的结果
 *thenAccept 任务A执行完执行B，b需要A的结果,b没有返回值
 * thenApply  任务A执行完执行B，b需要A的结果 ， b有返回值
 *
 *
 */
public class CompletableFutureDemo6 {


    public static void main(String[] args) throws Exception, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
         CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "comr in ");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2秒后结果123" );
            return 1;
        }, executorService).handle((f,e)->{
            System.out.println("22");
            return f +2;
        }).thenApply((f)->{
            System.out.println("33");
            return f +2;
        }).thenAccept(r->{
            System.out.println("r" + r);
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

        //
        CompletableFuture.supplyAsync(()-> "resultA").thenRun(()->{}).join();
        CompletableFuture.supplyAsync(()-> "resultA").thenAccept((r)->{
            System.out.println(r);
        }).join();
        CompletableFuture.supplyAsync(()-> "resultA").thenApply((r)-> r +"A").join();
    }


}
