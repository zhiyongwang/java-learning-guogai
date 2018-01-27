package com.gai.demo01;

/**
 * Created by guo on 2018/1/27.
 * JDK1.5后出现的特性，自动装箱和自动拆箱
 * 自动装箱：基本数据类型转直接变为对象，自动完成
 * 自动拆箱：对象中的数据变回基本类型
 */
public class IntegerDemo02 {
    public static void main(String[] args) {
        function();
        function_1();
    }

    /**
     * 一些自动拆箱装箱的题目
     */
    public static void function_1() {
        Integer i = new Integer(1);
        Integer j = new Integer(1);
        //比较的是内存地址的值
        System.out.println(i == j);         //false
        //比较的是内容
        System.out.println(i.equals(j));    //true  继承Object，重写了equals()方法

        System.out.println("---------------------");

        Integer a = 500;
        Integer b = 500;
        System.out.println(a==b);   //false
        System.out.println(a.equals(b)); //true

        System.out.println("---------------------");

        //数据在byte范围内，JVM不会重新new对象，直接拿来主义，为了节省内存
        //
        Integer c = 127;   //Integer  = new Integer(127);
        Integer d = 127;    //c==d
        System.out.println(c==d);   //true
        System.out.println(c.equals(d)); //true

    }

    public static void function() {
        //引用类型，引用变量直接指向对象
        //自动装箱，基本数据类型1直接变成了对象
        Integer in = 1;  //Integer in = new Integer(1)
        //in是引用类型，不能和基本类型进行运算，但这里用到了自动拆箱
        // in + 1 ==> in.intValue()
        in = in +1;
        System.out.println(in);
    }

    /**
     * ArrayList<Integer> ar = new ArrayList</Integer>;
     * add(1); 不会报错，会自动装箱
     */
}
