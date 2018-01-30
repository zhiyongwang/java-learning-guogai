package com.da.study01;

/**
 * Created by guo on 2018/1/30.
 */
public class Synchronized {
    public static void main(String[] args) {
        //对synchronized Class对象进行加锁
        synchronized (Synchronized.class) {
        }
        //静态同步方法，对Synchronized Class对象进行加锁
        m();
    }
    public static synchronized void m() {

    }
}
