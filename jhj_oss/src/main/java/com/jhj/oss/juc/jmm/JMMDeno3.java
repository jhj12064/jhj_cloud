package com.jhj.oss.juc.jmm;

import java.util.concurrent.atomic.AtomicInteger;

public class JMMDeno3 {
    //保证可见性
    //不保证原子性？ 原子性：不可分割
    private static volatile AtomicInteger num = new AtomicInteger();

    /**
     * 方法1：synchronized lock
     */
    public  static void add() {
        num.getAndIncrement();//+1 CAS
        // num++;//不是原子型操作
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> { //不加volatile，感知不到主内存变化，死循环
                for (int j = 0; j < 1000; j++) {
                    add();

                }
            }, "A").start();
        }
        //存活线程大于2 ，有两条默认线程 main gc
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "" +num);
    }
}
