package com.jhj.oss.juc2.threadlocal;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 群雄逐鹿起纷争
 * 人手一份天下安
 * @author Jeremy
 * @date 2022/6/23 11:05
 */
public class ThreadLocalDemo1 {
    public static void main(String[] args) throws InterruptedException {
        House house = new House();
        //5个销售卖房子
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    int size = new Random().nextInt(5) + 1;
                    System.out.println("size: "+size);
                    for (int i1 = 0; i1 < size; i1++) {
                        house.sale();
                        house.saleByThreadLocal();
                    }
                    System.out.println(Thread.currentThread().getName() + "号销售卖出：" + house.saleVolume.get());
                } finally {
                    //强制：必须回收自定义的ThreadLocal变量,尤其在线程池场景下，线程经常被复用，如不清理，容易导致内存泄漏
                    house.saleVolume.remove();
                }
            }, i +"").start();
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println("总共卖出：" + house.saleCount);
    }
}

//卖房子
class House{
    int saleCount = 0;
    public synchronized void sale(){
        saleCount ++;
    }

    //不推荐
    /*ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return super.initialValue();
        }
    };*/
    ThreadLocal<Integer> saleVolume = ThreadLocal.withInitial(()->0);//每个线程独有的

    public  void saleByThreadLocal(){
        Integer integer = saleVolume.get();
        saleVolume.set(saleVolume.get() +1);
    }
}
