package com.guo.demo02;

import java.util.LinkedHashSet;

/**
 * Created by guo on 2018/1/31.
 * LinkedhashSet 基于链表的哈希表
 * 继承自HashSet
 * LinkedhashSet自身的特点呢：具有顺序，存储和取出的顺序一样
 * 线程不安全，运算速度较快
 */
public class LinkedHashSetDemo {
    public static void main(String[] args) {
       LinkedHashSet<Integer> link = new LinkedHashSet();
       link.add(123);
       link.add(123);
       link.add(345);
       link.add(456);
       link.add(457);
        System.out.println(link);  //[123, 345, 456, 457] 不允许有重复元素，取出和插入顺序一样

        
    }
}
