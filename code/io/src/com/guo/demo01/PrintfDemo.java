package com.guo.demo01;

/**
 * Created by guo on 2018/1/30.
 */
public class PrintfDemo {
    public static void main(String[] args) {
        String name= "郭";
        int  age = 24 ;
        //传统做法
        String str  = "名字:" + name + "年龄：" +age ;
        System.out.println(str);
        //格式化做法
        String format = "名字: %s ,年龄%d ";
        Object[] data  = {"张大猪",24};
        System.out.printf(format ,data);
        System.out.println();
        //简化写法
        System.out.printf("名字: %s ,年龄%d ","张大猪",24);
    }
}
