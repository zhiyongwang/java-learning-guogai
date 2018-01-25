package com.guogai.reflect01;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by guo on 2018/1/25.
 */
public class TestConstructor {
    @Test
    public void test() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String name = "com.guogai.reflect01.Person";
        Class<?> clazz = Class.forName(name);
        //创建对应的运行时类对象，必须要与无参构造器
        Object obj = clazz.newInstance();  //实际上调用了类的无参构造器
        Person p = (Person) obj;
        System.out.println(p);
    }
    @Test
    public void test1() throws ClassNotFoundException {
        String clazzName = "com.guogai.reflect01.Person";
        ClassLoader loader = this.getClass().getClassLoader();
        Class<?> clazz = loader.loadClass(clazzName);
        //huoqu suoyou gouzaoqi !!!!!!
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }

    }
    //调用指定类型的构造器,创建运行时类对象
    @Test
    public void test2() throws Exception {
        String clazzName = "com.guogai.reflect01.Person";
        ClassLoader loader = this.getClass().getClassLoader();
        Class<?> clazz = loader.loadClass(clazzName);
        Constructor<?> constructor = clazz.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true);
        Person person = (Person)constructor.newInstance("xiao", 20);
        System.out.println(person);

    }
}
