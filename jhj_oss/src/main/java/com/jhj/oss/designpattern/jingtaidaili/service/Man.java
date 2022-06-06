package com.jhj.oss.designpattern.jingtaidaili.service;

public class Man implements Person{
    /**
     * 姓名
     */
    private String name;
    
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 重写结婚方法
     */
    @Override
    public void marry() {
        System.out.println(name+"在结婚");
    }

    /**
     * 重写说话方法
     */
    @Override
    public void talk() {
        System.out.println(name+"在说话");
    }
}
