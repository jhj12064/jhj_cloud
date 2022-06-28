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
        Map<String, String> map0 = new HashMap<>();//并发修改异常
        Map<String, String> stringStringMap = Collections.synchronizedMap(map0);
       // stringStringMap.put()
        Collections.synchronizedMap(new HashMap<>());//1
        Collections.synchronizedList(null);
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
