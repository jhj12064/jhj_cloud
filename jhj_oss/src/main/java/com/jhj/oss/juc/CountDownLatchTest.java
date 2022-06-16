package com.jhj.oss.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 计数器
 * @author Jeremy
 * @date 2022/6/15 15:43
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "go out");
                countDownLatch.countDown();//数量-1
            }, i + "").start();
        }
        countDownLatch.await();//等待计数器归零
        System.out.println("关门");

    }
}
