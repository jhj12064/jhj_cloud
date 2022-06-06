package com.jhj.oss.designpattern.jingtaidaili;

import com.jhj.oss.designpattern.jingtaidaili.service.Man;

/**
 * @author 晓龙
 * @version 1.8.271
 * @ProtectName ProxyDemo
 * @ClassName Test
 * @Description 测试类
 * @createTime 2021年05月23日 12:42:00
 */
public class Test {
    public static void main(String[] args) {
        // 实例一个正常的man
        Man man = new Man();
        man.setName("peter");
        // man结婚
        man.marry();
        System.out.println("=========分割线============");
        // 实例代理类--->中介公司
        MarryProxy marryProxy = new MarryProxy();
        // 设置代理对象
        marryProxy.setPerson(man);
        // 结婚代理
        marryProxy.marry();
    }
}
