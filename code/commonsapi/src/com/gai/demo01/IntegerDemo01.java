package com.gai.demo01;

import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * Created by guo on 2018/1/27.
 * Integer类中的其他方法
 * 包括三个方法，和两个静态成员变量
 */
public class IntegerDemo01 {
    public static void main(String[] args) {
        function();
        function_1();
    }
    /**
     * Ingeger类的三个静态方法
     * 进制的转化
     * 十进制转为二进制 toBinaryString(int i)
     * 十进制转为八进制 toOctalString(int i)
     * 十进制转为十六进制  	toHexString(int i)
     */
    public static void function_1() {
        String s = Integer.toBinaryString(123);
        System.out.println(s);

        String s1 = Integer.toOctalString(123);
        System.out.println(s1);

        String s2 = Integer.toHexString(123);
        System.out.println(s2);
    }

    /**
     * Integer类的成员变量
     * int MAX_VALUE
     * int MIN_VALUE
     */
    public static void function() {
        System.out.println("Integer最大值：" + Integer.MAX_VALUE);  //2147483647
        System.out.println("Integer最小值：" + Integer.MIN_VALUE);  //-2147483648
        System.out.println(Integer.MAX_VALUE + 1);                   //-2147483648
        System.out.println(Integer.MIN_VALUE + 1);                   //-2147483647

        System.out.println("Long最大值：" + Long.MAX_VALUE);         //9223372036854775807
        System.out.println("Long最小值：" + Long.MIN_VALUE);          //-9223372036854775808

        System.out.println("Double最大值：" + Double.MAX_VALUE);               //1.7976931348623157E308
        System.out.println("Double最小值：" + Double.MIN_VALUE);               //4.9E-324
    }

}
