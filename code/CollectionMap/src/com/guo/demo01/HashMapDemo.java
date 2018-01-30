package com.guo.demo01;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by guo on 2018/1/31.
 * 使用HashMap存储自定义的对象
 * 自定义对象作为键出现，也可以是值
 */
public class HashMapDemo {
    public static void main(String[] args) {
        function_1();
    }

    /**
     * HashMap自定义的Person作为键出现
     * 键的对象是Person，值是字符串
     * 保证键的唯一性，存储键的对象需要重写hashCode()、equals()
     */
    public static void function_1() {
        Map<Person, String> map = new HashMap<Person, String>();
        map.put(new Person("gai", 24), "努力");
        map.put(new Person("guo", 25), "加油");
        map.put(new Person("guo", 25), "加油");
        map.put(new Person("da", 26), "越努力，越幸运");
        for (Map.Entry<Person, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "..." + entry.getValue());
        }
        System.out.println("===================================");
        for (Person key : map.keySet()) {
            System.out.println(key + "..." + map.get(key));
        }
    }

    /**
     * HashMap存储自定的Person作为值出现
     * 键的对象可以是字符串，可以确保唯一性
     */
    public static void function() {
        Map<String, Person> map = new HashMap<String, Person>();
        map.put("1", new Person("da", 21));
        map.put("2", new Person("guo", 22));
        map.put("3", new Person("gai", 23));
        System.out.println(map);
        for (Map.Entry<String, Person> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "..." + entry.getValue());
        }
    }
}
