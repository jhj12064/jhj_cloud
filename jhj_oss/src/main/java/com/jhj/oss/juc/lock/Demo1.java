package com.jhj.oss.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * 可重入锁
 */
public class Demo1 {
    public static void main(String[] args) throws Exception{
        Phone phone = new Phone();
        new Thread(()->{
            try {
                phone.sms();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread(()->{
            try {
                phone.sms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}

class Phone{
    public  synchronized  void sms() throws InterruptedException {

        System.out.println(Thread.currentThread().getName() +":短信");
        call(); //这里也有所
    }

    public  synchronized  void call(){
        System.out.println(Thread.currentThread().getName() +":电话");
    }
}
