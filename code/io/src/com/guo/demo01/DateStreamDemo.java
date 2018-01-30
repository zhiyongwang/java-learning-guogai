package com.guo.demo01;

import java.io.*;

/**
 * Created by guo on 2018/1/30.
 */
public class DateStreamDemo {
    public static void main(String[] args) throws Exception {
        File f = new File("E:\\java\\test.txt");
        //write(f);
        read(f);
    }

    public static void read(File f) throws Exception {
        DataInputStream in = new DataInputStream(new FileInputStream(f));
        System.out.println(in.readByte());
        System.out.println(in.readChar());    //只能用相对应的方式来读取
        System.out.println(in.readUTF());
        in.close();
    }

    private static void write(File f) throws Exception {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(f));
        out.writeByte(66);// byte
        out.writeChar('郭');// char
        out.writeUTF("越努力，越幸运!");// string
        out.close();
    }
}
