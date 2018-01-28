package com.gai.demo03;

/**
 * 多个线程并发访问同一个数据资源
 * 3个线程，对一个票资源出售
 */
public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        //创建Runable接口实现类
        Tickets t = new Tickets();
        //创建三个Thread类对象，传递Runnable接口的实现类
        Thread t0 = new Thread(t);
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t0.start();
        t1.start();
        t2.start();
    }
}
