package com.guo.demo;

import java.io.File;

/**
 * Created by guo on 2018/1/30.
 * 列出目录中的子文件
 */
public class FileDemo1 {
    public static void main(String[] args) {
        String filePath = "I:\\Java\\code";
        //创建File对象
        File f = new File(filePath);
        listAllFiles(f);

       /* //列出电脑的盘符
        File[] roots = File.listRoots();
        for (File r : roots) {
            System.out.println(r);
            listAllFiles(r);  //递归操作，列出电脑的所有目录和文件。会出现java.lang.NullPointerException
        }*/

       
       /* //获取当前目录下的所有文件，包括目录  File[] 	listFiles()
        File[] files = f.listFiles();
        //循环遍历数组
        for (File file : files) {
            System.out.println(file.getName());
        }*/
    }
    //获取一个指定目下中的所有文件以及子目录中的文件
    public static void listAllFiles(File file) {
        System.out.println(file);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                //递归调用
                listAllFiles(f);
            }
        }
    }
}
