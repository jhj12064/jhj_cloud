package com.jhj.oss.designpattern.single;

/**
 * 懒汉式
 */
public class Single3 {
    private volatile static  Single3 single ;
    private Single3() {

    }

    /*public static synchronized Single3 getSingle(){
        if(single == null){
            single = new Single3();
        }
        return single;
    }*/

    /**
     * 双重检索模式
     * @return
     */
    public static  Single3 getSingle(){
        if(single == null){
            synchronized (Single2.class){
                if(single == null){
                    single = new Single3(); //不是原子性操作
                }
            }

        }
        return single;
    }
}
