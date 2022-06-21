package com.jhj.oss.juc2.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 中断三种方法
 * 3. 通过Thread自带的api实现方法实现
 * @author Jeremy
 * @date 2022/6/21 14:44
 */
public class InterruptDemo3 {


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                //判断是否被中断
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "isInterrupted==true.程序停止");
                    break;
                }
                System.out.println("t1 -----hello isInterrupted treue");
            }
        }, "t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            t1.interrupt();
        }, "t2").start();
    }
}
