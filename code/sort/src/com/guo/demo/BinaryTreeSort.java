package com.guo.demo;

import javax.xml.soap.Node;

/**
 * Created by guo on 2018/2/2.
 * 树是一种重要的非线性数据结构，直观的看，它是数据元素(节点)按分支关系组织起来的结构
 * 二叉树(BinaryTree)是每个节点最多有两个子树的有序树，通常子树被称为：“左子树”和“右子树”
 * 二叉树算法的排序规则：
 * 1.选择第一个元素作为根节点
 * 2.之后如果每个元素大于根节点放在右子树，小于根节点则放左子树
 * 3.最后按照中序遍历的方式输出，则可以得到排序的结果(左->根->右)
 */
public class BinaryTreeSort {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.add(6);
        bt.add(2);
        bt.add(54);
        bt.add(32);
        bt.add(7);
        bt.add(59);
        bt.add(42);
        bt.print();   //2->6->7->32->42->54->59->
    }
}

class BinaryTree {
    private Node root;

    //添加节点
    public void add(int date) {
        if (root == null) {
            root = new Node(date);
        } else {
            root.addNode(date);
        }
    }

    //输出节点
    public void print() {
        root.printNode();

    }

    private class Node {
        private int date;
        private Node left;
        private Node right;

        public Node() {
        }

        public Node(int date) {
            this.date = date;
        }

        //添加节点
        public void addNode(int date) {
            //判断传入的值是否大于根节点
            if (this.date > date) {
                //看左节点是否有叶子节点
                if (this.left == null) {
                    this.left = new Node(date);
                } else {
                    //递归调用
                    this.left.addNode(date);
                }
            } else {
                if (this.right == null) {
                    this.right = new Node(date);
                } else {
                    this.right.addNode(date);
                }
            }
        }

        //按照中序遍历
        public void printNode() {
            if(this.left != null) {
                this.left.printNode();
            }
            System.out.print(this.date + "->");
            if (this.right != null) {
                this.right.printNode();
            }
        }
    }

}
