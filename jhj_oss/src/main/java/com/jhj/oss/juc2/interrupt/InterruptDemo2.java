package com.jhj.oss.juc2.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 中断三种方法
 * 2. AtomicBoolean 中断程序
 * @author Jeremy
 * @date 2022/6/21 14:44
 */
public class InterruptDemo2 {

    //标志位
  static AtomicBoolean isstop = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (true){
                if (isstop.get()){
                    System.out.println(Thread.currentThread().getName() + "isstop==true.程序停止");
                    break;
                }
                System.out.println("t1 -----hello volatile");
            }
        }, "t1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            isstop.set(true);
        }, "t2").start();
    }
}
