package com.jhj.oss.juc2.threadlocal.gc;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Jeremy
 * @date 2022/6/23 14:16
 */
public class ReferenceDemo {

    public static void main(String[] args) throws InterruptedException {
        xu();
    }

    /**
     * 虚引用：形同虚设
     * 1.只要gc就会被回收，他必须和队列(ReferenceQueue)联合使用
     * 2.get方法总是null
     * 3处理监控通知使用
     */
    public static void xu() throws InterruptedException {
        Myobject myobject = new Myobject();
        //队列
        ReferenceQueue<Myobject> referenceQueue = new ReferenceQueue();
        PhantomReference<Myobject> phantomReference = new PhantomReference<>(myobject, referenceQueue);
        System.out.println(phantomReference.get());
        ArrayList<Object> objects = new ArrayList<>();
        new Thread(()->{
            while (true){
                objects.add(new byte[1*1024*1024]);

            }
        },"t1").start();
        new Thread(()->{
            while (true){
                Reference<? extends Myobject> reference = referenceQueue.poll();
                if(reference!=null){
                    System.out.println("存在虚引用被回收");
                }
            }
        },"t2").start();


    }

    /**
     * 弱引用
     * 只要gc就会被回收
     */
    public static void weak() throws InterruptedException {
        WeakReference<Myobject> myobjectWeakReference = new WeakReference<>(new Myobject());
        System.gc();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(myobjectWeakReference.get());
    }

    /**
     * 软引用
     * 内存够用的情况不会被回收,内存不足会被回收
     * 使用场景： 必须需要缓存大量的图片，每次都去读取影响性能，缓存太多容易内存溢出，此时可以使用软引用
     */
    public static void soft() {
        SoftReference<Myobject> myobjectSoftReference = new SoftReference<>(new Myobject());
        System.gc();
        System.out.println(myobjectSoftReference.get());
        //创建一个大对象沾满内存，gc就会被回收
    }

    /**
     * 强引用
     */
    public static void strong() throws InterruptedException {
        Myobject myobject = new Myobject();
        System.out.println("gc before" + myobject);
        myobject = null;
        System.gc();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("gc after" + myobject);
    }

}

class Myobject {
    @Override
    protected  void finalize() throws Throwable{
        System.out.println("invoke finalize");
    }
}

