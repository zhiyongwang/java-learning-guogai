package com.guo.regexDemo;

/**
 * Created by guo on 2018/1/26.
 * 实现正则规则和字符串进行匹配，使用到字符串类的方法
 * String类三个和正则表达式相关的方法
 * boolean 	matches(String regex)  regex表示正则的规则
 * "abc".matches("[a]"),结果为真就『匹配』。
 * String[] split(CharSequence input)
 * "abc".split("a") 使用规则将字符串进行『切割』
 * String 	replaceAll(String regex, String replacement)
 * "abc123".replaceAll("[\\d]","#")
 *  按照正则的规则进行『替换』字符串
 */
public class RegexDemo01 {
    public static void main(String[] args) {
        checkQQ();
        checkTel();
    }


    /**
     * 检查手机号是否合法
     * 1开头，可以是34578 0-9为固定为11位
     */
    public static void checkTel() {
        String telNumber = "15771695438";  //瞎写的
        boolean b = telNumber.matches("1[34578][\\d]{9}");
        System.out.println(b);
    }

    /**
     * 检查QQ号码是否合法
     * 0不能开头，全数字，位数为5，10位
     * \d需要写成\\d
     */
    public static void checkQQ() {
        String QQ = "0123456";
        //检查QQ号码和规则是否匹配，String类的matches()
        boolean b = QQ.matches("[1-9][0-9]{4,9}");
        System.out.println(b);

    }
}
