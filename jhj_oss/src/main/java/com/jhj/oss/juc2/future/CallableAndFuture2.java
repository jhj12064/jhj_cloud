package com.jhj.oss.juc2.future;

import java.util.concurrent.*;

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
public class CallableAndFuture2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        //线程池
        ExecutorService pool = Executors.newFixedThreadPool(3);
        FutureTask<String> futuretask2 =new FutureTask<String>(()->{
            return "task1";
        });
        pool.submit(futuretask2);
        System.out.println(futuretask2.get());
        System.out.println(futuretask2.get(3, TimeUnit.SECONDS));//3秒拿不到结果就报错
        //3
        while (true){
            if(futuretask2.isDone()){//是否已经完成
                System.out.println(futuretask2.get());
                break;
            }
        }
        pool.shutdown();
    }


}



