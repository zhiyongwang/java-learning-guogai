class Node<E> {
    Node<E> next = null;
    E data;
    public Node(E data) {
        this.data = data;
    }
}

//采用单链表实现栈
public class MyStack<E> {
    int depth;   //栈的深度

    public MyStack(int i) {
        this.depth = i;
    }

    Node<E> top = null;

    //将元素压入栈中
    public boolean push(E data) {
        if(size() < depth) {
        Node<E> newNode = new Node<E>(data);
        newNode.next = top;
        top = newNode;
        return true;
        }
        return false;
    }

    //读取栈中的头节点，不删除头节点

    public E peek() {
        if(top ==null) {
            return null;
        }
        return top.data;
    }

    //获取栈中的头节点，并删除头节点
    public E pop() {
        if(top ==null) {
            return null;
        }
        Node<E> tmp = top;
        top = top.next;
        return tmp.data;
    }
    //栈的元素个数

    public int size() {
        int len = 0;
        Node tmeNode = top;
        while(tmeNode != null) {
            tmeNode = tmeNode.next;
            len++;
        }
        return len;
    }

    //当前栈的深度
    public int depth() {
        return this.depth;
    }
    public static void main(String[] args) {
      MyStack stack = new MyStack(2);
      System.out.println(stack.push(1));
      System.out.println(stack.push(2));
      System.out.println(stack.push(3));
      System.out.println("栈的元素个数： " +stack.size());
      System.out.println(stack.pop());
      System.out.println(stack.pop());
      System.out.println(stack.pop());
      System.out.println("栈的元素个数： " + stack.depth());
    }
}