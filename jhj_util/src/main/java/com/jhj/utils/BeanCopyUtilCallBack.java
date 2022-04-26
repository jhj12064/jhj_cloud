package com.jhj.utils;

/**
 * @author sea
 * @date 2020/1/13 10:31 AM
 */
@FunctionalInterface
public interface BeanCopyUtilCallBack<S, T>  {

    /**
     * 定义默认回调方法
     * @param t
     * @param s
     */
    void callBack(S t, T s);
}
