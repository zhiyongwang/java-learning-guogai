package com.guo.demo03;

import java.util.HashSet;

/**
 * Created by guo on 2018/1/31.
 *  HashSet集合自身的特点
 *      底层的数据结构是哈希表，
 *      存储，取出都比较快
 *      线程不安全，意味着效率好，
 */
public class HashSetDemo01 {
    public static void main(String[] args) {

        /**
         * new String("abc")
         * 1.调用对象的hashCode方法，new String("abc").hashCode()    == 96354
         * 2.集合在容器内找，有没有和96354一样的哈希值，
         * new String("abc")
         * 3.调用对象的hashCode方法，new String("abc").hashCode()    == 96354
         * 4.集合在啊容器内，找到了一个对象也是96354
         * 5.集合会让后来的new String("abc")调用对象的equals(已经有的对象)
         * 5.两个对象哈希值一样，equals方法返回true，集合判断元素重复，
         * new String("adc)
         * 集合调用对象的hashCode方法 new String("adc").hashCode()=  96354
         * 集合去容器中找，有没有96354的对象，找到了
         * 集合让后来的对象 new String("adc").equals(已存在的对象)
         * 两个对象的哈希值一样，equals返回false
         * 集合判定对象没有重复，因此采用桶的方式
         *
         *
         */
        HashSet<String> set = new HashSet<>();
        set.add(new String("abc"));
        set.add(new String("abc"));
        set.add(new String("bbc"));
        set.add(new String("bbc"));
        System.out.println(set);          //[bbc, abc]

        HashSet<Person> setPerson = new HashSet<>();
        setPerson.add(new Person("da",21));
        setPerson.add(new Person("da",21));
        setPerson.add(new Person("guo",22));
        setPerson.add(new Person("gai",23));
        System.out.println(setPerson);

    }
}
