package com.guogai.reflet01;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by guogai on 2018/1/25.
 */
public class TestReflection {

    /**
     * 类的加载器作用：用来把类(class)加载到内存中
     * 引导类加载器(Bootstap Classloader)：用C++编写，是JVM自带的类加载器，用来加载核心类库，如rt.jar
     * 扩展类加载器(Extension Classloader):负责加载jre/lib/ext目录下的jar包。
     * 系统类加载器(System Classloader）：负责加载CLASSPATH目录下的jar，是最常用的加载器。
     */
    @Test
    public void test5() throws ClassNotFoundException, IOException {
        ClassLoader classLoader1 = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader1);  //AppClassLoader@3e58f124
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);   //ExtClassLoader@413f9276
        ClassLoader classLoader3 = classLoader2.getParent();
        System.out.println(classLoader3);   //null
        Class<Person> classLoader4 = Person.class;
        ClassLoader classLoader5 = classLoader4.getClassLoader();
        System.out.println(classLoader5);   //AppClassLoader@413f9276

        String name = "java.lang.Object";
        Class<?> clazz = Class.forName(name);
        ClassLoader classloader6 = clazz.getClassLoader();
        System.out.println(classloader6); //null

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        /**
         * 读取配置文件
         */

        //方法一：从 指定的路径下获取文件
        ClassLoader loader = this.getClass().getClassLoader();
        InputStream in = loader.getResourceAsStream("com\\guogai\\reflet01\\jdbc.properties");//获取一个输入流
        //方法二：从当前工程下获取文件
       // FileInputStream in = new FileInputStream(new File("jdbc1.properties"));
        Properties pros = new Properties();
        pros.load(in);
        String user = pros.getProperty("user");
        System.out.println(user);
        String passwd = pros.getProperty("password");
        System.out.println(passwd);

    }

    //获取运行时类的实力（3）
    @Test
    public void test() throws Exception {

        //1、调用运行时类本身的.Class属性
        Class<Person> clazz1 = Person.class;
        System.out.println(clazz1.getName());

        Class<String> stringClass = String.class;
        System.out.println(stringClass.getName());

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");


        //2、通过运行时类的对象获取
        Person person = new Person();
        Class<? extends Person> clazz2 = person.getClass();
        System.out.println(clazz2.getName());
        System.out.println(clazz2.getSuperclass());

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        //3、通过Class的静态方法获取,可能会出现的异常：java.lang.ClassNotFoundException
        String clazzName = "com.guogai.reflet01.Person";
        Class<?> clazz3 = Class.forName(clazzName);
        System.out.println(clazz3.getName());
        System.out.println(clazz3.getSuperclass());

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");


        //4、通过类的加载器来实现(类的加载，类的连接，类的初始化 )
        ClassLoader classLoader = this.getClass().getClassLoader();
        Class clazz4 = classLoader.loadClass(clazzName);
        System.out.println(clazz4.getClassLoader());


    }
    /**
     * java.lang.Class:是反射的源头
     * 创建一个类，通过编译(javac.exe),生成对应的字节码文件*.class。
     * 之后使用jaca.exe进行加载（JVM的类加载器完成的），此*.class文件加载
     * 到内存以后，就是一个运行时类。存放在缓存区。这个运行时类本身就是一个Class实例。
     * 1、每一个运行时类之加载一次，
     * 2、有了Class类实例以后，就可以进行如下的操作
     *      1)创建对应的运行时类对象
     *      2)获取对应的运行时类的完整结构（属性，方法，构造器，代码块，父类，异常类，内部类等等。）
     *      3）调用对应的运行时类的指定结构（属性，方法，构造器）
     *      4）反射的应用：动态代理
     */
    @Test
    public void test3() {
        Person p = new Person();
        Class<? extends Person> clazz = p.getClass();   //Returns the runtime class of this Object.
        System.out.println(clazz);

    }
    //有了反射之后，可以通过反射创建一个类对象，并调用其中的结构
    @Test
    public void test2() throws Exception {
        Class<Person> clazz = Person.class;
        //1创建clazz对应的运行时类Person类的对象
        Person person = clazz.newInstance();
        System.out.println(person);
        Field f1 = clazz.getField("name");
        f1.set(person,"guogai");
        System.out.println(person);

        //java.lang.NoSuchFieldException: age
//        Field age = clazz.getField("age");
//        f1.set(person,77);
//        System.out.println(person);
        //java.lang.IllegalAccessException
        //2通过反射调用运行时类的指定属性。private属性需要GetDeclaredField()方法，并设置setAccessible()为true
        Field f2 = clazz.getDeclaredField("age");
        f2.setAccessible(true);  //出现非法异常需要添加此方法。setAccessible()是启用和禁用访问安全检查的开关
        f2.set(person,66);
        System.out.println(person);


        //通过反射调用运行类的指定方法
        Method m1 = clazz.getMethod("show");
        m1.invoke(person);

        Method m2 = clazz.getMethod("display", String.class);
        m2.invoke(person,"china");


    }
    //在没有反射之前，如何创建一个对象，并调用其中的方法
    @Test
    public void test1() {
        Person p = new Person();
        p.setName("guogai");
        p.setAge(66);
        System.out.println(p);
        p.display("china");
        p.show();
    }
}
