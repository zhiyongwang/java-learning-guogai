package com.guo.demo;

import java.util.*;

/**
 * Created by guo on 2018/1/30.
 * 选择List的具体实现：
 * １.安全性问题
 * ２.是否频繁的插入、删除操作
 * ３.是否存储后遍历
 * Collection接口：
 * The root interface in the collection hierarchy.
 * A collection represents a group of objects, known as its elements. Some collections allow duplicate elements and others do not. Some are ordered and others unordered.
 * The JDK does not provide any direct implementations of this interface:
 * it provides implementations of more specific subinterfaces like Set and List. This interface is typically used to pass collections around and manipulate them where maximum generality is desired.
 * List接口：1,有序的，2.允许有多个null元素，3、具体的实现类常用的有：ArrayList，Vector，LinkedList
 * An ordered collection (also known as a sequence).
 * The user of this interface has precise control over where in the list each element is inserted. The user can access elements by their integer index (position in the list),
 * and search for elements in the list.
 * Set接口:
 * A collection that contains no duplicate elements. More formally, sets contain no pair of elements e1 and e2 such that e1.equals(e2), and at most one null element.
 * As implied by its name, this interface models the mathematical set abstraction.
 */
public class ListDemo {

    public static void main(String[] args) {
        //arrayList();
        vector();
    }

    /**
     * LinkedList
     * １.实现原理：采用双向的链表实现
     * ２.适合插入和删除操作，性能高
     *  
     */
    public static void linkedList() {
        LinkedList<String> list = new LinkedList<>();
        list.add("苍老师");
        list.add("张老师");
        list.add("王老师");
        list.add("郭老师");
        int size = list.size();
        for (int i = 0; i < size; i++) {
            System.out.println(list.get(i));
        }
    }

    /**
     * Vector
     * 1.实现原理：采用动态对象数组实现，默认构造方法创建了大小为10的对象数组
     * 2.扩充算法：当增量为0时，扩充为原来大小的两倍。当增量大于0时，扩充为原来大小+增量
     * 3.不适合删除或插入操作
     * 4.为了防止数组动态扩容次数过多，建议创建Vector时，给定初始容量
     * 5.线程安全，适合在多线程访问使用，但效率较低
     */
    public static void vector() {
        Vector<String> v = new Vector<>();
        v.add("苍老师");
        v.add("张老师");
        v.add("王老师");
        v.add("郭老师");
        int size = v.size();
        for (int i = 0; i < size; i++) {
            System.out.println(v.get(i));
        }
    }

    /**
     * ArrayList
     * 1.实现原理，采用动态数组，默认构造方法创建了一个空数组   DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
     * 2.不适合进行删除或插入操作
     * 3.第一次扩容，初始容量增加10，之后的扩充算法：原来数据+原来数组的一半。
     * 4.为了防止数组动态扩容次数过多，建议创建ArayList时，给定初始容量
     * 5.线程不安全，适合在单线程访问使用
     */
    private static void arrayList() {
        //使用集合来存储不同的元素，在一个集合中存储相同的类型对象
        List<String> list = new ArrayList<String>();
        list.add("苍老师");
        list.add("张老师");
        list.add("王老师");
        list.add("郭老师");
        //list.add(10);
        //第一种遍历方式
        //size变量存在栈中，提高了遍历效率
        int size = list.size();
        for (int i = 0; i < size; i++) {
            System.out.println(list.get(i));
        }
        /*//第二种遍历方式
        for(String l : list) {
            System.out.println(l);
        }
        //第三种遍历方式
        Iterator i = list.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }*/
        System.out.println(list.contains("苍老师"));     //true
        int index = list.indexOf("王老师");
        System.out.println(index);
        String s = list.remove(index);
        System.out.println(list);

        //把list转为数组
        String[] array = list.toArray(new String[]{});
        //在把数组转为ArrayList。有病，哈哈。
        List<String> list1 = Arrays.asList(array);
        for (String l : list1) {
            System.out.println(l);
        }
    }
}
