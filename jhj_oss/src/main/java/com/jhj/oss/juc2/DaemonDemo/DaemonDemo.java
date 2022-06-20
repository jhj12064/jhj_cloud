package com.jhj.oss.juc2.DaemonDemo;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程
 *
 * 并发（concurrent）：一个处理器多个任务
 * 并行(parallel)：多台处理器多个任务
 * 进程：一个程序
 * 线程：调度基本单元
 * 管程：锁
 * 用户线程：系统工作线程
 * 守护线程：为其他线程服务，比如垃圾回收线程 isDaemon 是否守护
 * 如果用户线程全部结束了，守护线程也会自己结束
 *
 * @author Jeremy
 * @date 2022/6/20 14:31
 */
public class DaemonDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread a=new Thread(()->{
            System.out.println(Thread.currentThread() +"开始运行" +
                    Thread.currentThread().isDaemon()); //是否守护线程
        }, "A");
        a.setDaemon(true);
        a.start();

        TimeUnit.SECONDS.sleep(2);

        System.out.println(Thread.currentThread() +"main开始运行" +
                Thread.currentThread().isDaemon()); //是否守护线程
    }
}
