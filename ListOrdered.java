package Project2;

import java.util.ArrayList;
import java.util.Comparator;

/** A list of generic type objects stored in an array and maintained in order.
"In order" means in the order defined in the compareTo method in class T.
*/
class ListOrdered<T extends Comparable<T>> {
  
  /**Is the comparator of which the list is ordered
   * 
   */
  private Comparator<T> sortOrder = new Comparator<T>(){
      @Override
      public int compare(T obj1, T obj2) {
          return obj1.compareTo(obj2);
      }
  };
    
  
  /** number of elements that can be stored in list at default */
  private static final int DEFAULT_CAPACITY = 10;
  /** number of elements that can be stored in list at default */
  private static int capacity = DEFAULT_CAPACITY;
  

  /** current number of elements in list */
  private int numberOfItems = 0;

  /** basic data structure to hold list elements */
  private T[] items = null;

  /** Constructor with user-defined capacity of list */
  public ListOrdered(int size) {
    /* A declaration of an array like below is generally not advised!!
    If you were not trying to learn about data structures and how they are implemented, you would use one of Java's Collections classes (e.g. ArrayList, LinkedList, etc.).
    */
    @SuppressWarnings("unchecked")
    T[] temp = (T[]) new Comparable[DEFAULT_CAPACITY];
    items = temp;
  }

  /** Constructor with default capacity of list */
  public ListOrdered() {
    this(DEFAULT_CAPACITY);
  }

  /* CAREFUL not to mix up ListOrdered.length() with items.length.
  The method length() is returning the number of elements in the list,
  whereas the member variable length of items is the capacity of the list. */
  public int length() { return numberOfItems; }

  /** True if no elements in the list. */
  public boolean isEmpty() { return numberOfItems == 0; }
  /** True if list is filled to capacity */
  public boolean isFull() { return numberOfItems == capacity; }

  /**
  @return string of all list elements, 1 per line.
  */
  public String toString() {
    String returnString = "";
    for (int i=0; i<numberOfItems; i++) {
      returnString += items[i].toString() + "\n";
    }
    return returnString;
  } // end toString()

  /** Getter for list element at a specific index. Essential for testing.
  @param index Location of element to return (without removal).
  @return list element at index, if index valid (null if not valid)
  */
  public T get(int index) throws InvalidIndexException {
    if (index < 0 || index >= numberOfItems) {
      throw new InvalidIndexException(index , capacity, "get(int Index)");
    }
    return items[index];
  } // end get(int)

  /** Remove element at provided index (if valid), shifting elements so no gaps.
  NOTE that this is private.
  @param index Location of element to remove.
  @return removed element (or null if index out of bounds).
  */
  private T remove(int index) throws InvalidIndexException{
    if (index < 0 || index >= numberOfItems) {
      throw new InvalidIndexException(index , capacity, "remove(int Index)");
    }
    T temp = items[index];
    for (int i=index; i<numberOfItems-1; i++) {
      items[i] = items[i+1];
    }
    --numberOfItems;
    items[numberOfItems]= null;
    trimToSize();
    return temp;
  } // end remove(int)

  /** Add element at provided index (if valid), shifting elements
  to the right from index to the end to make room. Note that this is private.
  @param object to be added at provided index (if valid)
  @param index Location of where to add element
  */
  private void add (T object, int index) throws InvalidIndexException, NullObjectException{
    if (isFull()) {
        trimToSize();
    }
    if (index < 0 || index > numberOfItems) {
      throw new InvalidIndexException(index , capacity, "add(T object, int Index)");
    }
    if (object == null) {
      throw new NullObjectException("add(T object, int Index)");
    }
    for (int i=numberOfItems; i>index; i--) {
      items[i] = items[i-1];
    }
    items[index] = object;
    ++numberOfItems;
  } // end add(T, int)

  /** Remove all elements in the list, replacing each with null.
  */
  public void clear() {
    for (int i=0; i<numberOfItems; i++) {
      items[i] = null;
    }
    numberOfItems = 0;
    trimToSize();
  } // end clear()

