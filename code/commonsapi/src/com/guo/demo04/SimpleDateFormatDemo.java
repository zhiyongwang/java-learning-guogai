package com.guo.demo04;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by guo on 2018/1/27.
 * 对日期类进行格式化(自定义)
 * 对日期格式化的类java.text.DateFormat,抽象类，普通方法，也有抽象方法
 * 实际使用的是子类SimpleDateFormat类。可以使用父类的普通方法，也可以重写抽象方法
 */
public class SimpleDateFormatDemo {
    public static void main(String [] args) {
        function();
    }
    /**
     * 如何对日期进行格式化
     *  步骤：
     *      1.创建SimpleDateFormat对象
     *         在类构造方法中，写入字符串的格式，
     *      2，SimpleDateFormat调用format()对日期进行格式化。
     *      String format(Date date) 传递日期对象，返回字符串
     *      日期模式：yyyy年-MM月-dd日 HH时:mm分:ss秒
     */
    public static void function() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年-MM月-dd日 HH时:mm分:ss秒");
        String date = sdf.format(new Date());
        System.out.println(date);
    }

}
