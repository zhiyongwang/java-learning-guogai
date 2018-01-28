package com.guo.demo02;

/**
 * Created by guo on 2018/1/28.
 * 继承Thread方式的线程
 */
public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
//        for(int i = 0; i < 5; i++) {
//            Thread.sleep(1000);
//            System.out.println(i);
//        }
       new SleepThread().start(); //通过匿名对象创建
    }
}
