package com.jhj.oss.designpattern.dongtaidaili.jdk;

import java.lang.reflect.Proxy;

/**
 * jdk动态代理测试（interface）
 *
 * @author Jeremy
 * @date 2022/6/2 11:11
 */
public class Test {
    public static void main(String[] args) {
        /*People chinese = () -> {
            System.out.println("111111111");
        };*/
        Chinese chinese = new Chinese();
        PeopleInvocationHandler invocationHandler = new PeopleInvocationHandler(chinese);
        People proxy = (People) Proxy.newProxyInstance(chinese.getClass().getClassLoader(), chinese.getClass().getInterfaces(), invocationHandler);
        proxy.sayHello();
    }
}
