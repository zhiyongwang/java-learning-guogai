package com.guo.demo;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

/**
 * Created by guo on 2018/1/30.
 * 文件名称过滤
 */
public class FileNameFilterDemo {
    public static void main(String[] args) {
        String filePath = "I:\\Java\\JavaSE思维导图";
        //创建File对象
        File f = new File(filePath);
        //定义文件的扩展名
        String vidoesExts = "png";
        List<String> exts = Arrays.asList(vidoesExts.split(","));
        System.out.println(exts);
        File[] fs = f.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                //获取文件最后一个.的索引位置
                int index = name.lastIndexOf('.');
                //获取当前文件夹的扩展名
                String ext = name.substring(index+1);
                //System.out.println(ext);
                 //如果当前文件的扩展名在exts集合中，就会被列出来
                return exts.contains(ext);
            }
        });
        for (File files : fs) {
            System.out.println(files);
        }
    }
}
