package com.jhj.oss.juc.list;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Jeremy
 * @date 2022/6/15 14:28
 */
public class CopyOnWriteArrayListTest {
//java.util.ConcurrentModificationException 并发修改异常
    public static void main(String[] args) {
        //List<String> list = new ArrayList<>();
       // list = new Vector<>(); 解决方案1
        //方案2
       // Collection<Object> list = Collections.synchronizedCollection(new ArrayList<>());
        //方案3 volatile 写入时复制
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 20; i++) {

            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0, 5));
               // list.add("asasas");
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
