package com.guo.demo01;

import java.util.LinkedHashMap;

/**
 * Created by guo on 2018/1/31.
 * LinkedHashMap继承自HashMap
 * 保证了迭代的顺序，都属于线程不安全
 */
public class LinkedHashMapDemo {
    public static void main(String[] args) {
        LinkedHashMap<String,String> link = new LinkedHashMap<>();
        link.put("1","a");
        link.put("11","aa");
        link.put("111","aaa");
        System.out.println(link);
    }
}