  /** >>>>> YOU COMPLETE contains <<<<<< */
  /** use equals or call find */
  public boolean contains(T object) throws NullObjectException{
    if (object == null) {
        throw new NullObjectException("contains(T object");
    }
    for (int i = 0; i < numberOfItems; i++) {
        if (items[i].equals(object)) { return true; }
    }
    return false;
  } // end contains(T)

  /** Adds to the function in order of ID and does not add duplicates
  Use compareTo or find.
  @param object student to be added
  */
  public void add(T object) throws InvalidIndexException, NullObjectException{
    if (isFull()) {
        trimToSize();
    }
    if (isEmpty()) { 
        add(object, numberOfItems);
        return;
    }
    if (object == null) {
        throw new NullObjectException("add(T object)");
    }
    int i = 0;
    for (i = 0; i < numberOfItems; i++){
        if (sortOrder.compare(items[i], object)==0) {
            return;
        }
        if (sortOrder.compare(items[i], object)>0) {
            break;
        }
    }
    add(object, i);
  } // end add(T)

  /** >>>>> YOU COMPLETE remove <<<<<< */
  /** Removes item found with objects student ID number
  @param object student to be removed
  */
  public void remove(T object) throws InvalidIndexException, NullObjectException{
    if (isEmpty()) { return; }
    if (object == null) {
      throw new NullObjectException("remove(T object)");
    }
    int i = 0;
    while (i < numberOfItems){
        if (sortOrder.compare(items[i], object) == 0) {
            remove(i);
            return;
        }
        i++;
    }
    trimToSize();
  } // end remove(T)

  /** Binary search uses high, low and mid to find an item at O(log(n)) time
  @param object to be found
  @return -1 if not found, otherwise it will return the index the object is found at
  */
  public int find(T object) throws NullObjectException{
      if (isEmpty()) {return -1; }
      if (object == null) {
          throw new NullObjectException("find(T object)");
      }
      int mid = numberOfItems / 2;
      int low = 0;
      int high = numberOfItems - 1;
      
      
      while (high >= low) {
          mid = (high + low) / 2;
          if(sortOrder.compare(items[mid],object) < 0) {
              low = mid + 1;
          }
          else if (sortOrder.compare(items[mid],object) > 0) {
              high = mid - 1;
          }
          else { return mid; }
      }
      return -1; //not Found
  } // end find(T)

  /** Linear search uses passed comparator to find an item at O(n) time
  @param object to be found
  @return -1 if not found, otherwise it will return the index the object is found at
  */
  public int find(T object, Comparator<T> comp) throws NullObjectException {
      if (object == null) {
          throw new NullObjectException("find(T object, Comparator<T> comp)");
      }  
    for (int i = 0; i < numberOfItems; i++) {
      if (comp.compare(items[i], object) == 0) {
          return i;
      }
  }
    return -1;
  } // end find(T,Comparator)


  /** Remove Range uses two objects and a comparator to remove all objects inclusive between
   * the two objects based on the value received when compared to the objects passed
  @param object to be found for a start or end indices.
  @param object to be found for a start or end indices.
  @param comp is the comparator that will evaluate how the list should be ordered
  */
  public void removeRange(T object1, T object2, Comparator<T> comp) throws InvalidIndexException, NullObjectException{
      if (isEmpty()) { return; }
        int i = 0;
      if (object1 == null || object2 == null) {
          throw new NullObjectException("removeRange(T object1, T object2, Comparator<T> comp)");
      }  
        int temp = 0;
        T tempO;
        if (comp.compare(object1, object2) == 0) {
            remove(object1);
        }
        if (comp.compare(object1, object2) > 0) {
            tempO = object1;
            object1 = object2;
            object2 = tempO;
        }

        while (i < numberOfItems) {
             if (comp.compare(object1, items[i]) <= 0) {
                 if (comp.compare(object2, items[i]) >= 0) {
                     remove(i);
                     temp = 1;
                 }
             }
             if(temp == 0) {
                i++;
             }
             temp = 0;
        }
        trimToSize();
      
  } // end removeRange()
    
