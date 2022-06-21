package com.jhj.oss.juc2.suo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平和非公平
 * @author Jeremy
 * @date 2022/6/21 10:10
 */
public class ReentranlockDemo1 {
    private  int num =50;
    Lock lock =new ReentrantLock(true);
    public static void main(String[] args) {
        ReentranlockDemo1 reentranlockDemo1 = new ReentranlockDemo1();
        new Thread(()->{
            for (int i = 0; i < 50; i++) {
                reentranlockDemo1.sale();
            }
        }, "a").start();
        new Thread(()->{
            for (int i = 0; i < 50; i++) {
                reentranlockDemo1.sale();
            }
        }, "b").start();
        new Thread(()->{
            for (int i = 0; i < 50; i++) {
                reentranlockDemo1.sale();
            }
        }, "c").start();
    }

    public void sale(){
        lock.lock();
        try {
            if (num >0){
                num--;
                System.out.println(Thread.currentThread().getName() + "  num：" + num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
