package com.jhj.oss.juc2.cas.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 对象属性修改原子类
 * 原子更新对象中的int类型字段

 * @author Jeremy
 * @date 2022/6/22 18:25
 */
public class AtomicIntegerFieldUpdaterTest {
    public static void main(String[] args) throws InterruptedException {
        Before2 before = new Before2();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    for (int i1 = 0; i1 < 1000; i1++) {
                        //before.add();
                        before.add();
                        //before.add(before);
                    }
                } finally {
                    countDownLatch.countDown();
                }

            }, i+"").start();
        }
        countDownLatch.await();
        System.out.println("result:" + before.money);
    }
}

/**
 * 之前的做法
 */
class Before {
    //不变的信息
    String bankName = "zs";
    //变化的信息
    int money = 0;
    public synchronized  void add(){
        money++;
    }
}

class Before2 {
    //不变的信息
    String bankName = "zs";
    //变化的信息
    AtomicInteger money = new AtomicInteger(0);
    public synchronized  void add(){
        money.getAndIncrement();
    }
}

class After {
    //不变的信息
    String bankName = "zs";
    //变化的信息
   public volatile int money = 0;
    /*public synchronized  void add(){
        money++;
    }*/
    AtomicIntegerFieldUpdater<After> updater =
            AtomicIntegerFieldUpdater.newUpdater(After.class, "money");
    //不加 sync 保证原子性
    public void add(After a){
        updater.getAndIncrement(a);
    }
}
