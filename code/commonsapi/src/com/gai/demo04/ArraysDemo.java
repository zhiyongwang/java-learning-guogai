package com.gai.demo04;

import java.util.Arrays;

/**
 * Created by guo on 2018/1/27.
 */
public class ArraysDemo {
    public static void main(String[] args) {
        function();
        function_1();
        function_2();
    }

    /**
     *  static String toString(数组)
     *  将数组变成字符串
     */
    public static void function_2(){
        int[] arr = {5,1,4,6,8,9,0};
        String s = Arrays.toString(arr);
        System.out.println(s);
    }

    /**
     *  static int binarySearch(数组, 被查找的元素)
     *  数组的二分搜索法
     *  返回元素在数组中出现的索引
     *  元素不存在, 返回的是  (-插入点-1)
     */
    public static void function_1(){
        int[] arr = {1,4,7,9,11,15,18};
        int index =  Arrays.binarySearch(arr, 10);
        System.out.println(index);
    }

    /**
     *  static void sort(数组)
     *  对数组升序排列
     */
    public static void function(){
        int[] arr = {5,1,4,6,8,9,0};
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}


