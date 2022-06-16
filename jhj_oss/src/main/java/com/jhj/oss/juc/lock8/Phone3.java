package com.jhj.oss.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 1.标准情况下，两个线程 是先发短信还是先打电话
 * 2.增加两个静态方法，静态的方法属于类，锁的class
 * @author Jeremy
 * @date 2022/6/15 11:17
 */
public class Phone3 {

    public static void main(String[] args) throws Exception {
        Phone3 phone = new Phone3();
        Phone3 phone2 = new Phone3();
        new Thread(()->{
            try {
                sms();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            call();
        }, "B").start();
    }

    // synchronized锁的对象是方法的调用者
    //两个方法用的同一把锁，谁先拿到谁先执行
    public static   synchronized  void sms() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("短信");
    }

    public static  synchronized  void call(){
        System.out.println("电话");
    }

    public    void hello(){
        System.out.println("hello");
    }
}
