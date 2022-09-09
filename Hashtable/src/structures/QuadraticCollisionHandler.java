package structures;

public class QuadraticCollisionHandler <K> implements CollisionHandler <K>{

    // constants for the quadratic 
    private int c1;
    private int c2;

    public QuadraticCollisionHandler(){
        this.c1 = 1;
        this.c2 = 1;
    }

    public QuadraticCollisionHandler(int firstParam, int secondParam){
        this.c1 = firstParam;
        this.c2 = secondParam;
    }

  public int probe(int startIndex, boolean[] activeArray, int M) {

        int curIndex = startIndex;
        int i = 1;
        while(activeArray[curIndex]){
            curIndex = (curIndex + c1*i + c2*(i*i)) % M;
            i++;
        }
        return curIndex;
    }

 public int search(int startIndex, K target, K[] keyArray, boolean [] activeArray, int M){
        int curIndex = startIndex;
        int i = 1;
        while(activeArray[curIndex] == true){
            if(keyArray[curIndex].equals(target)){
                return curIndex;
            }
            curIndex = (curIndex + c1*i + c2*(i*i)) % M;
            i++;
        }
        return -1;
    }
}

