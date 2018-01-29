package com.da.study;

/**
 * Created by guo on 2018/1/29.
 * 基于类的初始化解决方案
 */
public class InstanceFactory {
    private static class InstanceHolder{
        public static Instance instance = new Instance();
    }
    public static Instacen getInstance() {
        return InstanceHolder.instance;
    }
}
class Instance extends Instacen {

}
