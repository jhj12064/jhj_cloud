package com.jhj.oss.juc2.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class AQSDemo {
    public static void main(String[] args) throws Exception{
        Lock lock = new ReentrantLock(false);
        //A办理需要20秒
        new Thread(()->{
            lock.lock();
            System.out.println("A come in");
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }, "A").start();
        //B是第二个顾客，进入AQS等待队列
        new Thread(()->{
            lock.lock();
            System.out.println("B come in");
            try {
                //TimeUnit.SECONDS.sleep(20);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }, "B").start();
        //C是第二个顾客，进入AQS等待队列
        new Thread(()->{
            lock.lock();
            System.out.println("C come in");
            try {
              //  TimeUnit.SECONDS.sleep(20);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }, "C").start();
    }
}
