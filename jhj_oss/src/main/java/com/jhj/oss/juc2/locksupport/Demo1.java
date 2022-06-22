package com.jhj.oss.juc2.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 3种唤醒机制
 */
public class Demo1 {

    public static void main(String[] args) throws InterruptedException {
        lockSupportAndWait();
    }

    public static void lockSupportAndWait() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "被唤醒");
        }, "t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"come in");
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName()+"发出通知");
        },"t2").start();

    }


    public static void JucAndWait() {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(()->{
            lock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }).start();


        new Thread(()->{
            lock.lock();
            try {
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }).start();
    }

    public static void synchronizedAndWait() {
        Object o = new Object();
        new Thread(()->{
            synchronized (o){
                try {
                    o.wait();//等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(()->{
            synchronized (o){
                try {
                    o.notify();//唤醒
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
