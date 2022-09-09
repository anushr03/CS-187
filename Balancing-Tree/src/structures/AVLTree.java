	package structures;

import org.w3c.dom.Node;

public class AVLTree <T extends Comparable<T>> implements BSTInterface<T> {
		protected BSTNode<T> root;
		private int size;

		public AVLTree() {
			root = null;
			size = 0;
		}

		public boolean isEmpty() {
			// DO NOT MODIFY
			return root == null;
		}

		public int size() {
			// DO NOT MODIFY
			return size;
		}

		public BSTNode<T> getRoot() {
			// DO NOT MODIFY
			return root;
		}
		
		public void printTree() {
			System.out.println("------------------------");
			if (root != null) root.printSubtree(0);
		}

		public boolean remove(T element) {
			// Do not need to implement this method.
			return false;
		}

		public T get(T element) {
			// Do not need to implement this method.
			return null;
		}

		public int height() {
			return height(this.root);
		}

		public int height(BSTNode<T> node) {
			// return the node height
			return node != null ? node.getHeight() : -1;

		}
		
		public void updateHeight(BSTNode<T> node) {

			if (node == null) {
				return;
			}
			int leftHeight = height(node.getLeft());
			int rightHeight = height(node.getRight());
			node.setHeight( Math.max(leftHeight, rightHeight)+ 1);


		}
		
		public int balanceFactor(BSTNode<T> node) {

			if (node == null) {
				return -1;
			}
			int leftHeight = height(node.getLeft());
			int rightHeight = height(node.getRight());
			return rightHeight - leftHeight;

		}

		public BSTNode<T> rotateLeft(BSTNode<T> node) {

			BSTNode<T> y = node.getRight();
			if (y == null) {
				return null;
			}
			BSTNode<T> b = y.getLeft();
			y.setLeft(node);
			node.setRight(b);
			if (node.getParent() != null) {
				if (node.getParent().getLeft() == node) {
					node.getParent().setLeft(y);
				} 
				else {
					node.getParent().setRight(y);
				}
			} 
			else {
				this.root = y;
			}
			y.setParent(node.getParent());
			node.setParent(y);
			updateHeight(node);
			updateHeight(y);
			return y;
		}	
		
		public BSTNode<T> rotateRight(BSTNode<T> node) {

			if (node == null) {
				return null;
			}
			BSTNode<T> y = node.getLeft();
			if (y == null) {
				return null;
			}
			BSTNode<T> b = y.getRight();
			y.setRight(node);
			node.setLeft(b);
			if (node.getParent() != null) {
				if (node.getParent().getLeft() == node) {
					node.getParent().setLeft(y);
				} 
				else {
					node.getParent().setRight(y);
				}
			} 
			else {
				this.root = y;
			}
			y.setParent(node.getParent());
			node.setParent(y);
			updateHeight(node);
			updateHeight(y);
			return y;

		}

		public void add(T t) {

			BSTNode<T> newNode = new BSTNode<T>(t,null,null);
			if (this.root == null) {
				this.root = newNode;
				this.size++;
				return;
			}
			insertNode(this.root, newNode);
			updateHeight(newNode);
			rebalance(newNode);
			this.size++;


		}

		public void insertNode(BSTNode<T> node, BSTNode<T> newNode) {
			if (newNode.getData().compareTo(node.getData()) < 0) {
				if (node.getLeft() == null) {
					node.setLeft(newNode);
					newNode.setParent(node);
				} 
				else {
					insertNode(node.getLeft(), newNode);
				}
			} 
			else {
				if (node.getRight() == null) {
					node.setRight(newNode);
					newNode.setParent(node);
				} 
				else {
					insertNode(node.getRight(), newNode);
				}
			}
		}

		public void rebalance(BSTNode<T> node) {
			if (balanceFactor(node) > 1) {
				if (balanceFactor(node.getLeft()) < 0) {
					rotateRight(node.getLeft());
				}
				rotateLeft(node);
			} 
			else if (balanceFactor(node) < -1) {
				if (balanceFactor(node.getRight()) > 0) {
					rotateLeft(node.getRight());
				}
				rotateRight(node);
			}
			updateHeight(node);
			if (node.getParent() != null) {
				rebalance(node.getParent());
			}
		}
	}
