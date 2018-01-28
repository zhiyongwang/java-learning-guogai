package com.guo.demo;

/**
 * Created by guo on 2018/1/28.
 * 创建和启动线程
 * 创建Thread子类对象，
 * 子类对象调用start()方法
 *      让线程执行，JVM调用线程中的run()方法
 *
 */
public class ThreadDemo {
    public static void main(String[] args) {
        SubThread sub = new SubThread();
        //sub.run();
        sub.start();
        for(int i = 0; i < 10; i++) {
            System.out.println("main---唉，IDEA好难优化设置啊！");
        }
    }
}
