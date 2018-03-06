import java.util.*;

public class SimpleQueue<T> implements Iterable<T> {
	private LinkedList<T> storage = new LinkedList<T>();
	public void add(T t){
		storage.offer(t);
	}
	public T get() {
		return storage.poll();
	}
	public Iterator<T> iterator() {
		return storage.iterator();
	}

	public static void main(String[] args) {
		SimpleQueue queue = new SimpleQueue();
		queue.add(8);
		System.out.println(queue.get());

	}
}