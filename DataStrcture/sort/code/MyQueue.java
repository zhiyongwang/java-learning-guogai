import java.util.Stack;

 public class MyQueue{
	Stack<Integer> stack = new Stack<Integer>();
	Stack<Integer> stackTmp = new Stack<Integer>();

	//Push element X to the back of queue

	public void push(int x) {
		stack.push(x);
	}
	
	//Removes the element form in front of queue
	public void pop() {
		if (stackTmp.isEmpty()) {
			while (!stack.isEmpty()) {
				int tmp = stack.peek();
				stackTmp.push(tmp);
				stack.pop();
			}
		}
		else {
			stackTmp.pop();
		}
	}

	//Get the front element

	public int peek() {
		if (!stackTmp.isEmpty()) {
			int tmp = stack.peek();
			stackTmp.push(tmp);
		}
		return stackTmp.peek();
	}
	
	//Return whether the queueis empty

	public boolean empty() {
		if (stackTmp.isEmpty() && stack.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}

	public static void main(String[] args) {
		MyQueue queue = new MyQueue();
		queue.push(8);
		System.out.println(queue.empty());     //false
	}
}