package com.guogai.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by guo on 2018/1/25.
 * 静态代理模式
 */
//接口
interface ClothFactory{
    void productCloth();
}
//被代理类

class NikeClothFactory implements ClothFactory {

    @Override
    public void productCloth() {
        System.out.println("Nike厂家生产一批衣服");
    }
}
//代理类
class Proxyfacty implements ClothFactory {
    ClothFactory cf;
    //创建一个代理对象时，实际传入一个被代理对象
    public Proxyfacty(ClothFactory cf) {
        this.cf = cf;
    }
    @Override
    public void productCloth() {
        System.out.println("代理类开始执行，");
        cf.productCloth();

    }
}
public class TestClothProduct {
    public static void main(String[] args) {
        NikeClothFactory nike = new NikeClothFactory();
        //传入被代理对象的引用。
        Proxyfacty proxy1 = new Proxyfacty(nike);
        proxy1.productCloth();
    }
}
