package com.jhj.oss.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Jeremy
 * @date 2022/6/15 15:26
 */
public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //new Thread(new MyThread()).start();
        MyThread2 myThread2 = new MyThread2();
        //适配类
        FutureTask futureTask = new FutureTask<>(myThread2);
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();//结果会缓存,只打印一个call
        //返回结果
        Object o = futureTask.get();//可能产生阻塞，等待结果返回
        System.out.println(o);
    }
}

class MyThread implements Runnable{

    @Override
    public void run() {
        System.out.println("1");
    }
}


class  MyThread2 implements Callable<String> {


    @Override
    public String call() throws Exception {
        System.out.println("call");
        return "1024";
    }
}
