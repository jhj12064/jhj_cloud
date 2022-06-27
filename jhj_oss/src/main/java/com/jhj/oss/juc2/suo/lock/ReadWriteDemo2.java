package com.jhj.oss.juc2.suo.lock;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 琐降级
 * 2注意：锁降级： 遵循获取写锁、获取读锁、释放写锁的次序， 降级为读锁
 * 如果同一个线程持有了写锁，在没有释放写锁的情况下，还可以获取读锁
 */
public class ReadWriteDemo2 {
    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock wlock = readWriteLock.writeLock();

        Lock rlock = readWriteLock.readLock();
        /*//A
        wlock.lock();
        System.out.println("xie");
        wlock.unlock();
        //B
        rlock.lock();
        System.out.println("du");
        rlock.unlock();*/

        //降级
        //A
        wlock.lock();
        System.out.println("xie");

        //B
        rlock.lock();
        System.out.println("du");
        rlock.unlock();
        wlock.unlock();
    }
}




