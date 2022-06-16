package com.jhj.oss.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A执行完调用B，B执行完调用C，c执行完调用A
 * @author Jeremy
 * @date 2022/6/15 10:50
 */
public class C {
    public static void main(String[] args) {
        Data3 data3 = new Data3();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.printA();
            }
        }, "A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.printB();
            }
        }, "B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.printC();
            }
        }, "C").start();
    }
}

class Data3{
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int num =1; // 1A 2B 3C

    public void printA(){
        lock.lock();
        try {
            while (num !=1){
                //等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "->AAAAAAAAAA");
            //唤醒B
            num =2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB(){
        lock.lock();
        try {
            while (num != 2) {
                //等待
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "->BBBBBBBBB");
            //唤醒C
            num =3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printC(){
        lock.lock();
        try {
            while (num != 3) {
                //等待
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "->CCCCCCCCCCC");
            //唤醒C
            num =1;
            condition1.signal();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
