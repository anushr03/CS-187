package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements
		BSTInterface<T> {
	protected BSTNode<T> root;

	public boolean isEmpty() {
		return root == null;
	}



	public int getSize() {

		return getSize(root);

	}

	/* helper method for getSize() */
	private int getSize(BSTNode<T> node) {
		if (node == null) {
			return 0;
		}
		return getSize(node.getLeft()) + getSize(node.getRight())+1;
	}

	public boolean contains(T t) {

		if (t == null) {
			throw new NullPointerException();
		}
		return contains(root, t);

	}

	/* helper method for contains() */
	private boolean contains(BSTNode<T> node, T t) {
		if (node == null) {
			return false;
		}
		if (node.getData().equals(t)) {
			return true;
		}
		if (t.compareTo(node.getData()) < 0) {
			return contains(node.getLeft(), t);
		} 
		else {
			return contains(node.getRight(), t);
		}
	}

	public boolean removeElement(T t) {

		if (t == null) {
			throw new NullPointerException();
		}
		boolean found = contains(t);
		if (found) {
			root = removeElement(root, t);
		}
		return found;
	}

	/* helper method for removeElement() */
	private BSTNode<T> removeElement(BSTNode<T> node, T t) {
		if (node == null) {
			return null;
		}
		if (t.compareTo(node.getData()) < 0) {
			node.setLeft(removeElement(node.getLeft(), t));
		} 
		else if (t.compareTo(node.getData()) > 0) {
			node.setRight(removeElement(node.getRight(), t));
		} 
		else {
			if (node.getLeft() == null) {
				return node.getRight();
			} 
			else if (node.getRight() == null) {
				return node.getLeft();
			} 
			else {
				T min = getMin(node.getRight());
				node.setData(min);
				node.setRight(removeElement(node.getRight(), min));
			}
		}
		return node;
	}

	public T getHighestValueFromSubtree(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getData();
		} 
		else {
			return getHighestValueFromSubtree(node.getRight());
		}
	}

	public T getLowestValueFromSubtree(BSTNode<T> node) {

		if (node.getLeft() == null) {
			return node.getData();
		} 
		else {
			return getLowestValueFromSubtree(node.getLeft());
		}

	}





	private BSTNode<T> removeRightmostFromSubtree(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getLeft();
		} 
		else {
			node.setRight(removeRightmostFromSubtree(node.getRight()));
			if (node.getRight() != null){
				node.getRight().setParent(node);
			}
			return node;
		}
	}

	public BSTNode<T> removeLeftmostFromSubtree(BSTNode<T> node) {

		if (node.getLeft() == null) {
			return node.getRight();
		} 
		else {
			node.setLeft(removeLeftmostFromSubtree(node.getLeft()));
			if (node.getLeft() != null){
				node.getLeft().setParent(node);
			}
			return node;
		}
	}

	public T getElement(T t) {

		if (t == null) {
			throw new NullPointerException();
		}
		return getElement(root, t);

	}

	/* helper method for getElement() */
	private T getElement(BSTNode<T> node, T t) {
		if (node == null) {
			return null;
		}
		if (node.getData().equals(t)) {
			return node.getData();
		}
		if (t.compareTo(node.getData()) < 0) {
			return getElement(node.getLeft(), t);
		} 
		else {
			return getElement(node.getRight(), t);
		}
	}


	public void addElement(T t) {

		if (t == null) {
			throw new NullPointerException();
		  }
		  root = addToSubtree(root, new BSTNode<T>(t, null, null));

	}

	/* helper method for addElement() */
	protected BSTNode<T> addToSubtree (BSTNode<T> node, BSTNode<T> newNode) {
		if (node == null) {
			return newNode;
		}
		if (newNode.getData().compareTo(node.getData()) < 0) {
			node.setLeft(addToSubtree(node.getLeft(), newNode));
			node.getLeft().setParent(node);
		}
		else {
			node.setRight(addToSubtree(node.getRight(), newNode));
			node.getRight().setParent(node);
		}
		return node;

	}

	@Override
	public T getMin() {

		if (root == null) {
			return null;
		}
		return getMin(root);
	}

	/* helper method for getMin() */
	private T getMin(BSTNode<T> node) {
		if (node.getLeft() == null) {
			return node.getData();
		} 
		else {
			return getMin(node.getLeft());
		}
	}


	@Override
	public T getMax() {

		if (root == null) {
			return null;
		}
		return getMax(root);
	}

	/* helper method for getMax() */
	private T getMax(BSTNode<T> node) {
		if (node.getRight() == null) {
			return node.getData();
		} 
		else {
			return getMax(node.getRight());
		}
	}


	@Override
	public int height() {

		if (root == null) {
			return -1;
		}
		return height(root);
	}

	/* helper method for height() */
	private int height(BSTNode<T> node) {
		if (node == null) {
			return -1;
		}
		int leftHeight = height(node.getLeft());
		int rightHeight = height(node.getRight());
		if (leftHeight > rightHeight) {
			return leftHeight + 1;
		} 
		else{
			return rightHeight + 1;
		}
	}

	public Iterator<T> preorderIterator() {

		Queue<T> queue = new LinkedList<T>();
		preorder(root, queue);
		return queue.iterator();

	}

	/* helper method for preorderIterator() */
	private void preorder(BSTNode<T> node, Queue<T> queue) {
		if (node == null) {
			return;
		}
		queue.add(node.getData());
		preorder(node.getLeft(), queue);
		preorder(node.getRight(), queue);
	}


	public Iterator<T> inorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		inorderTraverse(queue, root);
		return queue.iterator();
	}

	private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			inorderTraverse(queue, node.getLeft());
			queue.add(node.getData());
			inorderTraverse(queue, node.getRight());
		}
	}

	public Iterator<T> postorderIterator() {

		Queue<T> queue = new LinkedList<T>();
		postorder(root, queue);
		return queue.iterator();

	}

	/* helper method for postorderIterator() */
	private void postorder(BSTNode<T> node, Queue<T> queue) {
		if (node == null) {
			return;
		}
		postorder(node.getLeft(), queue);
		postorder(node.getRight(), queue);
		queue.add(node.getData());
	}

	@Override
	public boolean equals(BSTInterface<T> other) {

		if (other == null) {
			throw new NullPointerException();
		}
		if (this.getSize() != other.getSize()) {
			return false;
		}
		return equals(root, other.getRoot());
	}

	/* helper method for equals() */
	private boolean equals(BSTNode<T> node1, BSTNode<T> node2) {
		if (node1 == null && node2 == null) {
			return true;
		}
		if (node1 == null || node2 == null) {
			return false;
		}
		return node1.getData().equals(node2.getData()) && equals(node1.getLeft(), node2.getLeft())
				&& equals(node1.getRight(), node2.getRight());
	}

	@Override
	public boolean sameValues(BSTInterface<T> other) {

		if (other == null) {
			throw new NullPointerException();
		}
		Queue<T> queue1 = new LinkedList<T>();
		Queue<T> queue2 = new LinkedList<T>();
		this.inorderTraverse(queue1,this.getRoot());
		this.inorderTraverse(queue2,other.getRoot());
		if (queue1.size() != queue2.size()) {
			return false;
		}
		while (!queue1.isEmpty()) {
			if (!queue1.remove().equals(queue2.remove())) {
				return false;
			}
		}
		return true;

	}
	
	@Override
	public int countRange(T min, T max) {

		int count = 0;
		if (min.compareTo(max) > 0) {
			return 0;
		}
		Queue<T> queue = new LinkedList<T>();
		inorderTraverse(queue, root);
		while (!queue.isEmpty()) {
			T temp = queue.remove();
			if (temp.compareTo(min) > 0 && temp.compareTo(max) < 0) {
				count++;
			}
		}
		return count;
  	}



	@Override
	public BSTNode<T> getRoot() {
        // DO NOT MODIFY
		return root;
	}

	public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
		// header
		int count = 0;
		String dot = "digraph G { \n";
		dot += "graph [ordering=\"out\"]; \n";
		// iterative traversal
		Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
		queue.add(root);
		BSTNode<T> cursor;
		while (!queue.isEmpty()) {
			cursor = queue.remove();
			if (cursor.getLeft() != null) {
				// add edge from cursor to left child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getLeft().getData().toString() + ";\n";
				queue.add(cursor.getLeft());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}
			if (cursor.getRight() != null) {
				// add edge from cursor to right child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getRight().getData().toString() + ";\n";
				queue.add(cursor.getRight());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}

		}
		dot += "};";
		return dot;
	}

	public static void main(String[] args) {
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			BSTInterface<String> tree = new BinarySearchTree<String>();
			for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
				tree.addElement(s);
			}
			Iterator<String> iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.preorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.postorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();

			System.out.println(tree.removeElement(r));

			iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
		}

		BSTInterface<String> tree = new BinarySearchTree<String>();
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			tree.addElement(r);
		}
		System.out.println(tree.getSize());
		System.out.println(tree.height());
		System.out.println(tree.countRange("a", "g"));
		System.out.println(tree.countRange("c", "f"));
	}
}