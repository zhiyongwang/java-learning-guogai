package com.guo.demo03;


public class StringBufferTest {
    public static void main(String[] args) {
    int[] arr = {12,34,56,78};
        System.out.println(toString(arr));
    }
    /**
     * Created by guo on 2018/1/26.
     * int[] arr = {12,34,56,78};将int[]中元素转化成字符
     * 格式[12，34，56，78]buffer.append(arr[i]).append("]");
     * String s = "["
     * s += arr[i];
     * s + "[";
     * StringBuffer实现，节约内存空间，String + 在缓冲区中相当于append()方法
     */
    public static String toString(int[] arr) {
        //创建字符缓冲区
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        for (int i =0; i <arr.length; i++) {
            //判断是否是数据的最后一个e
            if(i == arr.length-1) {
                buffer.append(arr[i]).append("]");
            }else {
                buffer.append(arr[i]).append(",");
            }
        }
        return buffer.toString();
    }
}
