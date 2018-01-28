package com.guo.demo;

/**
 * Created by guo on 2018/1/28.
 * 程序中的主线程
 */
public class Demo {
    public static void main(String[] args) {
       // System.out.println(0/0);  Exception in thread "main" java.lang.ArithmeticException: / by zero
        function();
        System.out.println(Math.abs(-9));
    }
    public static void function() {
        for(int i = 0; i < 100; i++) {
            System.out.println(i);
        }
    }

}
