package com.jhj.oss.juc.lock;

/**
 * @author Jeremy
 * @date 2022/6/13 16:00
 */
public class NewNotifyTest {

    int num =1;

    public synchronized void increment() throws Exception{
        while(num !=0){
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName() +"->" +num);
        this.notifyAll();
    }

    public synchronized void decrement() throws Exception{
        while(num ==0){
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() +"->" +num);
        this.notifyAll();
    }

    public static void main(String[] args) {
        NewNotifyTest notifyTest = new NewNotifyTest();
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
