package com.guo.demo01;

/**
 * Created by guo on 2018/1/28.
 * 获取线程的名字，父类Thread方法
 * String 	getName()
 */
public class NameTread extends Thread {
    public NameTread() {
        super("guo");
    }
    @Override
    public void run() {
        System.out.println(super.getName());
    }
}
