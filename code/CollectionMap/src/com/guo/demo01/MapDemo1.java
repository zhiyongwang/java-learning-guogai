package com.guo.demo01;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.security.Key;
import java.util.*;
import java.util.function.Consumer;

/**
 * Created by guo on 2018/1/31.
 * Map集合的遍历
 *      利用键获取值
 *      Map接口中定义方法keySet
 *      把集合中所有的键存储到Set集合中，通过集合中的键找到Map中的值
 */
public class MapDemo1 {
    public static void main(String[] args) {
        /**
         * 1.调用map集合的方法keySet，所有的键存储到Set集合中
         * 2.遍历Set集合，获取Set集合中所有的元素(Map中的键)
         * 3.调用map集合方法get，通过键获取到值
         */
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("e",111);
        map.put("f",222);
        map.put("g",333);

        //1.调用map集合的方法keySet，所有的键存储到Set集合
        Set<String> set = map.keySet();
        System.out.println(set.getClass());    // class java.util.HashMap$KeySet  是HashMap的一个内部类实现的
        //2.遍历Set集合，获取Set集合中所有的元素key
       /* for (String key : set) {          //实现细节上使用的是iterator接口和它的hasNext()，next()方法。
            //3.调用map集合方法get，通过键取得值
            Integer value = map.get(key);
            System.out.println(key + "..." + value);
        }*/
       //第二种遍历方法 通过迭代器模式
        /*Iterator<String> it = set.iterator();
        while(it.hasNext()) {
            //it.next()返回是Set集合元素，也就是Map集合中的键
            String key = it.next();
            Integer value = map.get(key);
            System.out.println(key + "..." + value);
        }*/
        //第三种遍历  forEach方法会接受一个Consumer接口类型作为参数，该接口是一个函数式接口(Functional Interface)，它是内部遍历器的实现方式。
       /* set.forEach((new Consumer<String>() {
            @Override
            public void accept(String key) {
                Integer value = map.get(key);
                System.out.println(key + "...." + value);
            }
        }));*/

       //第四种遍历  使用JDK1.8新特性Lambda表达式
        //set.forEach(( String key) -> System.out.println(key + "..." + map.get(key)));
        //set.forEach(key -> System.out.println( key + "..." + map.get(key)));
        //最终版本
        set.forEach(System.out :: println);
    }

}
