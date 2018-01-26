package com.guo.demo01;

/**
 * Created by guo on 2018/1/26.
 */
public class Person extends Object {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    /**
     * 将父类的equals()方法写过来，重写父类的方法，
     * 但是，不改变父类的源代码
     * 重写equals()方法，自己定义自己对象计较方式
     */
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        //对参数obj进行非空判断
        if (obj == null) {
            return false;
        }
        //判断传递进来的对象是否是Person类型
        if (obj instanceof Person) {
            //return (this == obj); //底层使用了==进行比较。
            //弊端：多态中不能调用子类特有的内容，必须类型向下转型。
            //对obj参数类型向下转型，obj转成Person。
            Person person = (Person)obj;
            return this.age == person.age;
        }
        return false;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 重写父类中的toString()方法。
     * 没有必要让调用者看到自己的内存地址
     * @return
     */
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
