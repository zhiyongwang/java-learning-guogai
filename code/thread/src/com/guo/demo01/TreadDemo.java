package com.guo.demo01;

/**
 * Created by guo on 2018/1/28.
 * 每个线程都有自己的名字
 * 运行方法main线程，名字就是“main“
 * 其他线程也有自己的名字，默认Thread-0
 * Thread类中静态方法
 * static Thread currentThread()   Returns a reference to the currently executing thread object.
 */
public class TreadDemo {
    public static void main(String[] args) {
        // System.out.println(0/0);  //thread "main" java.lang.ArithmeticException: / by zero
        NameTread nt = new NameTread();
        //nt.run();   //thread "main" java.lang.ArithmeticException: / by zero
       nt.setName("guo开启的线程");  //用子类对象对开启,也可以通过子类构造器改名字
        nt.start(); //hread "Thread-0" java.lang.ArithmeticException: / by zero
        //静态不允许调用非静态代码
        //返回正在执行的线程名字
        System.out.println(Thread.currentThread().getName());
    }
}
