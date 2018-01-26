package com.guo.demo02;

import java.io.UnsupportedEncodingException;

/**
 * Created by guo on 2018/1/26.
 */
public class StringDemo4 {
    public static void main(String[] args) {
        //function();
        //function1();
        //function2();
        // function3();
        function9();

    }

    /**
     * char charAt(int index)
     * 返回索引上的字符
     */
    public static void function9() {
        char ch = "abcdef".charAt(4);
        System.out.println(ch);
    }

    /**
     * boolean 	equals(Object anObject)
     * 方法传递字符串，判断字符串中的字符完全相同，如果相同返回true
     * boolean 	equalsIgnoreCase(String anotherString)
     * 忽略大小写 boolean 	Compares this String to another String, ignoring case considerations.
     */
    public static void function8() {
        String str1 = "hahaha";
        String str2 = "HAHAHA";
        String str3 = "一hahaha";
        String str4 = "壹HAHAHA";
        System.out.println(str1.equals(str2)); //false
        System.out.println(str1.equalsIgnoreCase(str2)); //true
        System.out.println(str3.equalsIgnoreCase(str4));  //false 一个笑话！

    }

    /**
     * char[] 	toCharArray()  将字符串转成字符数组
     */
    public static void function7() {
        String str = "ABCDEFG";
        char[] chars = str.toCharArray();
        for (char c : chars) {
            System.out.println(c);
        }
    }

    /**
     * byte[] 	getBytes()
     * 将一个字符串转化成字节数组
     * byte数组相关的功能 ，会查询编码表。
     */
    public static void function6() {
        String  str = "abc";
        //调用String类getBytes()方法，将字符串转变成字节数组

            byte[]   bytes = str.getBytes();
           for(int i = 0; i< bytes.length;i ++) {
               System.out.println(bytes[i]);
           }
    }

    /**
     * int 	indexOf(int ch)
     * 查找一个字符，在字符串中第一次出项的位置
     */
    public static void function5() {
        String str = "action";
        int c = str.indexOf('c');
        System.out.println(c);
    }

    /**
     * boolean 	contains(CharSequence s)
     * 判断一个字符串中是否包含另一个字符串
     */
    public static void function4() {
        String str = "越努力，越幸运";
        boolean b = str.contains("努力");
        System.out.println(b);
    }

    /**
     * 判断一个字符串是否是另外一个字符串的后缀
     * boolean 	endsWith(String suffix)
     * Hello.java
     *      .java
     */
    public static void function3() {
        String str = "Hello.java";
        boolean b = str.endsWith(".java");
        System.out.println(b);
    }


    /**
     * 判断一个字符串是否是另一个字符串的前缀，
     * boolean 	startsWith(String prefix)
     * haohaojiayou
     * hao
     * boolean 	startsWith(String prefix, int toffset)
     *
     * 判断一个字符串是否是另外一个字符串的后缀
     * boolean 	endsWith(String suffix)
     */
    public static void function2() {
        String str = "haohaojiayou";
        //Tests if this string starts with the specified prefix.
        boolean b = str.startsWith("hao");
        System.out.println(b);

        boolean b1 = str.startsWith("hao", 4);
        System.out.println(b1); //false
    }

    /**
     * String substring(int beginIndex, int endIndex)  截取字符串的一部分
     * beginIndex - the beginning index, inclusive.
     * endIndex - the ending index, exclusive.
     * String substring(int beginIndex)
     */
    public static void function1() {
        String str = "haohaojiayou";
        String newStr = str.substring(1,3);
        System.out.println(newStr);
    }

    /**
     * int length() 返回字符串的长度
     * 包含多个字符
     */
    public static void function() {
        String str = "sdfjsakrudsjqfs5312453";
        //调用String类的length()方法，获取字符串的长度
        int len = str.length();
        System.out.println(len);
    }
}
