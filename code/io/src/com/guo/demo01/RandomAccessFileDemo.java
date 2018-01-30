package com.guo.demo01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by guo on 2018/1/30.
 * 随机访问文件，表示可以在文件的任何位置写出或读取数据
 */
public class RandomAccessFileDemo {
    public static void main(String[] args) throws Exception {
        File f = new File("E:\\java\\test.txt");
        write(f);
        read(f);
    }

    public static void read(File f) throws Exception {
        RandomAccessFile raf = new RandomAccessFile(f, "rw");
        System.out.println(raf.readByte());
        System.out.println("文件指针位置 " + raf.getFilePointer());
        System.out.println(raf.readChar());
        System.out.println("文件指针位置 " + raf.getFilePointer());
        raf.seek(0);//设置文件指针为0
        //raf.skipBytes(4);//设置文件指针跳过2个字节
        System.out.println(raf.readInt());
        System.out.println("文件指针位置 " + raf.getFilePointer());
        System.out.println(raf.readUTF());
        System.out.println("文件指针位置 " + raf.getFilePointer());
        raf.close();
    }

    public static void write(File f) throws Exception {
        RandomAccessFile raf = new RandomAccessFile(f, "rw");
        raf.writeByte(55);    //一个字节
        raf.writeChar('郭');  //两个字节
        raf.writeInt(33);      //四个字节
        raf.writeUTF("越努力，越幸运");   //使用时修改之后的UTF-8，多两个自己

    }
}
