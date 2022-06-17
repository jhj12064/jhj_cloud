package com.jhj.oss.juc.pool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用线程池创建线程
 * Executors 工具类 3大方法 阿里巴巴不推荐使用
 *
 *
 *
 * public ThreadPoolExecutor(int corePoolSize,  核心线程池大小
 *                               int maximumPoolSize,  最大线程池大小
 *                               long keepAliveTime,  超时不调用自动释放
 *                               TimeUnit unit,  超时单位
 *                               BlockingQueue<Runnable> workQueue,  阻塞队列
 *                               ThreadFactory threadFactory,   线程工厂
 *                               RejectedExecutionHandler handler) { 拒绝策略
 * @author Jeremy
 * @date 2022/6/17 9:20
 */
public class Demo1 {
    public static void main(String[] args) {
        //Executors 工具类 3大方法 阿里巴巴不推荐使用
        ExecutorService pool = null;//单个线程
        try {
            pool = Executors.newSingleThreadExecutor();
            pool = Executors.newFixedThreadPool(5);//创建一个固定线程池大小
            pool= Executors.newCachedThreadPool();//可伸缩
            for (int i = 0; i < 10; i++) {
                pool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭线程池
            pool.shutdown();
        }

    }
}
