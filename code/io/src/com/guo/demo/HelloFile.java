package com.guo.demo;

import java.io.File;

/**
 * Created by guo on 2018/1/30.
 * 认识File对象
 */
public class HelloFile {
    public static void main(String[] args) {
        //windows系统中
        System.out.println(File.separator);   //输出\
        System.out.println(File.pathSeparator); //输出;

        //在Unix体系系统中
         System.out.println(File.separator);   //输出/
        System.out.println(File.pathSeparator); //输出:
        //\t表示Tab,\n表示换行，\表示转义符
        System.out.println("越努力，\t越幸\n运！");

        //在Win中路径表示方法
        String path = "I:\\Java\\code";

        //在NUIX系统中
        String path1 = "I:/Java/code";

        //官方建议
        String path2 = "I" + File.pathSeparator + File.separator + "Java" + File.separator + "code";
    }
}
