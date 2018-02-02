package com.guo.demo;

/**
 * Created by guo on 2018/2/2.
 * 链表
 * 一种常常见的数据结构，是一种线性表，但是并不会按线性的顺序存储数据，
 * 而是在每一个节点里面存的是下一个节点的指针(pointer)
 * 数组和链表：线性数据结构
 *  数组适合查找，因为有索引。固定长度，数组删除的时候需要移动后面的元素
 *  链表适合插入，删除。遍历。只需要前后指针移动。不宜过长，尤其是在链尾插入时，效率较低。
 */
public class LinkedListDemo {
    public static void main(String[] args) {
        NodeManager nm = new NodeManager();
        System.out.println("------------add----------------");
        nm.add(5);
        nm.add(4);
        nm.add(3);
        nm.add(2);
        nm.add(1);
        nm.print();

        System.out.println("------------del----------------");
        nm.del(3);
        nm.print();

        System.out.println("------------find----------------");
        boolean b = nm.find(2);
        System.out.println(b);

        System.out.println("------------update----------------");
        nm.update(5, 9);
        nm.print();

        System.out.println("------------insert----------------");
        nm.insert(3, 6);
        nm.print();

    }
}

class NodeManager {
    private Node root;      //根节点
    private int currentIndex = 0; //节点的序号，每次操作从0开始

    //添加节点
    public void add(int data) {
        if (root == null) {
            root = new Node(data);
        } else {
            root.addNode(data);
        }
    }

    public void del(int data) {
        if (root == null) return;
        //如果删除的和根节点一样
        if (root.getData() == data) {
            root = root.next;
        } else {
            root.delNode(data);
        }
    }

    //查找是否存在节点
    public boolean find(int data) {
        if (root == null) return false;
        if (root.getData() == data) {
            return true;
        } else {
            return root.findNode(data);
        }
    }

    //修改节点
    public boolean update(int oldData, int newData) {
        if (root == null) return false;
        if (root.getData() == oldData) {
            root.setData(newData);
            return true;
        } else {
            return root.updateNode(oldData, newData);
        }

    }

    //打印所有节点
    public void print() {
        if (root != null) {
            System.out.print(root.getData() + "-->>");
            //递归调用打印
            root.printNode();
            System.out.println();
        }

    }

    //向节点前面插入
    public void insert(int index, int data) {
        if (index < 0) return;
        currentIndex = 0;
        if (index == currentIndex) {
            Node newNode = new Node(data);
            newNode.next = root; //把根节点作为新增节点的下一个节点
            root = newNode;
        } else {
            root.insertNode(index, data);
        }
    }

    //内部类
    private class Node {
        private int data;
        private Node next; //把当前类当作属性

        //添加节点
        public void addNode(int data) {
            if (this.next == null) {
                this.next = new Node(data);
            } else {
                this.next.addNode(data);
            }

        }

        //删除节点
        public void delNode(int data) {
            if (this.next != null) {
                if (this.next.data == data) {
                    this.next = this.next.next;
                } else {
                    this.next.delNode(data);
                }
            }

        }

        //查找节点是否存在
        public boolean findNode(int data) {
            if (this.next != null) {
                if (this.next.data == data) {
                    return true;
                } else {
                    return this.next.findNode(data);
                }
            }
            return false;
        }

        //输出节点

        public void printNode() {
            if (this.next != null) {
                System.out.print(this.next.data + "-->>");
                this.next.printNode();
            }

        }

        //修改节点
        public boolean updateNode(int oldData, int newData) {
            if (this.next != null) {
                if (this.next.data == oldData) {
                    this.next.data = newData;
                    return true;
                } else {
                    return this.next.updateNode(oldData, newData);
                }
            }

            return false;
        }

        //插入节点
        public void insertNode(int index, int data) {
            currentIndex++;
            if (index == currentIndex) {
                Node newNode = new Node(data);
                newNode.next = this.next;
                this.next = newNode;
            } else {
                this.next.insertNode(index, data);
            }
        }

        public Node() {
        }

        public Node(int data) {
            this.data = data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

    }

}
