package structures;

import java.util.NoSuchElementException;

/**************************************************************************************
 * NOTE: before starting to code, check support/structures/UnboundedQueueInterface.java
 * for detailed explanation of each interface method, including the parameters, return
 * values, assumptions, and requirements
 ***************************************************************************************/
public class Queue<T> implements UnboundedQueueInterface<T> {
	private Node<T> front;
	private Node<T> rear;
	private int size;


	public Queue() {
		front = null;
		rear = null;
		size = 0;
    }
	
	public Queue(Queue<T> other) {
		
		Node <T> temp = other.front;
		while(temp != null) {
			enqueue(temp.data);
			temp = temp.next;
		}

	}
	
	@Override
	public boolean isEmpty() {
		return front == null;

	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public void enqueue(T element) {
		Node<T> newNode = new Node<T>(element);
		size++;
		if(isEmpty()) {
			front = newNode;
			rear = newNode;
		}
		else {
			rear.next = newNode;
			rear = newNode;
		}
		

	}

	@Override
	public T dequeue() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		T temp = front.data;
		front = front.next;
		size--;
		return temp;
	}

	@Override
	public T peek() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return front.data;
	}

	
	@Override
	public UnboundedQueueInterface<T> reversed() {
		Queue<T> temp = new Queue<T>();
		reverseHelper(front, rear, temp);
		return temp;
	}
	
	private void reverseHelper(Node<T> front, Node<T> rear , Queue<T> temp) {
		if(front == null) {
			return;
		}
		reverseHelper(front.next, rear , temp);
		temp.enqueue(front.data);

	}

}

class Node<T> {
	public T data;
	public Node<T> next;
	public Node(T data) { this.data=data;}
	public Node(T data, Node<T> next) {
		this.data = data; this.next=next;
	}
}

