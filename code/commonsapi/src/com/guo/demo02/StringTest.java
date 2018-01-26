package com.guo.demo02;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

/**
 * Created by guo on 2018/1/26.
 */
public class StringTest {
    public static void main(String[] args) {
        String str = "abc1%2DEFG";
        getCount(str);
        System.out.println(toConvert("ab123cdEFG"));
        System.out.println("出现字符串的次数为：" + getStringConunt("abcABCabcEFGabc","abc"));
    }
    /**
     * 获取一个字符串中另一个字符串出现的次数
     * 思路：
     *      1、indexOf()到字符串中第一次出现的位置
     *      2、找到索引+被找字符串的长度，截取字符串
     *      3、计数器++
     */
    public static int getStringConunt(String str,String key) {
        //定义计数器
        int count = 0;
        //定义变量，保存indexOf查找后的索引的结果
        int index = 0;
        //开始循环，条件indexOf == -1 ，字符串就没有了
        while((index = str.indexOf(key)) != -1) {
            count++;
            //获取到的索引和字符串的长度求和，截取字符串
            str = str.substring(index + key.length());
        }
        return count;
    }


    /**
     * 将字符串的首字母转成大写，其它转成小写，并输出
     * 思路：
     *      1、获取首字母，charAt(0) subString(0,1)包含头，不包含尾,转成大写，toUpperCase()
     *      2、获取其余字符串subString(1) 转成小写toLowerCase();
     */
    public static String toConvert(String str) {
        //定义两个变量，保存首字母和剩余字符
        String first =  str.substring(0,1);
        String after = str.substring(1);
        //调用String的toUpperCase()和toLowerCase()
        first = first.toUpperCase();
        after = after.toLowerCase();
        return first + after;
    }

    /**
     * 获取指定字符串中，大写字母，小写字母，数字的个数
     * 思路：
     *    1、计数器，就是int变量，当满足一个条件时就让它++
     *    2、遍历字符串，用长度方法length() + charAt() 遍历
     *    3、字符判断是大写，是小写，还是数字。
     */
    public static void getCount(String str) {
        //定义三个变量，计数
        int upper = 0;
        int lower = 0;
        int digit = 0;
        //对字符进行遍历
        for (int i = 0; i < str.length(); i++) {
            //String类的chatAt()方法获取字符的索引
            char c = str.charAt(i);
            //利用编码表，65-90是大写字母，97-122，是小写 48-57是数字。
            if(c >='A' && c <=90) {
                upper++;
            }else if (c >=97 && c <=122) {
                lower++;
            }else if (c >= 48 && c <='9') {
                digit++;
            }
        }
        System.out.println("大写的个数：" + upper);
        System.out.println("小写的个数：" + lower);
        System.out.println("数字的个数：" + digit);
    }
}
