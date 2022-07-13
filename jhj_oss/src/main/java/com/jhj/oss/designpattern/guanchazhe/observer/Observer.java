package com.jhj.oss.designpattern.guanchazhe.observer;

/**
 * 抽象观察者Observer
 * 观察者一般是一个接口，每一个实现该接口的实现类都是具体观察者。
 */
public interface Observer {
   //响应
    public void response();
}