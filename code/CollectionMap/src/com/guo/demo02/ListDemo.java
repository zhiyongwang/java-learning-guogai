package com.guo.demo02;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guo on 2018/1/31.
 * List接口派系，继承Collection接口
 * List接口特点：有序，索引，可以有重复元素
 * 接口常用实现类：ArrayList、LinkedList
 * <p>
 * List接口中的抽象方法，有一部分和他的父接口Collection是一样的
 * List接口特有的方法，带有索引的功能
 */
public class ListDemo {
    public static void main(String[] args) {
        function_2();
    }

    /**
     * E set(int index,E)  Replaces the element at the specified position in this list with the specified element.
     * 修改指定索引上的元素
     * 返回修改之前的元素
     */
    public static void function_2() {
        List<Integer> list = new ArrayList<>(10);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list);
        Integer i = list.set(2, 5);
        System.out.println(i);
        System.out.println(list);
    }


    /**
     * E remove(int index)    Removes the element at the specified position in this list.
     * 移除指定索引上的元素
     * 返回删除之前的元素
     */
    public static void function_1() {
        List<Double> list = new ArrayList<>();
        list.add(1.1);
        list.add(2.1);
        list.add(3.1);
        list.add(4.1);
        System.out.println(list);

        Double d = list.remove(3);
        System.out.println(d);       //4.1
        System.out.println(list);

    }

    /**
     * add(int index,E)    Inserts the specified element at the specified position in this list.
     * 将元素插入到指定索引上
     * 带有索引的操作，需要防止越界问题
     * IndexOutOfBoundsException:索引越界异常
     * ArrayOutOfBoundsException：数组越界异常
     * StringOutOfBoundsException  :字符串越界异常
     */
    public static void function() {
        List<String> list = new ArrayList<>(4);     //可能会出现IndexOutOfBoundsException
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");
        list.add(5, "guo");  // IndexOutOfBoundsException: Index: 5, Size: 4
        list.add(2, "gai");
        list.add(6, "da");
        System.out.println(list);        //[aa, guo, gai, da, bb, cc]
    }
}
