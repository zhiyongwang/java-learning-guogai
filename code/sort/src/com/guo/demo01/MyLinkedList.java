package com.guo.demo01;

import jdk.nashorn.internal.runtime.linker.LinkerCallSite;

import java.awt.*;
import java.security.PublicKey;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

/**
 * Created by guo on 2018/2/3.
 * 单向链表、双向链表、单向循环列表、双向循环列表
 *
 * ---------------------------------------------------------------|
 *       对比      |   LinkedList     |    ArrayList                |
 * ----------------|------------------|----------------------------|
 *      添加       |   添加性能高     |  数据量大时，性能差。要复制  |
 * ----------------|------------------|-----------------------------|
 *       删除      |删除中间数据效率低 | 删除前面数据效率低，反之     |
 * ----------------|------------------|-----------------------------|
 *     获取和设置  |    效率不        |   有索引，性能比较好         |
 * ----------------|------------------|-----------------------------|
 * 注意：ArrayList循环删除，从后向前遍历来进行删除，删除前面的数据效率低，删除后面的数据效率高，
 */
public class MyLinkedList {
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();

        System.out.println("-----------添加--------------");
        list.add(43);
        list.add(14);
        list.add(21);
        list.add(29);
        list.add(22);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println("-----------设置--------------");

        list.set(1, 26);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println("-----------获取--------------");

        System.out.println(list.get(2));

        System.out.println("-----------清除--------------");

        list.clear();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println("-----------删除--------------");
        list.removeAt(3);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    int size = 0;

    Node root = null;
    //返回列表的大小
    public int size() {
        return size;
    }

    /**
     * 添加
     *
     * @param value
     */
    public void add(Object value) {
        Node newNode = new Node(value);
        if (root == null) {           //第一次添加
            root = newNode;
        } else {
            Node temp = root;
            while (temp.getNext() != null) {
                temp = temp.getNext();  //当前节点向后移动
            }
            //循环结束，表示temp是最后一个节点
            temp.setNext(newNode);
        }
        size++;
    }


    /**
     * 设置
     *
     * @param index
     * @param value
     */
    public void set(int index, Object value) {

        Node temp = root;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        //当前指针到达指定索引位置。
        temp.setValue(value);

    }

    /**
     * 获取
     *
     * @param index
     * @return
     */
    public Object get(int index) {
        Node temp = root;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        //temp定位到指定索引位置
        return temp.getValue();
    }

    /**
     * 清除
     */
    public void clear() {
        root = null;
        size = 0;

    }

    /**
     * 删除指定索引上的值
     */
    public void removeAt(int index) {
        if (index == 0) {           //删除头元素
            root = root.getNext();
        } else {
            //找到当前元素的前一个，
            Node temp = root;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
            }
            //循环结束，temp表示删除元素的前一个元素
            temp.setNext(temp.getNext().getNext());
        }
        size--;
    }
}
