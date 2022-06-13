package com.jhj.oss.juc;

/**
 * @author Jeremy
 * @date 2022/6/13 16:00
 */
public class NotifyTest {

    int num =1;

    public synchronized void increment() throws Exception{
        if(num !=0){
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName() +"->" +num);
        this.notifyAll();
    }
}
