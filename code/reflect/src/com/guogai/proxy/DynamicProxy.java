package com.guogai.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by guo on 2018/1/25.
 * 动态代理
 */
interface Subject {
    void action();
}
//被代理类
class RealSubject implements Subject {

    @Override
    public void action() {
        System.out.println("我是被代理类，记得要执行，么么，爱你哦！");
    }
}
class MyInvocationHandler implements InvocationHandler {
    Object obj; //实现了接口的被代理类的对象的声明
    //给被代理类实例化，返回一个代理类对象
     public Object blind(Object obj) {
         this.obj =  obj;
         return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),this );

     }
    //当通过代理类对象爱你个发起被重写方法调用时，都会转化为对下面invoke()方法的调用。
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object returnVal = method.invoke(obj, args);
        return returnVal;
    }
}
public class DynamicProxy {
    public static void main(String[] args) {
        //1、被代理类对象的创建
        RealSubject real = new RealSubject();
        //2、创建一个实现了Invocationhandler接口的类的对象
        MyInvocationHandler handler = new MyInvocationHandler();
        //3、调用blind()方法，动态的返回一个同样实现了real所在类实现的接口Subject的代理类对象
        Object obj = handler.blind(real);
        Subject sub = (Subject) obj;    //此时sub就是代理类的对象
        sub.action();//转到对Invocationhandler接口的的实现类invoke()方法的调用。


        //另外一个栗子
        NikeClothFactory nike = new NikeClothFactory();
        ClothFactory proxyCloth = (ClothFactory)handler.blind(nike);
        proxyCloth.productCloth();


    }
}
