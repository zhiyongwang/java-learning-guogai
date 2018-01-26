package com.guo.demo01;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by guo on 2018/1/26.
 */
public class TestEquals {
    //Person类继承Object，继承下来了equals()方法。
    @Test
    public void test1() {
        //Person类继承Object，继承下来了equals()方法。
        Person p1 = new Person("张三",24);
        Person p2 = new Person("李四",24);

        ArrayList<String> array = new ArrayList<>();
        //Person对象p1，调用父类对象equals()方法进行比较
        //底层是使用了==进行比较，
        //在引用类型中==比较的是内存的地址
        boolean b = p1.equals(array);  //ClassCastException类型转化异常，cannot be cast to com.guo.demo01.Person
        System.out.println(b);

    }
}
