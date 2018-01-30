package com.guo.demo01;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by guo on 2018/1/30.
 */
public class OutputStreamDemo {
    public static void main(String[] args) throws IOException {
        //把Hello,word写入到一个 文件中
        String str = "Hello,World";
        //1.创建输出流对象
        OutputStream out = new FileOutputStream("d:\\test.txt");
        //2.把程序中的文件写出到程序中
        out.write(str.getBytes());
        //3.关闭流
        out.close();
    }
}
