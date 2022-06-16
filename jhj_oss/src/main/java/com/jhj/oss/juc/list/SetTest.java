package com.jhj.oss.juc.list;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Jeremy
 * @date 2022/6/15 14:59
 */
public class SetTest {
    public static void main(String[] args) {
       // Set<String> list = new HashSet<>();//并发修改异常
        Set<Object> list = Collections.synchronizedSet(new HashSet<>());
        CopyOnWriteArraySet<Object> objects = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0, 5));
                // list.add("asasas");
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
