package com.jhj.oss.juc2.cas.atomic;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 对象属性修改原子类
 * @author Jeremy
 * @date 2022/6/23 9:18
 */
public class AtomicReferenceFieldUpdaterTest {


    public static void main(String[] args) {
        Myvar myvar = new Myvar();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    myvar.init(myvar);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, i+"").start();
        }
    }
}


/**
 * 资源类
 */
class Myvar{
    public volatile Boolean isInit = Boolean.FALSE;
    AtomicReferenceFieldUpdater<Myvar, Boolean> updater =
    AtomicReferenceFieldUpdater.newUpdater(Myvar.class, Boolean.class, "isInit");


    public void init(Myvar myvar) throws InterruptedException {
        boolean b = updater.compareAndSet(myvar, Boolean.FALSE, Boolean.TRUE);
        if(b){
            System.out.println(Thread.currentThread().getName() + " begin");
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName() + " oveer");
        }else {
            System.out.println(Thread.currentThread().getName() + " 已经有线程初始化");
        }
    }
}
