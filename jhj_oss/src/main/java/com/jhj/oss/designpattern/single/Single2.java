package com.jhj.oss.designpattern.single;

/**
 * 懒汉式
 */
public class Single2 {
    private static Single2 single ;
    private Single2() {

    }

    public static synchronized Single2 getSingle(){
        if(single == null){
            single = new Single2();
        }
        return single;
    }

    /**
     * 双重检索模式
     * @return
     */
    /*public static  Single2 getSingle(){
        if(single == null){
            synchronized (Single2.class){
                if(single == null){
                    single = new Single2(); //不是原子性操作
                }
            }

        }
        return single;
    }*/
}
