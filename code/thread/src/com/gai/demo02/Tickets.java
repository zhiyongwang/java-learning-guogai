package com.gai.demo02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用JDK1.5的接口Lock，替换掉同步代码块，提高线程的安全性
 * Lock接口方法：
 * lock() 获取锁
 * unlock()释放锁
 * 实现类：ReentrantLock
 */
public class Tickets implements Runnable {
    //定义出售的票源
    private int ticket = 10;
    //在类的成员变量位置，创建Lock接口的实现类
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            //调用Lock接口的lock()方法，获取锁
            lock.lock();
            //对票数判断，如果大于0，可以出售，变量进行--操作
            if (ticket > 0) {
                try {
                    Thread.sleep(10);
                    System.out.println(Thread.currentThread().getName() + "出售第：" + ticket-- + "票");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放锁，调用Lock接口的unlock()
                    lock.unlock();
                }
            }
        }
    }
}
