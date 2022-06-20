package com.jhj.oss.juc2.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * future 可以获取异步任务的属性，结果，状态
 * 三个特点： 多线程 异步 有返回值
 * 优点：1.提高执行效果
 * 缺点：1.get（阻塞）,一般放到程序最后 或者用isdone轮训（但是会浪费cpu）
 * future对于结果获取不是很友好，引出CompletableFuture
 *
 * @author Jeremy
 * @date 2022/6/20 14:39
 */
public class CallableAndFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //callable futuretask
        MyThread myThread =  new MyThread();
        FutureTask<String> futuretask =new FutureTask<>(myThread);
        Thread t1 = new Thread(futuretask, "t1");
        t1.start();
        System.out.println(futuretask.get());

        FutureTask<String> futuretask2 =new FutureTask<String>(()->{
            return "hello";
        });
    }


}


class MyThread implements Callable<String>{

    @Override
    public String call() throws Exception {
        return "hello";
    }
}
