package com.jhj.oss.juc2.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**

 * @author Jeremy
 * @date 2022/6/23 11:05
 */
public class ThreadLocalDemo2 {
    public static void main(String[] args) throws InterruptedException {
        MyData mydata = new MyData();
        ExecutorService pool = Executors.newFixedThreadPool(3);

        try {
            for (int i = 0; i < 10; i++) {
                pool.submit(()->{
                    try {
                        Integer integer = mydata.field.get();
                        mydata.add();
                        Integer integer2 = mydata.field.get();
                        System.out.println(Thread.currentThread().getName() + "  befaore :" + integer + " after: " + integer2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        mydata.field.remove();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            pool.shutdown();
        }

    }
}

//卖房子
class MyData {

    ThreadLocal<Integer> field = ThreadLocal.withInitial(()->0);//每个线程独有的

    public  void add(){

        field.set(field.get() +1);
    }
}
