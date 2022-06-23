package com.jhj.oss.juc2.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Jeremy
 * @date 2022/6/23 11:56
 */
public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        try {
            for (int i = 0; i < 10; i++) {
                pool.submit(() -> {
                    System.out.println("1");
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
             //TimeUnit.SECONDS.sleep(2);
            pool.shutdown();
        }
    }
}
