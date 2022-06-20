package com.jhj.oss.juc2.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture 核心4个方法
 * 如何减少阻塞，轮询
 * @author Jeremy
 * @date 2022/6/20 16:34
 */
public class CompletableFutureDemo2 {


    public static void main(String[] args) throws Exception, InterruptedException {

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "comr in ");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2秒后结果123" );
            return "123";
        }).whenComplete((v, e)->{
            if(e ==null){
                System.out.println("计算完成" + v);
            }
        }).exceptionally(e->{
            e.printStackTrace();
            System.out.println("出错");
            return null;
        });

        System.out.println(Thread.currentThread().getName() + ":main");
        TimeUnit.SECONDS.sleep(3);
    }

    public void test1() throws Exception, InterruptedException {

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "comr in ");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "123";
        });

        System.out.println(stringCompletableFuture.get());
        System.out.println(Thread.currentThread().getName() + ":main");

        TimeUnit.SECONDS.sleep(3);
    }
}
