package com.guo.demo01;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by guo on 2018/2/2.
 * ArrayList和
 */
public class MyArrayList {
    public static void main(String[] args) throws Exception {
        MyArrayList list = new MyArrayList();
        list.add(20);
        list.add(21);
        list.add(22);
        list.add(43);
        list.add(7);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("------------修改--------------");
        list.set(1, 23);
        System.out.println("------------查找--------------");
        //取得刚修改的元素
        System.out.println(list.get(1));  //超出索引范围会出现IndexOutOfBoundsException


        System.out.println("------------删除--------------");
        list.removeAt(1);
        list.removeAt(1);
        list.removeAt(1);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }


    }

    Object[] objs = new Object[4];

    int size = 0;   //集合的大小

    public int size() {
        return size;
    }

    /**
     * 添加
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     */
    public void add(Object value) {
        if (size >= objs.length) {
            Object[] temp = new Object[objs.length * 2];     //底层扩容是 3/2 + 1
            //把小数组的东东搬到大数组里面，底层是System.arrayCopy()
            for (int i = 0; i < objs.length; i++) {
                temp[i] = objs[i];
            }
            objs = temp;  //每次操作的都是obj
        }
        objs[size] = value;
        size++;

    }

    /**
     * 替换指定索引上的元素
     * Replaces the element at the specified position in this list with
     * the specified element.
     */
    public void set(int index, Object value) throws Exception {
        if (index >= size && index < 0) {
            throw new RuntimeException("IndexOutOfBoundsException");   //数组越界
        }
        if (index < size && index > -1) {
            objs[index] = value;
        }
    }

    /**
     * 获取  Returns the element at the specified position in this list
     */
    public Object get(int index) {
        if (index >= size || index < 0) {
            throw new RuntimeException("IndexOutOfBoundsException");   //数组越界
        }
        return objs[index];
    }

    /**
     * Removes all of the elements from this list.  The list will
     * be empty after this call returns.
     */
    public void clear() {
        objs = new Object[4];
        size = 0;
    }


    /**
     * 删除指定索引上的元素
     *  Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     */
    public void removeAt(int index) {
        if (index >= size || index < 0) {
            throw new RuntimeException("IndexOutOfBoundsException");   //数组越界
        }
        for(int i= index + 1; i < size; i++) {
            objs[i - 1] = objs[i];
        }
        size--;
    }

}
