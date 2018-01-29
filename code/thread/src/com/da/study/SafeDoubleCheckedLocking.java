package com.da.study;
/**
 * Created by guo on 2018/1/29.
 */
public class SafeDoubleCheckedLocking {
    private volatile static Instacen instance;
    public static Instacen getInstance() {
        if(instance == null) {
            synchronized (SafeDoubleCheckedLocking.class) {
                if (instance == null) {
                    instance = new Instacen();
                }
            }
        }
        return instance;
    }
}
class Instacen{

}
