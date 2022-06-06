package com.jhj.oss.designpattern.jingtaidaili;

import com.jhj.oss.designpattern.jingtaidaili.service.Marry;
import com.jhj.oss.designpattern.jingtaidaili.service.Person;

/**
 * @author 晓龙
 * @version 1.8.271
 * @ProtectName ProxyDemo
 * @ClassName MarryProxy
 * @Description 结婚代理
 * @createTime 2021年05月23日 12:44:00
 */
public class MarryProxy implements Marry {
    // 代理对象
    private Person person;

    public void setPerson(Person person) {
        this.person = person;
    }

    // 代理结婚
    @Override
    public void marry() {
        // 结婚前准备
        beforeMarry();
        // 结婚
        person.marry();
        // 结婚后
        afterMarry();

    }
    private void beforeMarry(){
        System.out.println("结婚前准备");
    }
    private void afterMarry(){
        System.out.println("结婚后准备");
    }
}
