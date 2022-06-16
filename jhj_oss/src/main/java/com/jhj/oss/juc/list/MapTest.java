package com.jhj.oss.juc.list;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jeremy
 * @date 2022/6/15 14:59
 */
public class MapTest {
    public static void main(String[] args) {
        //加载因子 初始化容量
       // Map<String, String> map = new HashMap<>();//并发修改异常
       Collections.synchronizedMap(new HashMap<>());//1
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                // list.add("asasas");
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
