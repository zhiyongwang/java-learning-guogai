package com.guo.demo01;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by guo on 2018/1/26.
 */
public class TestString {
    public static void main(String[] args) {
        //调用Person类的toString()方法
        //输出语句中，写的是一个对象，默认调用对象的toString()
        Person p = new Person("wangwu",22);
        String str = p.toString();
        System.out.println(p.toString());
        System.out.println(str);

        Random random = new Random();
        System.out.println(random.toString());

        Scanner sc = new Scanner(System.in);
        System.out.print(sc);
    }
}
