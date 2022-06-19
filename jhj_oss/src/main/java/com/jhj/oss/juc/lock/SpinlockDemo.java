package com.jhj.oss.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 自旋锁
 */
public class SpinlockDemo {
    AtomicReference<Thread> objectAtomicStampedReference = new AtomicReference<>();

    //加锁
    public void mylock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "----> mylock");
        while (!objectAtomicStampedReference.compareAndSet(null, thread)){

        }
    }
    //解锁
    public void mylUnock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "----> mylUnock");
        objectAtomicStampedReference.compareAndSet(thread, null);
    }
    public static void main(String[] args) throws InterruptedException {
        //底层使用的自旋锁
        SpinlockDemo spinlockDemo = new SpinlockDemo();

        new Thread(()->{
            try {
                spinlockDemo.mylock();
                System.out.println("A");
                TimeUnit.SECONDS.sleep(10);
                spinlockDemo.mylUnock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            try {
                spinlockDemo.mylock();
                System.out.println("B");
                spinlockDemo.mylUnock();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}
