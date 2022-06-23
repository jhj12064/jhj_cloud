package com.jhj.oss.juc2.cas.atomic;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * 阿里手册：LongAdder 比 AtomicLong性能封号（减少乐观锁重试次数）
 * LongAdder 只能计算加分，且只能从0开始
 * LongAccumulator 提供自定义函数操作
 * @author Jeremy
 * @date 2022/6/23 9:43
 */
public class LongAdderTest {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        longAdder.increment();
        System.out.println(longAdder.sum());

        LongAccumulator longAccumulator = new LongAccumulator((x,y)->x+y, 0);
        longAccumulator.accumulate(1);//1
        longAccumulator.accumulate(3);//4
        System.out.println(longAccumulator.get());
    }
}
