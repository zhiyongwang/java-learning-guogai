package com.guo.regexDemo;

/**
 * Created by guo on 2018/1/26.
 */
public class RegexDemo02 {
    public static void main(String [] args) {
        split_1();
        split_2();
        split_3();
        replaceAll_1();
    }
    /**
     * "Hello12345World678"将所有的书籍替换成*
     * String类方法repleceAll(正则规则，替换后的字符串)；
     * String 	replaceAll(String regex, String replacement)
     * Replaces each substring of this string that matches the given regular expression with the given replacement.
     */
    public static void replaceAll_1() {
        String str = "Hello12345World678";
        String newStr = str.replaceAll("[\\d]+","*");
        System.out.println(newStr);
    }

    /**
     * String类方法split对字符串进行切割
     * 192.168.0.124按照.切割字符串
     */
    public static void split_3() {
        String ip = "192.......168.0.124";
        String[] ipArr = ip.split("\\.+");   //在这里需要加\\进行转义
        System.out.println("数组的长：" +  ipArr.length);
        for (String  i : ipArr) {
            System.out.println(i);
        }
    }

    /**
     * String类方法split对字符串进行切割
     * 12 43 54 312 按照空格进行切割
     */
    public static void split_2() {
        String str = "12      43          54 312";
        String[] strArr = str.split(" +");  //+代表X出现一次或多次
        System.out.println("数组的长度：" + strArr.length);
        for(int i = 0; i < strArr.length; i ++) {
            System.out.println(strArr[i]);
        }
    }

    /**
     * String类方法split()对字符串进行切割
     * 12-34-56-78 按照“-”对字符串进行切割
     */
    public static void split_1() {
        String str = "12-34-56-78";
        //按照-对字符串进行切割，String类中split();
        //String[] 	split(String regex)  Splits this string around matches of the given regular expression.
        String[] strArr = str.split("-");
        System.out.println("数组的长度：" + strArr.length);
        for(String s : strArr) {
            System.out.println(s);
        }
    }
}
