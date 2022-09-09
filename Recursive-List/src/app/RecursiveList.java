package app;

import java.util.Iterator;

public class RecursiveList<T> implements ListInterface<T> {

  private int size;
  private Node<T> head = null;

  public RecursiveList() {
    this.head = null;
    this.size = 0;
  }

  public RecursiveList(Node<T> first) {
    this.head = first;
    this.size = 1;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void insertFirst(T elem) {
    if (elem == null) {
      throw new NullPointerException();
    }
    Node<T> node = new Node<T>(elem, null);
    if (head == null) {
      head = node;
    } 
    else {
      node.setNext(head);
      head = node;
    }
    size++;

  }
  
  // helper method 
  private Node<T> lastNode(Node<T> curr) {
    if (curr.getNext() == null) {
      return curr;
    } 
    else {
      return lastNode(curr.getNext());
    }
  }

  @Override
  public void insertLast(T elem) {
    if (elem == null) {
      throw new NullPointerException();
    }
    Node<T> node = new Node<T>(elem, null);
    if (head == null) {
      head = node;
    } 
    else {
      Node<T> last = lastNode(head);
      last.setNext(node);
    }
    size++;
  }

  // helper method 
  private Node<T> getNode(int index, Node<T> curr) {
    if (index == 0) {
      return curr;
    } 
    else {
      return getNode(index - 1, curr.getNext());
    }

  }
  @Override
  public void insertAt(int index, T elem) {
    if (elem == null) {
      throw new NullPointerException();
    }
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    }
    if (index == 0) {
      insertFirst(elem);
    } 
    else {
      Node<T> node = new Node<T>(elem, null);
      Node<T> prev = getNode(index - 1, head);
      node.setNext(prev.getNext());
      prev.setNext(node);
      size++;
    }


  }

  @Override
  public T removeFirst() {
    T removedItem = null;
    if (head == null) {
      throw new IllegalStateException();
    } 
    else {
      removedItem = head.getData();
      head = head.getNext();
      size--;
    }

    return removedItem;
  }

  @Override
  public T removeLast() {
    T removedItem = null;

    if (head == null) {
      throw new IllegalStateException();
    }
    else if (head.getNext() == null) {
      removedItem = head.getData();
      head = null;
      size--;
    }
    else {
      Node<T> prev = getNode(size - 2, head);
      removedItem = prev.getNext().getData();
      prev.setNext(null);
      size--;
    }
    return removedItem;
  }

  @Override
  public T removeAt(int i) {
    T removedItem = null;
    if (i < 0 || i >= size) {
      throw new IndexOutOfBoundsException();
    }
    if (i == 0) {
      removedItem = removeFirst();
    } 
    else {
      Node<T> prev = getNode(i - 1, head);
      removedItem = prev.getNext().getData();
      prev.setNext(prev.getNext().getNext());
      size--;
    }
    return removedItem;

  }


  @Override
  public T getFirst() {
    T item = null;
    if (head == null) {
      throw new IllegalStateException();
    } 
    else {
      item = head.getData();
    }

    return item;
  }

  @Override
  public T getLast() {
    T item = null;
    if (head == null) {
      throw new IllegalStateException();
    } 
    else {
      Node<T> last = lastNode(head);
      item = last.getData();
    }

    return item;
  }

  @Override
  public T get(int i) {
    T item = null;
    if (head == null) {
      throw new IllegalStateException();
    } 
    else if (i < 0 || i >= size) {
      throw new IndexOutOfBoundsException();
    }
    else {
      item = getNode(i, head).getData();
    }

    return item;
  }


  @Override
  public void remove(T elem) {
    if (elem == null) {
      throw new NullPointerException();
    }
    if (head == null) {
      throw new IllegalStateException();
    }
    else if (head.getData().equals(elem)) {
      head = head.getNext();
      size--;
    }
    else {
      Node<T> prev = getNode(size - 1, head);
      if (prev.getNext() != null && prev.getNext().getData().equals(elem)) {
        prev.setNext(prev.getNext().getNext());
        size--;
      }
      else {
        throw new ItemNotFoundException();
      }
    }


  }

  @Override
  public int indexOf(T elem) {
    int index = -1;
    if(elem == null){
      throw new NullPointerException();
    }
    if(head == null){
      throw new IllegalStateException();
    }
    else{
      index = recurrIndex(head, elem, 0);
    }
    return index;

  }

  private int recurrIndex(Node<T> curr, T elem, int index){
    if(curr == null)
      return -1;
    if(curr.getData().equals(elem)){
      return index;
    }
    else{
      index++;
      return recurrIndex(curr.getNext(), elem, index);
    }
  }

  @Override
  public boolean isEmpty() {
    boolean empty = false;
    if(head == null){
      empty = true;
    }

    return empty;
  }


  public Iterator<T> iterator() {
    Iterator<T> iter = null;
    iter = new LinkedNodeIterator<>(head);
   return iter;
  }
}
