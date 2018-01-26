package com.guo.demo06;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by guo on 2018/1/27.
 */
public class DateTest {
    public static void main(String[] args) throws ParseException {
        //function();
        function_1();
    }
    /**
     * 闰年计算
     * 高级的算法：日历社会子到指定年份的3月1日，add向前偏移一天，获取天数，29是闰年
     */
    public static void function_1() {
        Calendar cal = Calendar.getInstance();
        //将日历设置到指定年的3月1日。
        cal.set(2008,2,1);
         //Calendar类的add方法，向前偏移一天
        cal.add(Calendar.DAY_OF_MONTH,-1);

        //get()方法获取天数
        int day = cal.get(Calendar.DAY_OF_MONTH);
        System.out.println(day);

        if(day == 29) {
            System.out.println("今年是闰年");
        }else {
            System.out.println("不是闰年");
        }
    }

    /**
     * 计算活了多少天
     *  生日 今天的日期
     *  两个日期变为毫秒值，减去
     */
    public static void function() throws ParseException {
        System.out.println("请输入出生日期 格式为：yyyy-MM-dd");
        String birthdayString = new Scanner(System.in).next();
        //将字符串转为Date对象
        //创建SimpleDateFormat对象爱你个，写日期模式：yyyy-MM-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = sdf.parse(birthdayString);

        //获取今天的日期
        Date todayDate = new Date();

        //将两个日期转为毫秒值，Date类的方法getTime()
        long todayDateTime = todayDate.getTime();
        long birthDateTime = birthDate.getTime();
        long secode = todayDateTime -birthDateTime;
        if (secode <=0) {
            System.out.println("你还没出生呢！请重新录入生日...");
        }else{
            System.out.println("你活了" + (secode/1000/60/60/24)+ "天");
        }
    }
}
