package com.atguigu.java;
//实现字符串的反转，你有几种方法？
//时间复杂度  空间复杂度
public class TestString1 {
    public static void main(String[] args) {
        String str1 = reverse("helloworld");
        System.out.println(str1);

        String str2 = reverse1("helloworld");
        System.out.println(str2);

        String str3 = reverse2("helloworld");
        System.out.println(str3);
    }

    //方法三：
    public static String reverse2(String str){
        StringBuffer sb = new StringBuffer();
        for(int i = str.length()-1;i >= 0;i--){
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    //方法二:
    public static String reverse1(String str){
        StringBuffer sb = new StringBuffer(str);
        return sb.reverse().toString();
    }
    //方法一：
    public static String reverse(String str){
        char[] c = str.toCharArray();
        for(int x = 0,y = c.length-1;x < y;x++,y--){
            char temp = c[x];
            c[x] = c[y];
            c[y] = temp;
        }
        return new String(c);
    }
}
