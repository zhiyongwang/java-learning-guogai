package com.gai.demo02;

import java.util.Properties;

/**
 * Created by guo on 2018/1/27.
 */
public class SystemDemo {
    public static void main(String[] args) {
        //function();
        // function_1();
        //function_2();
        function_3();
        function_4();
    }
    /**
	   * System类方法,复制数组
	   * arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
	   * Object src, 要复制的源数组
	   * int srcPos, 数组源的起始索引
	   * Object dest,复制后的目标数组
	   * int destPos,目标数组起始索引
	   * int length, 复制几个
	   */
    public static void function_4(){
        int[] src = {11,22,33,44,55,66};
        int[] desc = {77,88,99,0};

        System.arraycopy(src, 1, desc, 1, 2);//将src数组的1位置开始(包含1位置)的两个元素,拷贝到desc的1,2位置上
        for(int i = 0 ;  i < desc.length ; i++){
            System.out.println(desc[i]);
        }
    }

    /**
     * 获取当前操作系统的属性
     */
    public static void function_3() {
        Properties properties = System.getProperties();
        System.out.println(properties);

    }

    /**
     * JVM内存中，收取对象的垃圾
     * public static void gc()
     */
    public static void function_2() {
        new Person();
        new Person();
        new Person();

        System.gc();
    }

    /**
     * 退出虚拟机
     * exit(int status)
     * Terminates the currently running Java Virtual Machine. The argument serves as a status code;
     * by convention, a nonzero status code indicates abnormal termination.
     */
    public static void function_1() {
        while (true) {
            System.out.println("jiayou");
            System.exit(0);  //结束当前的虚拟机
        }
    }

    /**
     * 获取系统当前的毫秒值
     * long 	currentTimeMillis()
     */
    public static void function() {
        long time = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                System.out.println("好好努力");
            }
        }
        long end = System.currentTimeMillis();

        time = end - start;
        System.out.println("总过花费时间：" + time);
    }
}
