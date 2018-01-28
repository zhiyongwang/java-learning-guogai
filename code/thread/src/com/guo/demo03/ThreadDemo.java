package com.guo.demo03;

/**
 * Created by guo on 2018/1/28.
 * 实现接口方式的线程
 *  创建Thread类对象，构造方法中，传递Runnable接口的实现类。
 *  调用Thread类的start()；
 *  实现Runnable接口，避免了继承Thread类带来的单继承局限性。更加符合面向对象的特点
 */
public class ThreadDemo {
    public static void main(String[] args) {
        SubRunnable sr = new SubRunnable();
        Thread t = new Thread(sr);
        t.start();
        for(int i = 0; i < 5; i++) {
            System.out.println("main..." + i);
        }
    }
}
