package app;
import java.util.ArrayList;

/**
 *  This class provides methods that perform operations on a list of generic objects.
 *  The Objects are assumed to have an appropriate implementation of the equals method.
 */
public class ArrayListServices <T> {

   /**
    * This method takes an ArrayList as a parameter and returns a new ArrayList that 
    * does not contain any duplicate data. For example, if this list was passed in: 
    * [A, B, D, A, A, E, B], the method would return a list containing: [A, B, D, E]. 
    * The ordering of the data does not matter. 
    * Assume that the parameter is not null, but it may be an empty ArrayList, in which case 
    * your method returns a new, empty ArrayList.
    * Also note that the ArrayList that is returned must be a new ArrayList which is not the 
    * same as the ArrayList passed in. In other words, the parameter must not be changed by your method code.
    */
   public ArrayList<T> deDuplicate(ArrayList<T> inputList){
      //Write your comments on how you implemented the method.
      /**
       * Iterating through the ArrayList passed in
       * If the ouput list already contains the element from the InputList, the loop iteration is skipped
       * If the 'if' condition is not met, the value is added to the output list
       
      **/
      
      //TODO: Implement this method.

      ArrayList<T> outputList = new ArrayList<T>(); // initializing an output list and setting it equal to Inputlist

      for (int i = 0; i < inputList.size(); i++){ // iterating through the InputList 

         if (outputList.contains(inputList.get(i))){ // if output list contains the value already, the loop iteration is skipped
           
            continue;
         }
         outputList.add(inputList.get(i)); // if the loop iteration is not skipped, the value is added to the output list
      }
      return outputList;
   }

   /**
    * This method takes two ArrayLists as parameters and returns a new ArrayList that 
    * contains the intersection of the data in the ArrayLists passed in. The intersection 
    * contains the elements that occur in both lists.
    * For example, if these lists were passed in: [A, B, D, A, A, E, B], [B, E, C], the method 
    * would return a list containing: [B, E]. The ordering of the data does not matter. Note that 
    * the result does not contain any duplicates.
    * Assume that the parameters are not null, but one or both may be an empty ArrayList, in which 
    * case your method returns a new, empty ArrayList.
    * Also note that the ArrayList that is returned must be a new ArrayList which is not the same as 
    * the ArrayList passed in. In other words, the parameter must not be changed by your method code.
    */
   public ArrayList<T> getSetIntersection(ArrayList<T> listA, ArrayList<T> listB){
      //Write your comments on how you implemented the method.
      /**
       * used nested for loop to check every element with one another of the two ArrayList
       * if the value of ListA is same to any values of ListB, the value is added to a new outputList
       
      **/
      
      //TODO: Implement this method.
      ArrayList<T> outputList = new ArrayList<T>(); 

      for (int i = 0; i < listA.size(); i++){ // nested for loops

         for (int j = 0; j < listB.size(); j++){ // checking every element of list A with every element of List B

            if (listA.get(i).equals(listB.get(j))){ // if there is a same value, the value is added to the output list

               outputList.add(listA.get(i));
            }
         }
      }

      return deDuplicate(outputList); // first calling the deDuplicate method to remove duplicates and returning the new outputList
   }

   /**
    *  This method takes two ArrayLists as parameters and returns a new ArrayList that 
    * contains the set difference of the data in the ArrayLists passed in. The set difference 
    * contains the elements that occur only in one or the other list, but not in both.
    * For example, if these lists were passed in: [A, B, D, A, A, E, B], [B, E, C], the method 
    * would return a list containing: [A, C]. The ordering of the data does not matter. Note 
    * that the result does not contain any duplicates.
    * Assume that the parameters are not null, but one or both may be an empty ArrayList. In the 
    * case where one list is empty, your method returns a new ArrayList that contains all of the 
    * elements on the other list- with no duplicates. In the case where both lists are empty, your 
    * method returns a new, empty ArrayList.
    * Also note that the ArrayList that is returned must be a new ArrayList which is not the same 
    * as the ArrayList passed in. In other words, the parameter must not be changed by your method code.
    */
   public ArrayList<T> getSetDifference(ArrayList<T> listA, ArrayList<T> listB){
      //Write your comments on how you implemented the method.
      /**
       * using two loop, first checking every value of listA with entire listB. If value not present in listB, the value from listA
       * is added to the outputList
       * 
       * Same procedure again but every value of listB is compared to entire listA.
       
      **/
      
      //TODO: Implement this method.

      ArrayList<T> outputList = new ArrayList<T>(); // initializing a new outputList which will have all the different elements
      
      for (int i = 0; i < listA.size(); i++){ // firstly checking for same values in listA by comparing to listB

         if (!listB.contains(listA.get(i))){ // if listB does not contain the same value, the value from listA is added 
     
             outputList.add(listA.get(i));
         }
     }
     
     for (int j = 0; j < listB.size(); j++){ // same procedure as above, ListB every value is checked against listA
     
         if (!listA.contains(listB.get(j))){
     
             outputList.add(listB.get(j));
         }
     
     }
      return deDuplicate(outputList);
   }

}