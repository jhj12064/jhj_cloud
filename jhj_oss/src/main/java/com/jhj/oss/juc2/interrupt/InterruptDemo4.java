package com.jhj.oss.juc2.interrupt;

import java.util.concurrent.TimeUnit;

/**

 * @author Jeremy
 * @date 2022/6/21 14:44
 */
public class InterruptDemo4 {


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                //判断是否被中断
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "isInterrupted==true.程序停止");
                    break;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();//为什么需要再调用一次，java.lang.InterruptedException: sleep interrupted 会清空中断标记
                    e.printStackTrace();
                }
                System.out.println("hello");
            }
        }, "t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            t1.interrupt();
        }, "t2").start();
    }
}
