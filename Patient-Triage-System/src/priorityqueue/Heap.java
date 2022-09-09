package priorityqueue;

import java.util.Comparator;

public class Heap<T> implements PriorityQueueADT<T> {

  private int numElements;
  private T[] heap;
  private boolean isMaxHeap;
  private Comparator<T> comparator;
  private final static int INIT_SIZE = 5;


  public Heap(Comparator<T> comparator, boolean isMaxHeap) {

      this.comparator = comparator;
      this.isMaxHeap = isMaxHeap;
      this.heap = (T[]) new Object[INIT_SIZE];
      this.numElements = 0;
  }

  public void bubbleUp(int index) {
    
    if (index < 0 || index >= numElements) {
      throw new IndexOutOfBoundsException();
    }

    int parentIndex = getParentOf(index);
    T NewElement = heap[numElements - 1];
    while (index > 0 && compareElements(heap[parentIndex], NewElement) < 0) {
      swapIndices(index, parentIndex);
      index = parentIndex;
      parentIndex = getParentOf(index);
    }
    heap[index] = NewElement; 

  }

  public void bubbleDown(int index) {
    
    if (index < 0 || index > numElements) {
      throw new IndexOutOfBoundsException();
    }
    T firstElement = heap[0];
    int i = 0;
    boolean flag = true;
    while (flag) {
      int leftChildIndex = getLeftChildOf(i);
      int rightChildIndex = getRightChildOf(i);
      int largestChildIndex = -1;
      if (leftChildIndex < numElements && rightChildIndex < numElements) {
        if (compareElements(heap[leftChildIndex], heap[rightChildIndex]) > 0) {
          largestChildIndex = leftChildIndex;
        } 
        else {
          largestChildIndex = rightChildIndex;
        }
      } 
      else if (leftChildIndex < numElements) {
        largestChildIndex = leftChildIndex;
      } 
      else if (rightChildIndex < numElements) {
        largestChildIndex = rightChildIndex;
      }
      if (largestChildIndex == -1) {
        flag = false;
      } 
      else if (compareElements(heap[largestChildIndex], firstElement) > 0) {
        swapIndices(i, largestChildIndex);
        i = largestChildIndex;
      } 
      else {
        flag = false;
      }
    }
    heap[i] = firstElement;


  }


  public boolean isEmpty() {
    boolean isEmpty = false;
    if (numElements == 0) {
      isEmpty = true;
    }
    return isEmpty;
  }

  public int getSize(){
    int size = -100;
    size = numElements;
    return size;
  }

  public int compareElements(T element1 , T element2) {
    int result = 0;
    int compareSign =  -1;
    if (isMaxHeap) {
      compareSign = 1;
    }
    result = compareSign * comparator.compare(element1, element2);
    return result;
  }


  public T peek() throws QueueUnderflowException {
    T data = null;
    data = heap[0];
    if (data == null) {
      throw new QueueUnderflowException();
    }
    return data;
  }  

  public T dequeueElement() throws QueueUnderflowException{

    T data = null;
    if (numElements == 0) {
      throw new QueueUnderflowException();
    }
    data = heap[0];
    heap[0] = heap[numElements - 1];
    heap[numElements - 1] = null;
    numElements--;
    bubbleDown(0);
    return data;
    
  }

  public void enqueueElement(T newElement) {
      expandCapacity();
      heap[numElements++] = newElement;
      bubbleUp(numElements - 1);
  }


  /*helper methods */
  private int getLeftChildOf(int parentIndex) {
    if (parentIndex < 0) {
      throw new IndexOutOfBoundsException();
    }
    return (parentIndex * 2) + 1;
  }
  private int getRightChildOf(int parentIndex) {
    if (parentIndex < 0) {
      throw new IndexOutOfBoundsException();
    }
    return (parentIndex * 2) + 2;
  }
  private int getParentOf(int childIndex) {
    if (childIndex < 0) {
      throw new IndexOutOfBoundsException();
    }
    return (childIndex - 1) / 2;
  }
  private void swapIndices(int index1, int index2) {
    if (index1 < 0 || index1 >= numElements || index2 < 0 || index2 >= numElements) {
      throw new IndexOutOfBoundsException();
    }
    T temp = heap[index1];
    heap[index1] = heap[index2];
    heap[index2] = temp;
  }

  private void expandCapacity() {
    if (numElements == heap.length) {
      T[] temp = (T[]) new Object[heap.length * 2];
      for (int m = 0; m < numElements; m++)
        temp[m] = heap[m];
        heap = temp;
      }
  }

}