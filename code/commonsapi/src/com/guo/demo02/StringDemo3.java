package com.guo.demo02;

/**
 * Created by guo on 2018/1/26.
 * String的构造方法，重载形式
 */
public class StringDemo3 {
    public static void main(String[] args) {
        //function();
        function1();
    }

    /**
     * String(char[] value) 传递字符数组
     * 将字符数组转化成字符串，字符数组的参数不查询编码表
     *
     * String(char[] value, int offset, int count)
     * 将字符数组的一部分转化成字符串
     */
    public static void function1(){
        char[] ch = {'a','b','c','d'};
        String str = new String(ch);
        System.out.println(str);

        char[] ch1 = {'a','b','c','d'};
        String str1 = new String(ch1,1,3); //会出现StringIndexOutOfBoundsException异常
        System.out.println(str1);

    }
    /**
     * 定义方法，String类的构造方法
     * Constructs a new String by decoding the specified array of bytes using the platform's default charset.
     * String(byte[] bytes)  //传递字节数组
     * 将字节数组中每个字节，查询了编码表，得到的结果。一个汉字采用两个字节来表示。
     *
     * 字节数组的一部分转化成字符串
     * String(byte[] bytes, int offset, int length)
     * bytes - The bytes to be decoded into characters
     * offset - The index of the first byte to decode
     * length - The number of bytes to decode
     */
    public static void function() {
        byte[] bytes = {97,98,99,100};
        //调用String的构造方法，传递字节数组
        String s = new String(bytes);
        System.out.println(s);

        byte[] bytes1 = {65,66,67,68,69};
        //调用String的构造方法，传递数组，传递两个int值
        String s1 = new String(bytes1,1,3);
        System.out.println(s1);
    }
}
