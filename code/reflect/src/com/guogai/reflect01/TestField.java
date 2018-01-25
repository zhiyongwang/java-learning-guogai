package com.guogai.reflect01;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by guo on 2018/1/25.
 */
public class TestField {
    //获取对应的运行时类的属性
    @Test
    public void test1() {
        Class clazz = Person.class;
        //getFields()只能获取运行时类中声明为public的属性
        Field[] fields = clazz.getFields();
        for (int i = 0; i < fields.length;i++) {
            System.out.println(fields[i]);
        }
        //获取运行时类的所有属性
        Field[] fields1 = clazz.getDeclaredFields();
        for(Field f : fields1) {
            System.out.println(f.getName());
        }

    }
    //权限修饰符，变量类型，变量名
    //获取属性各个部分的内容
    @Test
    public void test2() {
        Class<Person> clazz = Person.class;
        Field[] fields1 = clazz.getDeclaredFields();
        for(Field f : fields1) {
            //1、获取每个权限的修饰符
            int modifiers = f.getModifiers();
            String s = Modifier.toString(modifiers);
            System.out.print(s + " ");
            //2、获取权限的变量类型
            Class<?> type = f.getType();
            System.out.print(type.getName() + " ");
            //3、获取权限的变量名
            System.out.print(f.getName());
            System.out.println("");
        }

    }
    //调用运行时类中指定的属性
    @Test
    public void test3() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Class<Person> clazz = Person.class;
        //1、创建指定的属性
        Field name = clazz.getField("name");
        //创建运行时类的对象
        Person person = (Person) clazz.newInstance();
        System.out.println(person);
        name.set(person,"guogai");
        System.out.println(person);

        Field age = clazz.getDeclaredField("age");
       age.setAccessible(true);   //java.lang.IllegalAccessException
        age.set(person,23);
        System.out.println(person);
    }
}
