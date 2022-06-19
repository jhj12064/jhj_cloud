package com.jhj.oss.juc.jmm;

import java.util.concurrent.TimeUnit;

public class JMMDeno {
    //保证可见性
    private static volatile int  num = 0;
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{ //不加volatile，感知不到主内存变化，死循环
            while (num==0){

            }
        },"A").start();
        TimeUnit.SECONDS.sleep(2);
        num =1;
        System.out.println(num);
    }
}
