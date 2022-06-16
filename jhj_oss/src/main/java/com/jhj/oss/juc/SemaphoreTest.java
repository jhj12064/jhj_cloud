package com.jhj.oss.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 加法计数器
 * @author Jeremy
 * @date 2022/6/15 15:54
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        //线程数量：停车位
        Semaphore semaphore = new Semaphore(3);


        for (int i = 1; i <=6; i++) {
            final int finalI = i;
            new Thread(()->{
                try {
                    //获取线程
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() +"抢到车位："+finalI);
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() +"离开车位："+finalI);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放
                    semaphore.release();
                }


            },"").start();
        }
    }
}
