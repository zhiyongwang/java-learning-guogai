package com.guo.demo05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * JDK1.5新特性，实现线程池程序
 * 使用工厂类Executors中的静态方法，创建一个线程池对象，指定线程的个数
 * static ExecutorService 	newFixedThreadPool(int nThreads)
 *返回的是ExecutorService接口的实现类(线程池对象)
 * 接口实现类调用方法submite(Runnable r)提交线程执行的任务
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        //调用工厂类的静态方法，创建线程池对象引用
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //java.util.concurrent.ThreadPoolExecutor@14ae5a5[Running, pool size = 0, active threads = 0, queued tasks = 0, completed tasks = 0]
        System.out.println(executorService);
        //调用接口实现类对象executorService中的方法submite()提交任务
        //将Runable接口实现类对象直接传递进去
        executorService.submit(new ThreadPoolRunnable());  //pool-1-thread-1
        executorService.submit(new ThreadPoolRunnable());

    }
}
