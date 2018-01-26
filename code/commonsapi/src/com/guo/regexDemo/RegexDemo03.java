package com.guo.regexDemo;

/**
 * Created by guo on 2018/1/27.
 */
public class RegexDemo03 {
    public static void main(String [] args) {
        checkMail();
    }
    /**
     * 检查邮件地址是否合法
     * 规则：
     *  1234567@qq.com
     *  haohaojiayou@gmail.com
     *  @ ：前面可以是数字，字母，_组成。个数不能少于一个
     *  @ ：后面 数字，字母，个数不能少于一个，
     *  . : 后面 可以是com、jp、hk
     */
    public static void checkMail() {
        String email = "guoxx123@gmail.com.cn";
        boolean  b = email.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
        System.out.println(b);
    }
}
