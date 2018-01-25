package com.guogai.reflect01;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by guo on 2018/1/25.
 */
public class TestOther {
    //5、获取运行时类所在的包
    @Test
    public void test5() {
        Class<Person> clazz = Person.class;
        Package p = clazz.getPackage();
        System.out.println(p.getName());
    }
    //4、获取实现的接口
    @Test
    public void test4() {
        Class<Person> clazz = Person.class;
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class i : interfaces) {
            System.out.println(i);
        }

    }
    //3、获取父类的泛型
    @Test
    public void test3() {
        Class<Person> clazz = Person.class;
        Type type = clazz.getGenericSuperclass();
        ParameterizedType type1 = (ParameterizedType) type;
        Type[] types = type1.getActualTypeArguments();
        System.out.println(((Class)types[0]).getName());
    }
    //2、获取带泛型的父类
    @Test
    public void test2() {
        Class<Person> clazz = Person.class;
        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println(genericSuperclass);
    }
    //1、获取运行时类的父类
    @Test
    public void test() {
        Class<Person> clazz = Person.class;
        Class<? super Person> superclass = clazz.getSuperclass();
        System.out.println(superclass);

    }
}
