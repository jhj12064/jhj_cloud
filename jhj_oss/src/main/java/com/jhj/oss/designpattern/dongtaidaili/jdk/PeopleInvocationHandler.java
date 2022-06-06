package com.jhj.oss.designpattern.dongtaidaili.jdk;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * @author Jeremy
 * @date 2022/6/2 11:08
 */
public class PeopleInvocationHandler implements InvocationHandler{
    private Object people;

    public PeopleInvocationHandler(Object people){
        this.people = people;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("-------- start---------");
        Object invoke = method.invoke(people, args);
        System.out.println("-------- end ---------");
        return invoke;
    }
}
