package com.jhj.oss.designpattern.guanchazhe2;
 
import java.util.Observable;
import java.util.Observer;
 
public class JavaObserverPattern
{
    public static void main(String[] args)
    {   
        Exploiter exploiter = new Exploiter();
 
        TheOppressed theOppressed1 = new TheOppressed("打工仔1");
        TheOppressed theOppressed2 = new TheOppressed("打工仔2");
        TheOppressed theOppressed3 = new TheOppressed("打工仔3");
 
        //房东给打工仔（观察者）注册
        exploiter.addObserver(theOppressed1);
        exploiter.addObserver(theOppressed2);
        exploiter.addObserver(theOppressed3);
 
        //（被观察者）房东发信息了，通知所有观察者交房租
        exploiter.postChange("打工仔们快来交房租啦~~");
 
    }
}
 
//Observer抽象观察者
class TheOppressed implements Observer
{
    public String name;
    public TheOppressed(String name){
        this.name = name;
    }
 
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(name +" 收到要交租信息");
    }
}
 
//Observable抽象目标类
class Exploiter extends Observable
{
 
    public void postChange(String content)
    {
        setChanged();
        notifyObservers(content);
    }
 
}