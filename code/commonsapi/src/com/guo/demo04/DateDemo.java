package com.guo.demo04;

import java.util.Date;

/**
 * Created by guo on 2018/1/27.
 * 时间和日期
 * java.util.Date
 * 毫秒：1000毫秒=1秒，
 * 纳秒：1000纳秒=1毫秒
 * 毫秒的0点：
 *  System.currentTimeMillis() 返回值为long类型参数
 */
public class DateDemo {
    public static void main(String [] args) {
        function();
        function_1();
        function_2();
        function_3();
        //the difference, measured in milliseconds, between the current time and midnight, January 1, 1970 UTC.
        long first = System.currentTimeMillis();
        System.out.println("越努力，越幸运");
        long end = System.currentTimeMillis();
        System.out.println("总过花费时间为：" + first + end);
    }
    /**
     * Date类方法setTime(long)传递毫秒值
     * 将日期对象设置到指定毫秒值iiii
     * 毫秒值转化成日期对象是
     */
    public static void function_3() {
        Date date  = new Date();
        date.setTime(88888888);
        System.out.println(date);
    }

    /**
     * Date类方法，getTime()返回值为long类型
     * 返回的是毫秒值
     * 将Date表的日期转成毫秒值
     * 日期和毫秒值的转化
     */
    public static void function_2() {
        Date date = new Date();
        long time = date.getTime();
        System.out.println(time);
    }

    /**
     * Date类的long参数的构造方法
     * Date(long date) 表示的是毫秒值
     * 传递一个毫秒值，将毫秒值转成日期对象。
     */
    public static void function_1() {
        Date date =  new Date(6666);
        System.out.println(date);   //Thu Jan 01 08:00:06 CST 1970
    }

    /**
     * Date类空参构造方法
     * 获取的是当前系统中的日期和时间
     */
    public static void function() {
        Date date = new Date();
        System.out.println(date);    //重写过toString方法
    }
}
