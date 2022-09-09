package structures;

public class LinearCollisionHandler <K> implements CollisionHandler <K>{
    private int probeLength;

    /**
  * Constructors to set probeLength to 1, or a different length.
  */
    public LinearCollisionHandler(){
        this.probeLength = 1;
    }

    public LinearCollisionHandler(int probeLength){
        this.probeLength = probeLength;
    }


   public int probe(int index, boolean[] activeArray, int M) {

      int curIndex = index;
      while(activeArray[curIndex]){
          curIndex = (curIndex + probeLength) % M;
      }
      return curIndex;
   }

   public int search(int startIndex, K target, K[] keyArray, boolean [] activeArray, int M){

      int curIndex = startIndex;
      while(activeArray[curIndex]){
          if(keyArray[curIndex].equals(target)){
              return curIndex;
          }
          curIndex = (curIndex + probeLength) % M;
      }
      return -1;
        
   }
}
