package structures;

import java.util.Iterator;

//This class implements a hashtable for a generic key and value type using an array backend.
public class ArrayHashTable<K, V> implements HashTable<K, V> {

  //Default constructor values- DO NOT CHANGE.
  public static final int DEFAULT_CAPACITY = 5;
  public static final double DEFAULT_LOAD_FACTOR = 0.7;

  //Provided instance variables- DO NOT CHANGE
  protected K[] keyArray;  //stores all keys 
  protected V[] valueArray;  // stores all values
  protected boolean[] isActiveArray;  // if true the cell is active, false the cell has data to be deleted.
  protected CollisionHandler <K> collisionHandler;  // collision handler can be either linear or quadratic.
  protected int count; // counts the number of key/value pairs on the hashtable
  protected int capacity;  // length of the arrays
  private double loadFactor;  // the ratio (0 < loadFactor < 1) that determines when to resize the hashtable.

  /**
   *   Initialize count to 0, capacity and loadFactor to the respective DEFAULT values,
   *   collisionHandler to the collisionHdler argument, create the three arrays
   *   for keys, values, and the isActive boolean array.
   */
  public ArrayHashTable(CollisionHandler <K> collisionHdler) {
      count = 0;
      capacity = DEFAULT_CAPACITY;
      loadFactor = DEFAULT_LOAD_FACTOR;
      collisionHandler = collisionHdler;
      keyArray = (K[]) new Object[capacity];
      valueArray = (V[]) new Object[capacity];
      isActiveArray = new boolean[capacity];

  }

  public ArrayHashTable(int capacity, CollisionHandler <K> collisionHdler){
      count = 0;
      this.capacity = capacity;
      loadFactor = DEFAULT_LOAD_FACTOR;
      collisionHandler = collisionHdler;
      keyArray = (K[]) new Object[capacity];
      valueArray = (V[]) new Object[capacity];
      isActiveArray = new boolean[capacity];


  }

  public ArrayHashTable(int capacity, double loadFactor, CollisionHandler <K> collisionHdler){
      count = 0;
      this.capacity = capacity;
      this.loadFactor = loadFactor;
      collisionHandler = collisionHdler;
      keyArray = (K[]) new Object[capacity];
      valueArray = (V[]) new Object[capacity];
      isActiveArray = new boolean[capacity];


  }

  public int getSize() {
      return count;
  }


  private void resizeArray() {

    int newCapacity = capacity * 2;
    ArrayHashTable<K, V> newHashTable = new ArrayHashTable<K, V>(newCapacity, loadFactor, collisionHandler);
    for (int i = 0; i < capacity; i++) {
      if (isActiveArray[i]) {
        newHashTable.put(keyArray[i], valueArray[i]);
      }
    }
    keyArray = newHashTable.keyArray;
    valueArray = newHashTable.valueArray;
    isActiveArray = newHashTable.isActiveArray;
    capacity = newCapacity;

  }

  private double calcLoadFactor() {

    return (double) count / capacity;
  } 

  public void put(K key, V value) {
      
    if(calcLoadFactor() >= loadFactor) {
      resizeArray();
    }
    int index = getHashOfKey(key);
    index = resolveCollision(index);
    keyArray[index] = key;
    valueArray[index] = value;
    isActiveArray[index] = true;
    count++;
  }

  public V getValue(K target) {

   int index = getHashOfKey(target);
   index = getIndex(index, target);
      if(index == -1){
          return null;
      }
      else{
          return valueArray[index];
      }
  }

  public V remove(K targetKey)throws ElementNotFoundException {

    int index = getHashOfKey(targetKey);
    index = getIndex(index, targetKey);
    
    if(isActiveArray[index]){
        V value = valueArray[index];
        isActiveArray[index] = false;
        count--;
        return value;
    }
    else{
        throw new ElementNotFoundException(null);
    }

  }

  private int getBoundedHash(int input) {
    return Math.abs(input) % this.capacity;
  }

  private int getHashOfKey(K key) {
    int index = getBoundedHash(key.hashCode());
    return index;
  }
  
  private int resolveCollision(int index) {
      index = collisionHandler.probe(index, isActiveArray, capacity);
    return index;
  }

  private int getIndex(int index, K key) {
      index = collisionHandler.search(index, key, keyArray, isActiveArray, capacity);
      return index;
  }

  /* end private methods */
  
  


  /**
   *  Returns a KeyIterator for the keys in this HashTable. 
   */
  public Iterator<K> keyIterator(){
       return new KeyIterator<K>(keyArray, count);
  }

    /******** methods used for testing purposes ***********/
    public int getCapacity(){
      return capacity;
    }

    public double getLoadFactor(){
      return loadFactor;
    }
  
    public K[] getKeyArray(){
       return keyArray;
    }  
  
    public V[] getValueArray(){
      return valueArray;
   }

   public boolean[] getIsActiveArray(){
     return isActiveArray;
   }

   public CollisionHandler <K>  getCollisionHandler(){
     return collisionHandler;
   }
  /************************************************************/

   /**  
    * Use this code for testing your ArrayHashTable. 
    * Comment out statements as needed. Use the debugger to see
    * what your code is doing by stepping in, out, and over methods
    * and statements, and checking the variables pane to see the state 
    * of your table and its variables.
    */
  public static void main(String[] args) throws Exception {
      // Init the type of collision handler: linear or quadratic.
    // CollisionHandler <String> collisionHdler = new LinearCollisionHandler<>();
      CollisionHandler <String> collisionHdler = new QuadraticCollisionHandler<>();
     ArrayHashTable<String, String> table = new ArrayHashTable<String, String> (collisionHdler);
     table.put("fatimeh", "ef4#B%k");
     // test the get method after the put method was called:
     System.out.println("test get after put: "+table.getValue("fatimeh"));
     table.put("matt", "A9d%&b");
     table.put("fadhil", "2h*k9s");
     table.put("rumeng", "j8*shX2");
    // this next call should cause a resize- uncomment when the table works on the above data:
     table.put("harper", "m8Ut6%#a");
    // test the keyIterator
     Iterator<String> keyIter = table.keyIterator();
     while(keyIter.hasNext()){
       String curKey = keyIter.next();
       System.out.print(curKey+ " ");
       System.out.println(table.getValue(curKey)+", ");
     }
    // test removing 
    table.remove("matt");
    System.out.println("test remove: "+table.getValue("matt"));
  }
}
