package com.jhj.oss.designpattern.guanchazhe;

import com.jhj.oss.designpattern.guanchazhe.observer.ConcreteObserver;
//import com.jhj.oss.designpattern.guanchazhe.observer.ConcreteObserver1;
//import com.jhj.oss.designpattern.guanchazhe.observer.ConcreteObserver2;
import com.jhj.oss.designpattern.guanchazhe.observer.Observer;
import com.jhj.oss.designpattern.guanchazhe.subject.ConcreteSubject;

/**
 * 首先创建一个被观察者，然后定义一个观察者，将该被观察者添加到该观察者的观察者数组中，进行测试。
 */
public class ObserverPattern {
 
    public static void main(String[] args) {
        //创建一个主题
        ConcreteSubject subject = new ConcreteSubject();
        //定义一个观察者
        Observer observer = new ConcreteObserver();
//        Observer observer1 = new ConcreteObserver1();
//        Observer observer2 = new ConcreteObserver2();
        //注册观察者
        subject.addObserver(observer);
//        subject.addObserver(observer1);
//        subject.addObserver(observer2);
        //开始活动
        subject.doSomething();
    }
 
}