package com.jhj.oss.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 解决ABA问题
 * 只注重头和尾的一致，只要首尾一致就接受。但是有的需求，还看重过程，中间不能发生任何修改，这就引出了AtomicReference原子引用。
 * 解决：加版本号
 */
public class CASDemo3 {


    public static void main(String[] args) {
        //AtomicInteger atomicInteger = new AtomicInteger(2020);
        AtomicStampedReference<Integer> integerAtomicStampedReference = new AtomicStampedReference<>(1, 1);
        new Thread(()->{
            int stamp = integerAtomicStampedReference.getStamp();
            System.out.println("A1------ stamp" + stamp);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A1------ 计算" +integerAtomicStampedReference.compareAndSet(1, 2, integerAtomicStampedReference.getStamp(), integerAtomicStampedReference.getStamp() + 1));
            System.out.println("A2------ stamp" + integerAtomicStampedReference.getStamp());
            System.out.println("A2------ 计算" +integerAtomicStampedReference.compareAndSet(2, 1, integerAtomicStampedReference.getStamp(), integerAtomicStampedReference.getStamp() + 1));
            System.out.println("A3-----stamp " + integerAtomicStampedReference.getStamp());

        },"A").start();
        new Thread(()->{
            int stamp = integerAtomicStampedReference.getStamp();
            System.out.println("B1------stamp " + stamp);
            System.out.println("B1------计算 " + integerAtomicStampedReference.compareAndSet(1, 3, integerAtomicStampedReference.getStamp(), integerAtomicStampedReference.getStamp() + 1));
            System.out.println("B2------stamp " + integerAtomicStampedReference.getStamp());

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}
