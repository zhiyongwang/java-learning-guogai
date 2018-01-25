package com.guogai.reflect01;

import sun.awt.SunHints;

/**
 * Created by guogai on 2018/1/25.
 */
@MyAnnotation(value="guogai")
public class Person extends Creature<String> implements Comparable,MyInterface {
    public String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @MyAnnotation(value = "8888")
    public void show() {
        System.out.println("我是一个人");
    }
    public void display(String nation) throws Exception {
        System.out.println("我的国际是：" + nation);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
    class bird {

    }
}
