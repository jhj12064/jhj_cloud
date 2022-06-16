package com.jhj.oss.juc.bq;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Jeremy
 * @date 2022/6/16 15:00
 */
public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        Test1 test1 = new Test1();
        test1.test3();
    }


    public void test1(){
        //队列设置大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.add("a"));//添加
        System.out.println(arrayBlockingQueue.add("b"));
        System.out.println(arrayBlockingQueue.add("c"));

        System.out.println(arrayBlockingQueue.element());//队列首部
        System.out.println(arrayBlockingQueue.peek());//检测第一个元素
        System.out.println(arrayBlockingQueue.remove());//移除
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
    }

    public void test2(){
        //队列设置大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.offer("a"));//添加
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("c"));
        System.out.println(arrayBlockingQueue.offer("d"));//不会抛出异常



        System.out.println(arrayBlockingQueue.poll());//移除
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());//不会抛出异常
    }


    public void test3() throws InterruptedException {
        //队列设置大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        arrayBlockingQueue.put("a");
        arrayBlockingQueue.put("b");
        arrayBlockingQueue.put("c");
        arrayBlockingQueue.put("d");//阻塞

        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());//阻塞
    }

    public void test4() throws InterruptedException {
        //队列设置大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.offer("a"));//添加
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("c"));
        System.out.println(arrayBlockingQueue.offer("d", 2 , TimeUnit.SECONDS));//等2秒 ，退出

        System.out.println(arrayBlockingQueue.poll());//移除
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll(2 , TimeUnit.SECONDS));////等2秒 ，退出
    }
}