   /**Orders the list in the passed order and sets the default comparator to the passed comparator
   @param comp is the comparator to be set as the default
   */
    public void reorder(Comparator<T> comp) throws NullObjectException {
        sortOrder = comp;
        sort(this);
  }
  /**Sorts the list according to the current comparator for this object
   * 
   * @param <T> Type of list to be ordered
   * @param listToSort object list to be ordered
   */
    public static <T extends Comparable<T>> void sort(ListOrdered<T> listToSort) throws NullObjectException {
        if (listToSort.isEmpty()){
           throw new NullObjectException("sort(ListOrdered<T> listToSort)");
        }
        T temp;
        int j;
        for (int i = 1; i < listToSort.length();i++){
            j = i;
            while (j > 0 && listToSort.sortOrder.compare(listToSort.items[j], listToSort.items[j-1]) < 0) {
              temp = listToSort.items[j];
              listToSort.items[j] = listToSort.items[j - 1];
              listToSort.items[j-1] = temp;
              --j;
            }
        }
    }
    /** Sorts the list based on the comparator passed
     * 
     * @param <T> type of object to be sorted
     * @param listToSort object listOrdered to be sorted
     * @param comp comparator to be used in sort
     */
    public static <T extends Comparable<T>> void sort(ListOrdered<T> listToSort, Comparator<T> comp) throws NullObjectException{
      if (listToSort.isEmpty()){
           throw new NullObjectException("sort(ListOrdered<T> listToSort, Comparator<T> comp");
      }
      T temp;
      int j;
      for (int i = 1; i < listToSort.length();i++){
          j = i;
          while (j > 0 && comp.compare(listToSort.items[j], listToSort.items[j-1]) < 0) {
              temp = listToSort.items[j];
              listToSort.items[j] = listToSort.items[j - 1];
              listToSort.items[j-1] = temp;
              --j;
          }
      }
  }
    /**Sort function to sort based on passed comparator and returns an arraylist
     * ordered
     * @param comp comparator passed
     * @return an Arraylist properly sorted
     */
    public ArrayList<T> sort(Comparator<T> comp) throws InvalidIndexException, NullObjectException{
        ListOrdered<T> temp = new ListOrdered<>();
        //copy items of this into a new listordered object
        for (int i = 0; i < this.numberOfItems; i++){
            temp.add(items[i]);
        }
        //sort the copy using the copy
        temp.reorder(comp);
        //copy the newly sorted copy into a new arraylist
        ArrayList<T> a = new ArrayList<>();
        for(int i = 0; i < temp.length(); i++) {
            a.add(temp.items[i]);
        }
        return a;
  }
    /** Grabs all items in the array that match the key passed with the comparator
     * 
     * @param key object to be matched
     * @param comp comparator used to check
     * @return all objects that match the key
     */
    public ArrayList<T> getAll(Object key, Comparator<T> comp) throws NullObjectException{
      if (numberOfItems == 0 || key == null) {
          throw new NullObjectException("getAll(Object key, Comparator<T> comp)");  
      }
      ArrayList<T> a = new ArrayList<>();
      for(int i = 0; i < numberOfItems; i++) {
          T temp = (T)key;
          if (comp.compare(temp, items[i]) == 0){
              a.add(items[i]);
          }
      }
      return a;    
  }
    /**Grabs first instance in the sorted list that matches the key
     * 
     * @param key object to be matched
     * @param comp comparator to be used
     * @return first instance of object that matches
     */
    public T getFirst(Object key, Comparator<T> comp) throws NullObjectException{
      if (numberOfItems == 0 || key == null) {
          throw new NullObjectException("getFirst(Object key, Comparator<T> comp)");  
      }
      for(int i = 0; i < numberOfItems; i++) {
          T temp = (T)key;
          if (comp.compare(temp, items[i]) == 0){
              return items[i];
          }
      }
      return null;    
  }
/** Creates an Arraylist of all objects between both passed keys using the passed comparator
 * 
 * @param key1 start value
 * @param key2 end value
 * @param comp comparator used to determine whats between
 * @return arraylist of all objects that are within range
 */
    public ArrayList<T> getRange(Object key1, Object key2, Comparator<T> comp) throws NullObjectException{
      if (numberOfItems == 0 || key1 == null || key2 == null) {
          throw new NullObjectException("getRange(Object key1, Object key2, Comparator<T> comp)");  
      }
      ArrayList<T> a = new ArrayList<>();
      T obj1 = (T)key1;
      T obj2 = (T)key2;
      
      for(int i = 0; i < numberOfItems; i++) {
          if (comp.compare(obj1, items[i]) <= 0 && comp.compare(obj2, items[i]) >= 0){
              a.add(items[i]);
          }
      } 
      return a;    
  }
/**removes all objects in list that aren't within the range
 * 
 * @param key1 start value
 * @param key2 end value
 * @param comp comparator to be used
 */
     public void retainRange(Object key1, Object key2, Comparator<T> comp) throws InvalidIndexException, NullObjectException{
   //if items[i] is < obj1 or > obj2 then remove it
        if (numberOfItems == 0 || key1 == null || key2 == null) {
          throw new NullObjectException("retainRange(Object key1, Object key2, Comparator<T> comp)");  
        }
        ArrayList<T> a = getRange(key1, key2, comp);
        this.clear();
        for(int i = 0; i < a.size(); i++){
            add(a.get(i));
        }
        trimToSize();
  }
/**Creates a T array with all the objects in this object
 * 
 * @return T array
 */
    public T[] toArray() throws NullObjectException {
        if (numberOfItems == 0) {
          throw new NullObjectException("toArray()");  
        }
        @SuppressWarnings("unchecked")
        T [] array;
        array =(T[]) new Object[numberOfItems];
        for (int i = 0; i < numberOfItems; i++) {
            array[i] = items[i];
        }
        return array;
    }
    /** Increments or decrements list by multiples of 10 if needed
     * is recursive
     */
    public void trimToSize(){
        if ((int)Math.ceil((double)(numberOfItems) / 10.0) < (capacity /10)) {
            return;
        }

        if ((int)Math.ceil((double)(numberOfItems) / 10.0) < (capacity /10)) {
            if (capacity == 10) {
                return;
            }
            capacity = capacity - 10;
            @SuppressWarnings("unchecked")
            T[] copiedArray = (T[]) new Comparable[capacity];

            for (int i = 0; i < numberOfItems; i++) {
                copiedArray[i] = items[i];
            }
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) new Comparable[capacity];
            items = newArray;
            for (int i = 0; i < copiedArray.length; i++) {
                items[i] = copiedArray[i];
            }
            this.trimToSize();
        }
        if ((int)Math.ceil((int)Math.ceil((double)(numberOfItems + 1)/ 10.0)) >= (capacity /10)) {
            capacity = capacity + 10;
            @SuppressWarnings("unchecked")
            T[] copiedArray = (T[]) new Comparable[capacity];

            for (int i = 0; i < numberOfItems; i++) {
                copiedArray[i] = items[i];
            }
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) new Comparable[capacity];
            items = newArray;
            for (int i = 0; i < copiedArray.length; i++) {
                items[i] = copiedArray[i];
            }
            this.trimToSize();
        }
    }
  
    /** calls max function with current objects comparator
     * 
     * @return objects max value based on comparator
     */
    public T max() throws NullObjectException{
      return max(sortOrder);    
  }
    /** calls min function with current objects comparator
     * 
     * @return objects min value based on comparator
     */
    public T min() throws NullObjectException{
      return min(sortOrder);    
  }
/** Uses passed comparator to return highest value based on comparator
 * 
 * @param comp comparator to be used
 * @return  objects max value based on comparator
 */
    public T max(Comparator<T> comp) throws NullObjectException{
      if (numberOfItems == 0) {
         throw new NullObjectException("max(Comparator<T> comp)");  
      }
      T maxObj = items[0];
      for (int i = 0; i < numberOfItems;i++){
          if (comp.compare(items[i], maxObj) > 0) {
              maxObj = items[i];
          }
      }
      return maxObj; 
  }
    /**Uses passed comparator to return lowest value based on comparator
     * 
     * @param comp comparator to be used
     * @return  objects min value based on comparator
     */
    public T min(Comparator<T> comp) throws NullObjectException{
      if (numberOfItems == 0) {
         throw new NullObjectException("min(Comparator<T> comp)");  
      }
      T minObj = items[numberOfItems - 1];
      for (int i = 0; i < numberOfItems;i++){
          if (comp.compare(minObj, items[i]) > 0) {
              minObj = items[i];
          }
      }
      return minObj; 
  }
}
