package com.jhj.oss.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 加法计数器
 * @author Jeremy
 * @date 2022/6/15 15:54
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        //集齐7个龙珠

        CyclicBarrier cyclicBarrier = new CyclicBarrier(8, () -> {
            System.out.println("召唤神龙");
        });
        for (int i = 1; i <=7; i++) {
            final int finalI = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() +"收集："+finalI);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },"").start();
        }
    }
}
