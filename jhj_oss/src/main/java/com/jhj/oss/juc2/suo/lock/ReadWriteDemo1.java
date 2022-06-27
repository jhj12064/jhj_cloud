package com.jhj.oss.juc2.suo.lock;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteDemo1 {
    public static void main(String[] args) {
        NyRsource nyRsource = new NyRsource();

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(()->{
                nyRsource.write(finalI +"", finalI +"");
            },i+"").start();
        }
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(()->{
                nyRsource.read(finalI +"");
            },i+"").start();
        }

    }
}

class NyRsource{
    //模拟缓存
    HashMap<String, String> map = new HashMap<>();
    Lock lock = new ReentrantLock();
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void write(String key, String value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+ "正在写入");
            map.put(key, value);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+ "写入成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public String read(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+ "正在读取");
            String s = map.get(key);
            TimeUnit.MICROSECONDS.sleep(200);
            System.out.println(Thread.currentThread().getName()+ "读取成功,结果：" + s);
            return s;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

