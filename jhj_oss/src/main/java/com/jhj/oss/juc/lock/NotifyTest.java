package com.jhj.oss.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者 消费者
 * @author Jeremy
 * @date 2022/6/13 16:00
 */
public class NotifyTest {

    int num =1;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public  void increment() throws Exception{
        lock.lock();
        try {
            while(num !=0){
                //等待
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() +"->" +num);
            //唤醒全部
            condition.signalAll();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }

    }

    public  void decrement() throws Exception{
        lock.lock();
        try {
            while(num ==0){
                //等待
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() +"->" +num);
            //唤醒全部
            condition.signalAll();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        NotifyTest notifyTest = new NotifyTest();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    notifyTest.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    notifyTest.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    notifyTest.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    notifyTest.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
