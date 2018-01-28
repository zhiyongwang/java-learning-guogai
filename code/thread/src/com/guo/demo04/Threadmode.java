package com.guo.demo04;

/**
 * Created by guo on 2018/1/28.
 * 使用匿名内部类，实现多线程程序
 * 前期：继承或接口实现
 * new 父类对象或者接口() {
 *     重写父类方法
 * }
 */
public class Threadmode {
    public static void main(String[] args) {
        //继承方式 Xxx extends Thread{public void run() {}}
        new Thread() {
            @Override
            public void run() {
                for(int i = 0; i < 20; i++) {
                    System.out.println("run...." + i);
                }
            }
        }.start();

        //实现Runnable接口 Xxx implements Runnable {public void run() {} }
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("guo1");
            }
        }).start();
    }
}
