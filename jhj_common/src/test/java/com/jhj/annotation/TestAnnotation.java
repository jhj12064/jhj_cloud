package com.jhj.annotation;

import cn.hutool.core.lang.ClassScanner;
import cn.hutool.core.map.TableMap;
import com.jhj.common.annotation.TestClass;
import com.jhj.common.annotation.TestField;
import com.jhj.common.model.test.TestZhuJie;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Jeremy
 * @date 2022/4/28 16:48
 */

public class TestAnnotation {

    /**
     * 获取注解属性
     */
    @Test
    public void test1(){
        TestZhuJie testZhuJie = new TestZhuJie();
        Class<? extends TestZhuJie> aClass = testZhuJie.getClass();
        //判断对象是否有注解
        if(aClass.isAnnotationPresent(TestClass.class)){
            System.out.println("有注解！有注解");
            TestClass annotation = aClass.getAnnotation(TestClass.class);
            System.out.println(annotation +"------------->"+ annotation);

            System.out.println(annotation.name() +"------------->"+ annotation.flag());
        }else {
            System.out.println("没有注解呦");
        }
    }



    @Test
    public void test2() throws Exception {
        //Set<Class <?>> classes = ClassScanner.scanPackageByAnnotation(packageName, BatchSynchTable.class);
        Class <?> clazz = Class.forName("com.jhj.common.model.test.TestZhuJie");
        TestClass testClass = clazz.getAnnotation(TestClass.class);
        System.out.println("testClass.flag------->"+ testClass.flag());
        System.out.println("clazz.getName()------->"+ clazz.getName());
        System.out.println("clazz.getSimpleName()------>"+ clazz.getSimpleName());
        //所有字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            boolean annotationPresent = field.isAnnotationPresent(TestField.class);
            if (annotationPresent) {
                TestField testField = field.getAnnotation(TestField.class);
                System.out.println("testField---------->" + testField);
            }
        }
    }

    @Test
    public void test3() throws Exception {
        //Set <Class <?>> classes = ClassScanner.scanPackageByAnnotation("", BatchSynchTable.class);

    }
}
