package com.guo.demo02;

/**
 * Created by guo on 2018/1/26.
 */
public class StringDemo2 {
    public static void main(String[] args) {
        String str2 = new String("guo");
        String str1 = "guo";  //检查下常量池中是否有该字符串，str2直接指向了str1
        System.out.println(str1 == str2); //false 引用数据类型，比较的是内存的地址
        //String类继承Object，重写了父类的equals()方法，建立了自己的比较方式，字符串中每个字符是否相同
        System.out.println(str1.equals(str2)); //ture

        /**
         * public boolean equals(Object anObject) {
             if (this == anObject) {
             return true;
         }
         if (anObject instanceof String) {
             tring anotherString = (String)anObject;
             int n = value.length;
             if (n == anotherString.value.length) {
             char v1[] = value;
             char v2[] = anotherString.value;
             int i = 0;
             while (n-- != 0) {
             if (v1[i] != v2[i])
             return false;
             i++;
         }
         return true;
         }
         }
         return false;S
         }
         */
    }
}
