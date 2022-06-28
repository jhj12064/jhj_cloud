package com.jhj.oss.juc.pool;

import java.util.concurrent.*;

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
 *                               RejectedExecutionHandler handler) { 拒绝策略 4种
 * @author Jeremy
 * @date 2022/6/17 9:20
 */
public class Demo2 {
    public static void main(String[] args) {
        /**
         * 最大线程数该如何定义
         * 1.cpu密集型，查看cpu核数，切换线程需要时间m，应用于复杂算法 Runtime.getRuntime().availableProcessors()
         * 所以可以设置最大核数+1，原因：当cpu密集型线程犹豫故障暂停时，这个额外的线程能确保时钟周期不被浪费，备份的线程
         * 2.io密集型 判断程序中十分耗io的线程
         * 执行任务需要大量io，比如网络，磁盘io，对cpu消耗少。可以设置 cpu最大核数*2
         */
        //cpu核数
        System.out.println(Runtime.getRuntime().availableProcessors());

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, //核心线程池大小
                5, //最大线程池大小
                3, //超时不调用自动释放
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3), // LinkedBlockingDeque 无界队列 ArrayBlockingQuene 有界队列
                Executors.defaultThreadFactory(),
                //new ThreadPoolExecutor.AbortPolicy() //银行满了，还有人进来，抛出异常
//                new ThreadPoolExecutor.CallerRunsPolicy() //哪来的回哪里 让main线程自己执行
             //   new ThreadPoolExecutor.DiscardPolicy() //满了，丢掉任务，不抛出异常
        new ThreadPoolExecutor.DiscardOldestPolicy() //满了，尝试竞争，竞争失败依旧丢掉任务
        );
        try {

            for (int i = 0; i < 10; i++) {
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭线程池
            threadPoolExecutor.shutdown();
        }

    }
}
