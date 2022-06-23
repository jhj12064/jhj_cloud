package com.jhj.oss.juc2.suo.sync;

/**
 * 锁消除
 */
public class LockClearUpDemo {
 static Object lock = new Object();

 public void m1(){
     Object o = new Object();
     //锁消除问题，jit编译器会无视他，因为锁的不同对象
     synchronized ( o){

     }
 }

    public static void main(String[] args) {
        LockClearUpDemo lockClearUpDemo = new LockClearUpDemo();

    }
}
