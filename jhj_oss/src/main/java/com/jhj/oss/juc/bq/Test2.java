package com.jhj.oss.juc.bq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列
 * SynchronousQueue 不存储元素
 *
 * @author Jeremy
 * @date 2022/6/16 15:00
 */
public class Test2 {
    public static void main(String[] args) throws InterruptedException {

        SynchronousQueue<Object> queue = new SynchronousQueue<>();
        new Thread(()->{

            try {
                System.out.println(Thread.currentThread().getName() +"put 1");
                queue.put("1");
                System.out.println(Thread.currentThread().getName() +"put 2");
                queue.put("1");
                System.out.println(Thread.currentThread().getName() +"put 2");
                queue.put("2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() +"take 1" + queue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() +"take 1" + queue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() +"take 1" + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
    }



}
