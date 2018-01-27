package com.gai.demo02;

/**
 * Created by guo on 2018/1/27.
 */
public class Person {
    public void finalize() {
        System.out.println("垃圾收取了");
    }
}
