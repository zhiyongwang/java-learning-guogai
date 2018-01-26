package com.guo.demo02;

/**
 * Created by guo on 2018/1/26.
 * String类的特点：
 * 一切都是对象，字符串事物"" 也是对象
 * 类是描述事物，String类，描述字符串的类
 * All string literals in Java programs, such as "abc", are implemented as instances of this class.
 * Strings are constant; their values cannot be changed after they are created.
 * String buffers support mutable strings. Because String objects are immutable they can be shared
 * 字符串本质是一个字符数组，字符串是常量。"hello" = char[] = {"h","e","l","l","0"};
 *
 *The value is used for character storage.
 * private final char value[];
 */
public class StringDemo1 {
    public static void main(String[] args) {
        String str = "好好加油";
        //因为String类重写了toString()方法。
        //引用变量str执行内存变化
        str = "越努力，越幸运";
        System.out.println(str);
    }
}
