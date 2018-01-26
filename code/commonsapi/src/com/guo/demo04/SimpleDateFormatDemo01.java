package com.guo.demo04;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by guo on 2018/1/27.
 * DateFormat类方法parse
 * 将字符串解析为日期对象
 * Date parse(String s) 字符串变为日期对象
 * String >= Date parse()
 * Date >= String format()
 */
public class SimpleDateFormatDemo01 {
    public static void main(String[] args) throws ParseException {
        function();
    }
    /**
     * 将字符串转为日期对象
     * DateFormat类的parse方法，
     * 步骤：
     *  1.创建SimpleDateFormat对象，在构造方法中指定日期的模式：
     *  2.调用parse()方法，传递String，返回Date
     */
    public static void function(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;  //ParseException
        try {
            date = sdf.parse("2018-01-23");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);
    }
}
