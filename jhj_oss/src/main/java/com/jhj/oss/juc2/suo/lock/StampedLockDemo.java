package com.jhj.oss.juc2.suo.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {
    static int num = 37;
    static StampedLock stampedLock = new StampedLock();
    public static void main(String[] args) throws InterruptedException {

        StampedLockDemo resource = new StampedLockDemo();
        //悲观 例子
        /*new Thread(()->{
            try {
                resource.read();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "readTHread").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            try {
                System.out.println("写进程 come in");
                resource.writ();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "write THread").start();*/
        //乐观例子
        new Thread(()->{
            try {
                resource.read2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "readTHread").start();
        //读 过程 可以写
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            try {
                System.out.println("写进程 come in");
                resource.writ2();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "write THread").start();

    }

    //乐观
    public void  writ2(){
        long l = stampedLock.writeLock();
        System.out.println(Thread.currentThread().getName() + "xie 开始");
        try {
            num = num +13;
        }finally {
            stampedLock.unlockWrite(l);
        }
        System.out.println(Thread.currentThread().getName() + "xie 结束");
    }
    //乐观
    public void read2() throws InterruptedException {
        long l = stampedLock.tryOptimisticRead();
        int result = num;
        System.out.println("4秒前戳记，（true无修改，false有修改）" +stampedLock.validate(l));
        System.out.println( Thread.currentThread().getName() + "du 开始 故意拖延4s");
        for (int i = 0; i < 4; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("读取中，第几次：" +i + " 这时候 戳记" + stampedLock.validate(l));
        }
        if (!stampedLock.validate(l)){
            //有人修改过
            System.out.println("有人修改过");
            //从乐观回复升级到悲观读
            long l2 = stampedLock.readLock();
            try {

                result = num;
                System.out.println(Thread.currentThread().getName() + " 被人修改过 result：" + result);
            } finally {
                stampedLock.unlockRead(l2);
            }
        }
        System.out.println(Thread.currentThread().getName() + " 最终结果 result：" + result);

    }
    //传统
    public void  writ(){
        long l = stampedLock.writeLock();
        System.out.println(Thread.currentThread().getName() + "xie 开始");
        try {
            num = num +13;
        }finally {
            stampedLock.unlockWrite(l);
        }
        System.out.println(Thread.currentThread().getName() + "xie 结束");
    }
    //传统
    public void read() throws InterruptedException {
        long l = stampedLock.readLock();
        System.out.println(Thread.currentThread().getName() + "du 开始");
        for (int i = 0; i < 4; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("读取中");
        }
        try {
            int result = num;
            System.out.println(Thread.currentThread().getName() + " 获得 result：" + result);
            System.out.println(" 写线程没有修改成功，读时候锁无法介入，传统读写互斥：" + result);

        }finally {
            stampedLock.unlockRead(l);
        }
    }
}
