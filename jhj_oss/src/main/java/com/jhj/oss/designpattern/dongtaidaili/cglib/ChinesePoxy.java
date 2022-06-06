package com.jhj.oss.designpattern.dongtaidaili.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Method;

/**
 * @author Jeremy
 * @date 2022/6/2 11:17
 */
public class ChinesePoxy implements MethodInterceptor {

    private Object target;

    public ChinesePoxy(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {

        // 1.cglib工具类
        Enhancer en = new Enhancer();
        // 2.设置父类
        en.setSuperclass(this.target.getClass());
        // 3.设置回调函数
        en.setCallback(this);

        return en.create();
    }



    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始事务...");

        // 执行目标对象的方法
        Object result = method.invoke(target, args);

        System.out.println("提交事务...");
        return result;

    }
}
