package com.guo.demo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/**
 * Created by guo on 2018/1/30.
 * 集合对象Properties类，继承Hashtable，实现Map接口
 * 可以和IO对象结合使用，实现数据的持久存储
 */
public class PropertiesDemo {
    public static void main(String[] args) throws IOException {
        //function();
        //function_1();
        function_2();
    }

    /**
     * Properties集合特有的方法store
     * store(OutputStream out, String comments)
     * store(Writer writer, String comments)
     * 接收所有的字节或字符输出流，将集合中的键值对，写回文件中保存
     */
    public static void function_2() throws IOException {
        Properties pro = new Properties();
        pro.setProperty("className","java.util.Properties");
        pro.setProperty("methodName","store");
        FileWriter fw = new FileWriter("c:\\pro.properties");
        //键值对，存回文件，使用集合的方法store传递字节输出流
        pro.store(fw,"注释ABC");  //中文会被转成UNICODE
        pro.clone();
    }

    /**
     * Properties集合中特有的方法load
     * oad(InputStream inStream)
     * load(Reader reader)
     * 传递任意的字节或字符输入流
     * 流对象读取文件的键值对，保存到集合中
     */
    public static void function_1() throws IOException {
        Properties pro = new Properties();
        FileReader fileReader = new FileReader("c:\\pro.properties");
        //调用集合的load()，传递字符输入流
        pro.load(fileReader);
        fileReader.close();
        System.out.println(pro);
        
    }

    /**
     * 使用Properties集合，存储键值对
     * setProperties等同于Map接口中的put()
     * setProperties(String key,String value)
     * 通过键获取值 getProperties(String key) 等同于Map接口中的get()
     */
    public static void function() {
        Properties pro = new Properties();
        pro.setProperty("A", "1");
        pro.setProperty("B", "2");
        pro.setProperty("C", "3");
        System.out.println(pro);

        //通过键获取值
        String value = pro.getProperty("A");
        System.out.println(value);

        //方法stringPropertyNames()，将集合中的键存储到Set集合中类似Map中的KeySet
        Set<String> set = pro.stringPropertyNames();
        for (String key : set) {
            System.out.println(key + "..." + pro.getProperty(key));
        }
    }
}
