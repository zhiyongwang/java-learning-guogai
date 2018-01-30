package com.guo.demo01;
import java.io.*;
/**
 * Created by guo on 2018/1/30.
 * 把一个字符转为一段Java代码执行
 * 需要把项目的字符编码设置为GBK
 */
public class EvalDemo {
    public static void main(String[] args) throws Exception {
        String str = "System.out.println(1+1);";
        eval(str);
    }

    private static void eval(String str) throws Exception {
        String className = "TempClass";
        String classFileName = "TempClass.java";
        //1.把传入的字符串，拼接成一个带有main()方法的Java类
        StringBuilder buffer = new StringBuilder(100);
        buffer.append(" public class ").append(className).append("{");
        buffer.append(" public static void main(String[] args) { ");
        buffer.append(str);
        buffer.append("}");
        buffer.append("}");
        //2.使用文件输出流，把该字符串保存到磁盘的一个Java文件中
        Writer out = new FileWriter(new File("d:\\",classFileName));
        out.write(buffer.toString());
        out.close();

        //3在调用编译器工具(javac),编译生成的java文件
        Process javacProcess = Runtime.getRuntime().exec("javac "+classFileName);
        //获取编译失败的错误信息
        InputStream in = javacProcess.getErrorStream();
        byte[] b = new byte[102];
        int len = -1;
        while ((len = in.read(b)) != -1) {
          System.out.println("编译失败");
            System.out.println(new String(b,0,len));
        }

        //4.在调用运行工具java，运行该类
        Process javaProcess = Runtime.getRuntime().exec("java " + classFileName);
        in = javaProcess.getInputStream();
        b = new byte[1024];
        len = -1;
        while ((len = in.read(b)) != -1) {
            System.out.println(new String(b,0,len));
        }
    }
}
