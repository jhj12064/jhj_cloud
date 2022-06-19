package com.jhj.oss.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * cas 比较并交换
 * java无法操作内存，但是可以操作C—++ 操作内存
 */
public class CASDemo {


    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);
        //expect 期望值 update 修改值
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());
    }
}
