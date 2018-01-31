package com.guo.demo02;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by guo on 2018/1/31.
 * 迭代器的并发修改异常  java.util.ConcurrentModificationException
 * 在遍历的过程中，使用集合的方法修改了集合的长度，这是不允许的
 */
public class ListDemo01 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(10);
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");
        list.add("ee");
        //对集合使用迭代器进行遍历,获取的时候判断集合中是否存在“cc”元素
        //如果有，添加一个元素“aa”
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String s = it.next();
            //对获取的元素s，进行判断，是不是有"cc"
            if(s.equals("cc")){
                list.add("cc");     //此时会出现ConcurrentModificationException异常
                System.out.println(s);
            }
        }
    }
}
