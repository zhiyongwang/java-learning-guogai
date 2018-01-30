package com.guo.demo02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by guo on 2018/1/31.
 * Collections工具类的使用
 */
public class CollectionsDemo {
    public static void main(String[] args) {
        function_2();
    }
    /**
     * Collections.shuffle方法
     * 对List集合中的元素随机排列
     */
    public static void function_2() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println("随机之前："+ list);
        //调用Collectionde的shuffle方法，对集合随机排列
        Collections.shuffle(list);
        System.out.println("随机之后" + list);
    }

    /**
     * Collections.binarysearch()
     * 对List集合进行二分查找，传递List集合，被查找的元素
     */
    public static void function_1() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(22);
        list.add(23);
        list.add(41);
        list.add(35);
        list.add(63);
        //调用工具类的binarySearch()进行二分查找
        int index = Collections.binarySearch(list, 21);
        System.out.println(index);
    }

    /**
     * Collections.sort()
     *  对List集合进行升序排列
     */
    public static void function() {
        List<String> list = new ArrayList<>();
        list.add("saf");
        list.add("qwrq");
        list.add("rq");
        System.out.println(list);
        //调用集合工具类的方法sort()
        Collections.sort(list);
        System.out.println(list);
    }
}
