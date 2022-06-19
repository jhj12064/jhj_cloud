package com.jhj.oss.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ABA问题
 * 只注重头和尾的一致，只要首尾一致就接受。但是有的需求，还看重过程，中间不能发生任何修改，这就引出了AtomicReference原子引用。
 * 解决：加版本号
 */
public class CASDemo2 {


    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);
        //expect 期望值 update 修改值
        //捣乱的线程
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(2021, 2020));
        System.out.println(atomicInteger.get());
        //期望的线程
        System.out.println(atomicInteger.compareAndSet(2020, 6666));
        System.out.println(atomicInteger.get());
    }
}
