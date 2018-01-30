package com.guo.demo01;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by guo on 2018/1/31.
 * Map接口实现类Hashtable
 *  底层使用哈希表。特点和HashMap是一样的，
 *  不同的是Hashtable线程安全的，效率比较低。子类Propeties很有用。
 *
 *  HashMap允许存储null键null值，而hashtable不允许
 */
public class HashtableDemo {
    public static void main(String[] args) {
       /* Map<String,String> table = new Hashtable<>();
        table.put(null,null);        //java.lang.NullPointerException
       System.out.println(table);*/

        Map<String,String> map = new HashMap<>();
        map.put(null,null);
        System.out.println(map);   //{null=null}

    }
}
