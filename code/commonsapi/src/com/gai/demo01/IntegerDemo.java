package com.gai.demo01;

/**
 * Created by guo on 2018/1/27.
 * Integer类，封装基本数据类型int，包含了大量方法帮我们去操作基本数据类型
 * 将一个字符串转为基本数据类型int，可以进行数学方面的运算
 * The Integer class wraps a value of the primitive type int in an object. An object of type Integer contains a single field whose type is int.
 * static int parseInt(String s)
 */
public class IntegerDemo {
    public static void main(String[] args) {
        function();
        function_1();
        function_2();
        function_3();
    }
    /**
     * Integer类的构造方法，
     * Integer(int value
     * Integer(String s)
     * 创建Integer对象，包装的是一个字符串。
     *
     * --i,先减1在计算
     * i--,后计算。
     */
    public  static void function_3() {
        Integer in = new Integer("123");
        //将构造方法中字符串转为基本数据类型调用非静态intValue()
        int i = in.intValue();
        System.out.println(--i);
    }

    /**
     * 将基本数据类型int变为字符串
     * intValue()
     * Returns the value of this Integer as an int.
     * int => String
     *  1.直接写一个空串
     *  2.Integer类的toString(int i),
     * toString(int i, int radix)  //转成进制数，
     */
    public static void function_2() {
        int i = 3;
        String s = i + "";
        System.out.println(s +1);

        System.out.println(Integer.toString(5));
    }

    /**
     * Integet类中静态方法parseInt(String s, int radix)
     * radix表示基数,进制数
     */
    public static void function_1() {
        //将二进制数"110"转为整数
        int i = Integer.parseInt("110",2); //可能会出现NumberFormatException异常
        int ii = Integer.parseInt("A",16); //可能会出现NumberFormatException异常
        System.out.println(i);
        System.out.println(ii);
    }

    /**
     * Integer类中静态方法，parseInt(String s）传递一个字符串，返回一个基本类型
     * 要求：字符串必须是数字格式的
     * 前面的数字必须和后面的进制数匹配上。否则会出现NumberFormatException异常
     */
    public static void function() {
        int i = Integer.parseInt("123");  //可能会出现NumberFormatException异常
        System.out.println(i + 3);
    }
}
