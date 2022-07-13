package com.jhj.oss.designpattern.guanchazhe.observer;

/**
 * @author Jeremy
 * @date 2022/7/13 9:59
 */
public class ConcreteObserver implements Observer{
    @Override
    public void response() {
        System.out.println("shabi！");
    }
}


//具体观察者1
class ConcreteObserver1 implements Observer {
    @Override
    public void response() {
        System.out.println("具体观察者1作出反应！");
    }
}

//具体观察者1
class ConcreteObserver2 implements Observer {
    @Override
    public void response() {
        System.out.println("具体观察者2作出反应！");
    }
}
