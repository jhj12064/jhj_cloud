package com.jhj.oss.juc2.suo.sync;

/**
 * 锁粗化
 */
public class LockBigDemo {
    static Object lock = new Object();

   

    public static void main(String[] args) {
        LockBigDemo lockClearUpDemo = new LockBigDemo();
        new Thread(() -> {
            synchronized (lock) {
                System.out.println(1);
            }
            synchronized (lock) {
                System.out.println(2);
            }
        }, "a").start();
        //编译器会优化成如下
        new Thread(() -> {
            synchronized (lock) {
                System.out.println(1);
                System.out.println(2);
            }
        }, "b").start();
    }
}
