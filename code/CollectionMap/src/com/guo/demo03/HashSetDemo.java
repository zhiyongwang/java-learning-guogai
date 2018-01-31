package com.guo.demo03;

import java.net.SocketTimeoutException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by guo on 2018/1/31.
 * Set接口，特点不重复元素，没有索引
 * Set接口的实现类，hashSet(哈希表)
 * 特点：无序集合，存储和取出顺序不同，没有索引，不存储重复元素
 * 代码的编写上，和ArrayList一样
 */
public class HashSetDemo {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>(10);
        set.add("11");
        set.add("22");
        set.add("33");
        set.add("33");
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());  //[11, 22, 33]
        }
        System.out.println("==================================");
        //第二种遍历方法
        for(String s : set) {
            System.out.println(s);
        }
        System.out.println("==================================");

        //第三种遍历方法
        set.forEach((s) -> System.out.println(s));
    }
}
