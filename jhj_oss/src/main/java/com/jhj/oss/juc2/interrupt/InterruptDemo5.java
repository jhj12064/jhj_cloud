package com.jhj.oss.juc2.interrupt;

import java.util.concurrent.TimeUnit;

/**
 interrupted 测试当前线程是否被中断。返回中断状态，并清除当前中断状态（false）
 * @author Jeremy

 */
public class InterruptDemo5 {


    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println("-----1");
        Thread.currentThread().interrupt();//设置中断标记
        System.out.println("-----2");
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        Thread.interrupted();//静态
        Thread.currentThread().interrupt();
    }
}
