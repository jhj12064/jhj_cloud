package com.jhj.oss.designpattern.dongtaidaili.cglib;

/**
 * cglib 动态代理（类）
 *
 * @author Jeremy
 * @date 2022/6/2 11:22
 */
public class Test {
    public static void main(String[] args) {
        ChinesePoxy chinesePoxy = new ChinesePoxy(new Chinese());
        Object proxyInstance = chinesePoxy.getProxyInstance();
        Chinese chinese=(Chinese)proxyInstance;
        chinese.sayHello();
    }
}
