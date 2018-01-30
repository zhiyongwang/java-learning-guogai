package com.guo.demo01;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by guo on 2018/1/31.
 *  Map集合获取方式
 *  entrySet方法，键值对映射关系获取
 *  实现步骤 ：
 *      1.调用map集合方法entrySet()将集合中的映射关系对象，存储到Set中 Set<Map.Entry<String,Integer>
 *       2.迭代Set集合
 *       3.获取出的Set集合元素是映射关系对象
 *       4.通过映射关系中的方法getKey(),getValue()来取出键和值
 *
 */
        public class MapDemo2 {
            public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("e",111);
        map.put("f",222);
        map.put("g",333);
       // 1.调用map集合方法entrySet()将集合中的映射关系对象，存储到Set中
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        //2.对Set集合循环遍历， 也可以把entries直接替换成map.entrySet()
        for (Map.Entry<String,Integer> entry : entries) {
            //3.取出映射关系的键和值
            System.out.println(entry.getKey() + "...." + entry.getValue());

        }


        //另外一种，调用map集合的values方法，
        Collection<Integer> values = map.values();
        for (Integer value : values)  {
            System.out.println(value);
        }

    }
}
