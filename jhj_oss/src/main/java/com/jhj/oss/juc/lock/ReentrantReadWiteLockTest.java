package com.jhj.oss.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁，可以被多个线程读，只能一个线程写
 * @author Jeremy
 * @date 2022/6/16 14:06
 */
public class ReentrantReadWiteLockTest {

    public static void main(String[] args) {
        MyCache2 myCache = new MyCache2();
        //写
        for (int i = 1; i <= 5; i++) {
            final int fi = i;
            new Thread(()->{
                myCache.put(fi+"", fi);
            },fi+"").start();
        }
        //读
        for (int i = 1; i <= 5; i++) {
            final int fi = i;
            new Thread(()->{
                myCache.get(fi+"");
            },fi+"").start();
        }
    }
}

//自定义缓存
class MyCache{
    private volatile Map<String, Object> map = new HashMap<>();

    public void put(String key, Object value){
        System.out.println(Thread.currentThread().getName() + "写---->key开始" + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "写---->key结束" + key);
    }

    public void get(String key){
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读---->key:" + key + "---------value:"+ o);
    }
}

//自定义缓存 加锁
class MyCache2{
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, Object value){
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + "写---->key开始" + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "写---->key结束" + key);
        lock.writeLock().unlock();
    }

    public void get(String key){
        lock.readLock().lock();

        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName() + "读---->key:" + key + "---------value:"+ o);
        lock.readLock().unlock();
    }
}