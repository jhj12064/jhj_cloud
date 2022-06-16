package com.jhj.oss.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Jeremy
 * @date 2022/6/13 14:26
 */
public class LockTest {
    public static void main(String[] args) {
        Ticket2 ticket2 = new Ticket2();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket2.sale();
            }
        }, "A").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket2.sale();
            }
        }, "b").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket2.sale();
            }
        }, "c").start();
    }
}

/**
 * 公平锁：先来后到
 * 非公平锁：可以插队(默认)
 * syncronized和lock区别
 */
class Ticket2{
    private int num = 30;

      Lock l =  new ReentrantLock();
    public void sale(){
        l.lock();
        try {

            l.tryLock();//尝试获取锁
            if(num>0){
                System.out.println(Thread.currentThread().getName() + (num--)+ "剩余" + num);
            }
        }catch (Exception e){

        }finally {
            l.unlock();
        }

    }
}