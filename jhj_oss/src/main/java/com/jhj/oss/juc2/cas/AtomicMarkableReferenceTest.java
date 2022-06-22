package com.jhj.oss.juc2.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * 类似一次性筷子
 * 作用和AtomicStampedReference差不多，但是就true 和 false 区分
 *
 * @author Jeremy
 * @date 2022/6/22 18:25
 */
public class AtomicMarkableReferenceTest {
    //值：100
    static AtomicMarkableReference<Object> reference = new AtomicMarkableReference<>(100, false);

    public static void main(String[] args) {

        new Thread(() -> {
            boolean marked = reference.isMarked();
            System.out.println(Thread.currentThread().getName()+ "默认表示u" + marked);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //4 个参数 ： 预期 新值 预期标记 新标记
            reference.compareAndSet(100, 200, marked, !marked);
        }, "t1").start();

        new Thread(() -> {
            boolean marked = reference.isMarked();
            System.out.println(Thread.currentThread().getName()+ "默认表示u" + marked);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //4 个参数 ： 预期 新值 预期标记 新标记
            System.out.println(reference.compareAndSet(100, 300, marked, !marked));
            System.out.println(reference.isMarked());
            System.out.println(reference.getReference());
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*Object reference = AtomicMarkableReferenceTest.reference.getReference();
        System.out.println(reference);*/
    }
}
