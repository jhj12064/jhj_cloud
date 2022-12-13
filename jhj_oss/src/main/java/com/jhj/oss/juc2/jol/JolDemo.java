package com.jhj.oss.juc2.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @author Jeremy
 * @date 2022/6/23 16:22
 */
public class JolDemo {
    public static void main(String[] args) {
      //  System.out.println(VM.current().details());// VM的细节详细情况
      //  System.out.println(VM.current().objectAlignment());//所有对象分配的字节都是8的整数
        Object o = new Object();
        Customer customer = new Customer();//默认16字节
        System.out.println(ClassLayout.parseInstance(customer).toPrintable());
    }
}

class Customer{
    int id;
     boolean a =false;
    Integer id2;
}