package com.guo.demo05;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by guo on 2018/1/27.
 */
public class CalendarDemo01 {
    public static void main(String[] args) {
        function();
        function_1();
        function_2();
        function_3();
    }
    /**
     * Calendar类的方法getTime()
     * 把日历对象转化成Date日期对象
     */
    public static void function_3() {
        Calendar cal = Calendar.getInstance();
        //把日历对象转化成Date对象
        Date date = cal.getTime();
        System.out.println(date);
    }

    /**
     * Calendar类方法add
     * 日历的偏移量，可以指定一个日历的字段，进行整数的偏移
     * abstract void add(int field, int amount)
     */
    public static void function_2() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR,2);
        int year = cal.get(Calendar.YEAR);
        System.out.println(year);  //2020
    }


    /**
     * Calendar类set方法
     * 设置日历
     * set(int field,int value)
     *     field 设置是那个日历字段
     *     value 设置后具体的数值
     *
     * void set(int year, int month, int date)
     * void set(int year, int month, int date, int hourOfDay, int minute)
     *
     */
    public static void function_1() {
        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR,2068);
//        cal.set(Calendar.MONTH,12) ;
//        cal.set(Calendar.DAY_OF_MONTH,22);
        //直接设置年月日
        cal.set(2068,12,22);
        //获取年
        int year = cal.get(Calendar.YEAR);
        //获取月份，需要+ 1
        int mouth = cal.get(Calendar.MONTH ) + 1;
        //获取天数
        int day = cal.get(Calendar.DAY_OF_MONTH);
        System.out.println(year + "年" + mouth +"月" + day + "日");
    }

    /**
     * Calendar类的get方法
     * 获取日历字段的值
     * int get(int)
     * 参数int 获取那个日历字段
     * 返回值，表示日历字段的具体数值
     */
    public static void function() {
        Calendar cal = Calendar.getInstance();
        //获取年
        int year = cal.get(Calendar.YEAR);
        //获取月份，需要+ 1
        int mouth = cal.get(Calendar.MONTH ) + 1;
        //获取天数
        int day = cal.get(Calendar.DAY_OF_MONTH);
        System.out.println(year + "年" + mouth +"月" + day + "日");

    }
}
