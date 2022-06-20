package com.jhj.oss.juc2.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture
 * 获得结果
 * 4个get方法 1 get  2 get加超时  3 join  4 getNow(默认值) 5 complete 主动打断，给出一个结果
 *
 */
public class CompletableFutureDemo4 {


    public static void main(String[] args) throws Exception, InterruptedException {
       // ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "comr in ");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2秒后结果123" );
            return "123";
        });
//        stringCompletableFuture.get();
//        stringCompletableFuture.get(1, TimeUnit.SECONDS);
    //    System.out.println(stringCompletableFuture.join());
        TimeUnit.SECONDS.sleep(3);
        //System.out.println(stringCompletableFuture.getNow("默认值"));
        //主动触发计算 主动打断，给出一个结果
        System.out.println(stringCompletableFuture.complete("任务完成" )+ "\t" + stringCompletableFuture.join());

        // TimeUnit.SECONDS.sleep(3);
    }


}
