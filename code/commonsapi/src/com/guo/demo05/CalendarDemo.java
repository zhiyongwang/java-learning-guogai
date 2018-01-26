package com.guo.demo05;

import java.util.Calendar;

/**
 * Created by guo on 2018/1/27.
 * 日历类 Java.util.Calendar
 * 抽象类，使用的他的子类对象
 * Calendar类写了静态方法getInstence()直接返回了子类的对象
 * 不需要直接new子类对象，通过静态方法直接获取
 */
public class CalendarDemo {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();  // cal = new GregorianCalendar(zone, aLocale);
        /**
         * YEAR=2018,MONTH=0,WEEK_OF_YEAR=4,WEEK_OF_MONTH=4,
         * DAY_OF_MONTH=27,DAY_OF_YEAR=27,DAY_OF_WEEK=7,
         * AY_OF_WEEK_IN_MONTH=4,AM_PM=0,HOUR=2,HOUR_OF_DAY=2,
         * MINUTE=50,SECOND=20,MILLISECOND=243,ZONE_OFFSET=28800000,
         */
        System.out.println(cal);
    }
}
