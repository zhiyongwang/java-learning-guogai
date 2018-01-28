package com.guo.demo06;

import javafx.concurrent.Task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用多线程技术，求和
 * 两个线程，一个线程计算1+100，另外一个线程计算1+200的和
 * 多线程的异步计算
 */
public class ThreadpoolDemo {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Integer> f1 = es.submit(new GetSumCallable(100));
        Future<Integer> f2 = es.submit(new GetSumCallable(200));
        try {
            System.out.println(f1.get());
            System.out.println(f2.get());
            //销毁线程池
            es.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
