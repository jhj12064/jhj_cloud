package com.jhj.oss.juc2.cas.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 数组类型原子类

 * @author Jeremy
 * @date 2022/6/22 18:25
 */
public class AtomicArrayTest {
    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(3);
        atomicIntegerArray.set(0,0);
        atomicIntegerArray.set(1,1);
        atomicIntegerArray.set(1,2);
        System.out.println(atomicIntegerArray);
        AtomicIntegerArray atomicIntegerArray2 = new AtomicIntegerArray(new int[]{1,2});
        for (int i = 0; i < atomicIntegerArray2.length(); i++) {
            System.out.println(atomicIntegerArray2.get(i));
        }
    }
}
