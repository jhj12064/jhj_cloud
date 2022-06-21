package com.jhj.oss.juc2.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * 中断三种方法
 * 1.通过 volatile 中断程序
 * @author Jeremy
 * @date 2022/6/21 14:44
 */
public class InterruptDemo {

    //标志位
    static volatile  boolean isstop = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (true){
                if (isstop){
                    System.out.println(Thread.currentThread().getName() + "isstop==true.程序停止");
                    break;
                }
                System.out.println("t1 -----hello volatile");
            }
        }, "t1").start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(()->{
            isstop = true;
        }, "t2").start();
    }
}
