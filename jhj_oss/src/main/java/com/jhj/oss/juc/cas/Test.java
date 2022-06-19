package com.jhj.oss.juc.cas;

public class Test {
    public static void main(String[] args) {
        Integer integer = new Integer(128);
        Integer integer2 = new Integer(128);

        System.out.println(integer.equals(integer2));

        Integer integer3 = new Integer(127);
        Integer integer4 = new Integer(127);

        System.out.println(integer3.equals(integer4));

        int a = 128;
        int b =128;
        System.out.println(a==b);
    }
}
