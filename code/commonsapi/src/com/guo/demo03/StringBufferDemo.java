package com.guo.demo03;

import javax.sound.midi.Soundbank;

/**
 * Created by guo on 2018/1/26.
 */
public class StringBufferDemo {
    public static void main(String[] args) {
        function_5();
    }
    /**
     * StringBuffer类的方法
     * String toString 继承Object,重写了toString（）
     * 将缓冲区的所有字符，变成了不可变String字符串
     */
    public static void function_5() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("guo");
        buffer.append("gai");
        System.out.println(buffer.toString());
    }

    /**
     * StringBuffer reverse() 将缓冲区的字符串反转。
     *
     * */
    public static void function_4() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("12345678");

        buffer.reverse();
        System.out.println(buffer);
    }

    /**
     * StringBuffer replace(int start, int end, String str)
     * 将指定的索引范围内的所有字符，替换成新的字符串
     */
    public static void function_3() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("12345678");

        buffer.replace(3,5,"love");
        System.out.println(buffer);
    }

    /**
     * StringBuffer类方法insert
     * StringBuffer insert(int index, 任意类型)
     * 将任意类型的数据，加入到缓冲区指定的索引上
     */
    public static void function_2() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("abcdef");

       buffer.insert(3,9.5);
        System.out.println(buffer);
    }

    /**
     * StringBuffer delete(int start, int end)
     * 删除缓冲区的字符
     */
    public static void function_1() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("abcdef");

        buffer.delete(1,4);
        System.out.println(buffer);
    }

    /**
     * StringBuffer类中append()方法
     * 将任意类型的数据，添加到缓冲区中
     */
    public static void function() {
        StringBuffer buffer = new StringBuffer();
        //调用StringBuffer类的append()方法向缓冲区中追加内容
        //Appends the specified string to this character sequence.
        buffer.append("Hello");
        System.out.println(buffer);
    }
}
