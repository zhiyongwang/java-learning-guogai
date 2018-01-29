package com.da.study;

/**
 * Created by guo on 2018/1/29.
 * 以下代码会引起死锁，是线程t1和t2互相等待对方释放锁
 */
public class DeadLockDemo {
    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
                   deadLock();
    }

    public static   void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        System.out.println("1");
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                       synchronized (B) {
                           synchronized (A) {
                               System.out.println("2");
                           }
                       }
            }
        });
        t1.start();t2.start();
    }
}
