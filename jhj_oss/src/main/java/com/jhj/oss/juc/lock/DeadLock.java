package com.jhj.oss.juc.lock;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * 死锁
 */
public class DeadLock {

    public static void main(String[] args) {
        String lockA = "locakA";
        String lockB = "locakB";
        new Thread(new MYThread(lockA, lockB),"T1").start();
        new Thread(new MYThread(lockB, lockA),"T2").start();
    }
}


class MYThread implements Runnable{

    private String lockA;
    private String lockB;

    public MYThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }


    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()  + "locak:" + lockA + "get:  " +lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()  + "locak:" + lockB + "get:  " +lockA);

            }
        }
    }
}
