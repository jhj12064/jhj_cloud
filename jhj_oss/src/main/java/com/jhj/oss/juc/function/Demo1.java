package com.jhj.oss.juc.function;

import java.util.function.Function;

/**
 * @author Jeremy
 * @date 2022/6/17 10:37
 */
public class Demo1 {
    public static void main(String[] args) {
        /*Function function =new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s;
            }
        };*/
        Function function = (Function<String, String>) s -> s;
        System.out.println(function.apply("a"));
    }
}
