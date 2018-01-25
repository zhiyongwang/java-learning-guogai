package com.guogai.reflect01;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by guo on 2018/1/25.
 */
public class TestMethod {
    //获取运行时类的方法
    @Test
    public void test1() {
        Person person = new Person();
        Class<? extends Person> clazz = person.getClass();
        //1、getMethods():获取运行时类及父类中所有声明为public的方法
        Method[] methods = clazz.getMethods();
        for(Method method : methods) {
            System.out.println(method.getName());
        }
        //2、获取运行时类本身声明的所有方法
        System.out.println("-----------------------------------------------");
        Method[] methods1 = clazz.getDeclaredMethods();
        for(Method method1 : methods1) {
            System.out.println(method1.getName());
        }
    }
    //注解，权限修饰符，返回值类型，方法名，形参列表，异常
    @Test
    public void test2() throws ClassNotFoundException {
        String name = "com.guogai.reflect01.Person";
        Class<?> clazz = Class.forName(name);
        Method[] methods1 = clazz.getDeclaredMethods();
        for(Method method1 : methods1) {
            //1、注解
            Annotation[] annotations = method1.getAnnotations();
            for (Annotation a : annotations) {
                System.out.println(a);
            }
            //2、权限修饰符
            String modifiers = Modifier.toString(method1.getModifiers());
            System.out.print(modifiers + "");
            //3、返回值类型
            Class<?> returnType = method1.getReturnType();
            System.out.print(returnType.getName() + " ");
            //4、方法名、
            System.out.print(method1.getName() + " ");
            //5、参数列表
            Class<?>[] parameterTypes = method1.getParameterTypes();
            for (Class p :parameterTypes){
                System.out.print(p.getName() + " ");
            }
            //6、异常
            Class<?>[] exceptionTypes = method1.getExceptionTypes();
            for (Class e : exceptionTypes) {
                System.out.print(e.getName() + " ");
            }

        }
    }
    //调用运行类中指定的方法
    @Test
    public  void test3() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        String name = "com.guogai.reflect01.Person";
        Class<?> clazz = Class.forName(name);
        Method method = clazz.getMethod("show");
        Object o = clazz.newInstance();
        Person person = (Person) o;
        method.invoke(person);

        Method toString = clazz.getMethod("toString");
        //调用指定的方法：invoke()
        Object returnValue = toString.invoke(person);
        System.out.println(returnValue);

    }
}
