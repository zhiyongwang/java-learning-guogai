package com.guo.demo05;

import java.util.concurrent.Callable;

public class ThreadPoolCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "guo";
    }
}
