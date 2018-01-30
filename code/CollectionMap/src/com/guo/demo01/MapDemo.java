package com.guo.demo01;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by guo on 2018/1/30.
 * Map接口中的常用方法  An object that maps keys to values
 *      使用Map接口的实现类HashMap
 */
public class MapDemo {
    public static void main(String[] args) {
        function_2();
    }
    /**
     * 移除集合中的键值对  ，返回被移除之前的值
     * remove(Object key)
     * Removes the mapping for a key from this map if it is present (optional operation)
     */
    public static void function_2() {
        //创建集合对象，
        Map<String,Integer> map = new HashMap<String ,Integer>();
        map.put("c",11);
        map.put("d",22);
        map.put("e",33);
        Integer d = map.remove("d");
        System.out.println(d);
        System.out.println(map.get("d"));    //null
    }

    /**
     * 通过键，获取值对象
     * V get(k)
     * 如果集合中没有这个键，则返回null，
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     */
    public static void function_1() {
        //创建集合对象，
        Map<String,Integer> map = new HashMap<String ,Integer>();
        map.put("c",11);
        map.put("d",22);
        map.put("e",33);
        System.out.println(map.get("d"));
    }

    /**
     * 将键值对存储到集合中
     * put(K key, V value) K 作为键的对象，V作为值的对象
     * 存储是重复键的话，会覆盖原有的值。值是允许重复的
     * 返回值一般情况下会返回null
     * 存储重复键的时候，会返回之前被覆盖的值
     * Associates the specified value with the specified key in this map (optional operation).
     */
    public static void function() {
        //创建集合对象，HashMap,存储对象，键是字符串，值是整数
        Map<String ,Integer> map = new HashMap<String ,Integer>();
        map.put("a",1);
        map.put("b",2);
        map.put("c",4);
        Integer i = map.put("c",5);  //会把之前的值替换掉
        System.out.println(i);   //4
        map.put("",6);   //HashMap中允许一个键可以为null，键不限制
        System.out.println(map);

    }
}
