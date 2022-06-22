package com.jhj.oss.juc2.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子引用类型
 *
 * @author Jeremy
 * @date 2022/6/22 18:02
 */
public class AtomicReferenceDemo1 {
    public static void main(String[] args) {

        AtomicReference<User> reference = new AtomicReference<>();
        User user = new User("1", 1);
        User user2 = new User("2", 2);
        reference.set(user);
        System.out.println(reference.compareAndSet(user, user2) + reference.get().toString());
        System.out.println(reference.compareAndSet(user, user2) + reference.get().toString());
    }
}

@Data
@AllArgsConstructor
@ToString
class User{
    String userName;

    int age;
}
