package com.guo.demo03;

/**
 * Created by guo on 2018/1/31.
 * 对象的哈希值，普通的十进制整数
 * 父类Object，方法public void hashCode()计算结果是一个int类型的整数
 *
 * equals方法早非空队形引用啥概念实现相等关系
 *  自反性，对于任何非空引用值x, x.equals(x) 都应该返回true
 *  对称性：对于任何非空引用值x，y,当且仅当y.equals(x)返回true时，x.equals(y)才应该返回true
 *  传递性：对于任何非空引用值 x、y 和 z，如果 x.equals(y) 返回 true，并且 y.equals(z) 返回 true，那么 x.equals(z) 应返回 true。
 *  一致性：对于任何非空引用值 x 和 y，多次调用 x.equals(y) 始终返回 true 或始终返回 false，前提是对象上 equals 比较中所用的信息没有被修改
 *  对于任何引用空值x，e.equals(null),都应该返回false；
 */
public class HashDemo {
    public static void main(String[] args) {
        Person p = new Person();
        //调用父类Object，如果重写了则调用子类的方法
        int i = p.hashCode();    //1956725890
        System.out.println(i);

        String s1 = new String("abc");
        String s2 = new String("abc");
        int i1 = s1.hashCode();
        int i2 = s2.hashCode();
        System.out.println(i1 == i2);        //true
        System.out.println(i1);             //96354

        /**
         * String类继承Object类，重写父类的方法hashCode
         * public int hashCode() {
             int h = hash;
             if (h == 0 && value.length > 0) {
             char val[] = value;

             for (int i = 0; i < value.length; i++) {
             h = 31 * h + val[i];
             }
             hash = h;
             }
             return h;
         }
         */

    }
}
