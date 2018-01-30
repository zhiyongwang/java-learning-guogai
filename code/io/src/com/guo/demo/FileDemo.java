package com.guo.demo;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by guo on 2018/1/30.
 * FIle表示一个文件或一个文件路径
 * 一旦File对象创建成功，只能表示指定的路径 如：I:\Java\code\README.md
 */
public class FileDemo {
    public static void main(String[] args) throws IOException {
        //创建File对象，通过构造器File(String pathname)   Creates a new File instance by converting the given pathname string into an abstract pathname.
        File filePath1 = new File("I:\\Java\\code\\README.md");
        System.out.println(filePath1);
        System.out.println("--------------------------------------------");
        //File(String parent, String child)   Creates a new File instance from a parent pathname string and a child pathname string.
        File  filePath2 = new File("I\\Java\\code","README.md");
        System.out.println(filePath2);

        System.out.println("--------------------------------------------");

        //用File对象表示文件夹
        File dir  = new File("I\\Java\\code");
        File filePath3 = new File(dir, "README.md");
        System.out.println(filePath3);

        System.out.println("--------------------------------------------");



        //判断是文件夹(目录)还是文件
        if (filePath1.isDirectory()) {
            System.out.println("是目录");
        }else if (filePath1.isFile()) {
            System.out.println("是文件");
        }

        //判断文件是否存在 不存在则创建 Tests whether the file or directory denoted by this abstract pathname exists.
        if(filePath1.exists()) {
            System.out.println("文件存在");
        }else {
            //判断是文件夹还是目录，
            if(!filePath1.isFile()) {
                //创建文件
                filePath1.createNewFile();
                System.out.println("文件创建Success");
            }else{
                //创建目录 创建多级目录要用mkdirs()
                filePath1.mkdir();
            }
        }
        //获取文件名称  Returns the name of the file or directory denoted by this abstract pathname
        System.out.println(filePath1.getName());
        //获取文件的相对路径  Converts this abstract pathname into a pathname string.
        System.out.println(filePath1.getPath());
        //获取文件的绝对路径 Returns the absolute pathname string of this abstract pathname.
        System.out.println(filePath1.getAbsolutePath());
        //获取文件的父目录  Returns the pathname string of this abstract pathname's parent, or null if this pathname does not name a parent directory.
        System.out.println(filePath1.getParent());

        //修改文件名称，不要通过File构造方法完成
        filePath1.renameTo(new File("README111.md"));

        //获取文件最后修改的时间
        long time = filePath1.lastModified();
        Date dateTime = new Date(time);
        System.out.println(dateTime);    //重写过toString方法

        //对日期对象进行格式化输出
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String s = df.format(time);
        System.out.println(s);


    }
}
