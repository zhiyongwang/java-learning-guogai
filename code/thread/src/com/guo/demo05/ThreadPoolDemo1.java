package com.guo.demo05;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 实现线程的第三种方式，实现Callable接口方式
 * 实现步骤：
 *        1.使用工厂类Executors中的静态方法，创建一个线程池对象，指定线程的个数
 *        static ExecutorService 	newFixedThreadPool(int nThreads)
 *          返回的是ExecutorService接口的实现类(线程池对象)
 *          接口实现类调用方法submite(Callable c)提交线程执行的任务
 */
public class ThreadPoolDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(4);
        //提交线程任务的submite()方法返回Future接口的实现类
        Future<String> f = es.submit(new ThreadPoolCallable());
        //Waits if necessary for the computation to complete, and then retrieves its result.
        System.out.println(f.get());
    }
}
