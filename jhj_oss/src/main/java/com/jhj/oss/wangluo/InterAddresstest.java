package com.jhj.oss.wangluo;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InterAddresstest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress ionetAddress = InetAddress.getByName("127.0.0.1");
        System.out.println(ionetAddress);
        System.out.println(InetAddress.getLocalHost());

        InetAddress baidu = InetAddress.getByName("www.baidu.com");
        System.out.println(baidu);
    }
}
