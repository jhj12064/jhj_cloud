package com.jhj.oss.juc2.cas.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * 阿里手册：LongAdder 比 AtomicLong性能封号（减少乐观锁重试次数）
 *
 * @author Jeremy
 * @date 2022/6/23 9:43
 */
public class LongAdderTest2 {
    static final int _1w = 10000;
    static final int threadNum = 50;

    public static void main(String[] args) throws InterruptedException {
        ClickNumber clickNumber = new ClickNumber();
        long start =System.currentTimeMillis();

        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            for (int i1 = 0; i1 < _1w ; i1++) {
                new Thread(() -> {
                    //clickNumber.click();
                    //clickNumber.clickAtomicLong();
                    clickNumber.clickLongAccumulator();
                }, "").start();
            }
            countDownLatch.countDown();

        }
        countDownLatch.await();

        long end = System.currentTimeMillis();
        System.out.println("cost time= " + (end -start) + "结果：" + clickNumber.longAccumulator);
    }
}

//结果卡死
class ClickNumber {
    //1
    int num = 0;

    public synchronized void click() {
        num++;
    }

    //2 cost time= 28963结果：499800
    AtomicLong atomicLong = new AtomicLong(0);

    public synchronized void clickAtomicLong() {
        atomicLong.getAndIncrement();
    }

    //3 cost time= 29613结果：500000
    LongAdder longAdder = new LongAdder();

    public synchronized void clickLongAdder() {
        longAdder.increment();
    }

    //4 cost time= 29554结果：500000
    LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x + y, 0);

    public synchronized void clickLongAccumulator() {
        longAccumulator.accumulate(1);
    }
}
