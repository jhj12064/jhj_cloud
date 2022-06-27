package com.jhj.oss.juc2.suo.sync;

public class Ticket {
    private int num = 50;
    Object lock = new Object();

    public synchronized void sale() {
        if (num > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出" + num-- + " 剩下：" + num);
        }
    }

    /**
     * 大部分情况是同一个线程执行，没有平均
     * @param args
     */
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                ticket.sale();
            }
        }, "a").start();
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                ticket.sale();
            }
        }, "b").start();
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                ticket.sale();
            }
        }, "c").start();
    }
}
