package com.jhj.jhj_spring.xmlBeanFactory;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Jeremy
 * @date 2022/6/7 10:57
 */
@SuppressWarnings("all")
public class BeanFactoryDemo {
    public static void main(String[] args) {

        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        Student s= (Student)xmlBeanFactory.getBean("student");
        System.out.println(s.getName());
    }
}
