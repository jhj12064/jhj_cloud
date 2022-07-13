package com.jhj.oss.designpattern.guanchazhe.subject;

/**
 * 具体主题
 * 继承Subject类，在这里实现具体业务，在具体项目中，该类会有很多变种。
 */
public class ConcreteSubject extends Subject {
 
    //具体业务
    public void doSomething() {
        //...
 
        System.out.println("具体目标发生改变...");
        System.out.println("--------------");
        super.notifyObserver();
    }
 
}