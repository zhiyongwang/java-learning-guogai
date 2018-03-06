public class MyLinkedList {
	protected Node first;	// 链表的第一个节点
	protected Node last;	// 链表的最后一个节点
	private int size;	// 节点的数量

	// 链表中的每一个节点
	public class Node {
		public Node(Object ele) { 	
			this.ele = ele;
		}

		Node prev;				// 上一个节点对象
		Node next;				// 下一个节点对象
		public Object ele; // 当前节点中存储的数据
	}

	public void addFirst(Object ele) {
		Node node = new Node(ele);      //需要保存的节点对象
		//进来一个节点，如果为空的话，它可定时第一个，也是最后一个
		if (size == 0) {
			this.first = node;
			this.last = node;
		}else {
			node.next = this.first;				// 把之前第一个作为新增节点的下一个节点，(进来一个，当前的只能当老二了。)
			this.first.prev = node;				// 把新增节点作为之前第一个节点的上一个节点
			this.first = node;					// 把新增的节点作为第一个节点
		}
		size++;    //这里很重要，别忘记
	}	
	public void addLast(Object ele) {
		Node node = new Node(ele);
		if (size == 0) {
			this.first = node;
			this.last = node;
		}else {
			this.last.next = node;			// 新增节点作为之前最后一个节点的下一个节点(因为是加在后面，所以当前节点的下一个才是 新增节点)
			node.prev = this.last;			// 之前最后一个节点作为新增节点的上一个节点
			this.last = node;				// 把新增的节点作为最后一个节点
		}
	}
	//原谅我复制了
	public void remove(Object ele) {
		// 找到被删除的节点
		Node current = this.first;// 确定为第一个节点,从头到尾开始找
		for (int i = 0; i < size; i++) {
			if (!current.ele.equals(ele)) {// 当前为true !true 为false ,说明找到当前ele,输出
				if (current.next == null) { // 续上: 如果false取反为true, 判断是否最后一个,
					return;
				}
				current = current.next;
			}
		}
		//删除节点
		if(current==first){
			this.first = current.next; //当前的下一个作为第一个
			this.first.prev = null; //当前下一个对上一个的引用设置为null
			
		}else if(current==last){
			this.last = current.prev;
			this.last.next = null;			
		}else{
			//把删除当前节点的下一个节点作为删除节点的上一个节点的next
			current.prev.next =current.next;
			//把删除节点的上一个节点作为删除节点下一个节点的prev
			current.next.prev = current.prev;
			
		}
		size--;
		//System.out.println("current =" + current.ele);
	}

	public String toString() {
		if (size == 0) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder(size * 2 + 1);
		Node current = this.first;// 第一个节点
		sb.append("[");
		for (int i = 0; i < size; i++) {
			sb.append(current.ele);
			if (i != size - 1) {
				sb.append(",");
			} else {
				sb.append("]");
			}
			current = current.next; // 获取自己的下一个节点
		}
		return sb.toString();
	}
	
	
}