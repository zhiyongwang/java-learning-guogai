package com.guo.demo01;

/**
 * Created by guo on 2018/2/3.
 */
public class Node {
    Object value;  //要保持的元素
    Node next;     //下一个节点的地址(对象引用)

    public Node(Object value) {
        this.value = value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }
}
