package graph;
import java.util.ArrayList;
/**
 * This class implements general operations on a graph as specified by UndirectedGraphADT.
 * It implements a graph where data is contained in Vertex class instances.
 * Edges between verticies are unweighted and undirected.
 * A graph coloring algorithm determines the chromatic number. 
 * Colors are represented by integers. 
 * The maximum number of vertices and colors must be specified when the graph is instantiated.
 * You may implement the graph in the manner you choose. See instructions and course material for background.
 */
 
 public class UndirectedUnweightedGraph<T> implements UndirectedGraphADT<T> {
   // private class variables here.
   
   private int MAX_VERTICES;
   private int MAX_COLORS;
    private ArrayList<Vertex<T>> vertices;
    private ArrayList<ArrayList<Integer>> adjacencyMatrix;
    private int numEdges;


   
   /**
    * Initialize all class variables and data structures. 
   */   
   public UndirectedUnweightedGraph (int maxVertices, int maxColors){
      MAX_VERTICES = maxVertices;
      MAX_COLORS = maxColors; 
      vertices = new ArrayList<Vertex<T>>();
      adjacencyMatrix = new ArrayList<ArrayList<Integer>>();
      numEdges = 0;



   }

   /**
    * Add a vertex containing this data to the graph.
    * Throws Exception if trying to add more than the max number of vertices.
   */
   public void addVertex(T data) throws Exception {
      if(vertices.size() >= MAX_VERTICES){
         throw new Exception("Cannot add more than " + MAX_VERTICES + " vertices.");
      }
      Vertex<T> newVertex = new Vertex<T>(data);
      vertices.add(newVertex);
      adjacencyMatrix.add(new ArrayList<Integer>());
      for(int i = 0; i < MAX_VERTICES; i++){
         adjacencyMatrix.get(vertices.size()-1).add(0);
      }
      
   }
   
   /**
    * Return true if the graph contains a vertex with this data, false otherwise.
   */   
   public boolean hasVertex(T data){
      for(int i = 0; i < vertices.size(); i++){
         if(vertices.get(i).getData().equals(data)){
            return true;
         }
      }
      return false;
   } 

   /**
    * Add an edge between the vertices that contain these data.
    * Throws Exception if one or both vertices do not exist.
   */   
   public void addEdge(T data1, T data2) throws Exception{
    int data1Index = 0, data2Index = 0;
      for(int i = 0; i < vertices.size(); i++){
         if(vertices.get(i).getData().equals(data1)){
            data1Index = i;
         }
         if(vertices.get(i).getData().equals(data2)){
            data2Index = i;
         }
      }
      if(data1Index == 0 && data2Index == 0){
         throw new Exception("One or both vertices do not exist.");
      }
      adjacencyMatrix.get(data1Index).set(data2Index, 1);
      adjacencyMatrix.get(data2Index).set(data1Index, 1);
      numEdges++;


   }


   /**
    * Get an ArrayList of the data contained in all vertices adjacent to the vertex that
    * contains the data passed in. Returns an ArrayList of zero length if no adjacencies exist in the graph.
    * Throws Exception if a vertex containing the data passed in does not exist.
   */   
   public ArrayList<T> getAdjacentData(T data) throws Exception{
      ArrayList<T> output = new ArrayList<T>();
      int vert=-1;
      for(int i = 0; i < vertices.size(); i++){
         if(vertices.get(i).getData().equals(data)){
            vert = i;
         }
      }
      if(vert == -1){
         throw new Exception("Vertex does not exist.");
      }
      for(int i = 0; i < vertices.size(); i++){
         if(adjacencyMatrix.get(vert).get(i) == 1){
            output.add(vertices.get(i).getData());
         }
      }
      return output;
    
   }
   
   /**
    * Returns the total number of vertices in the graph.
   */   
   public int getNumVertices(){
      return vertices.size();
   }

   /**
    * Returns the total number of edges in the graph.
   */   
   public int getNumEdges(){
      return numEdges;
   }
 
   public int getChromaticNumber() throws Exception{
      int highestColorUsed = -1;
      int colorToUse = -1;
      // Start with a vertex (we are dealing with a connected graph).
      for(Vertex <T> curVertex : vertices){
          if(curVertex.getColor() == -1){
              colorToUse = getColorToUse(curVertex);
              curVertex.setColor(colorToUse);
              if(colorToUse > highestColorUsed){
                highestColorUsed = colorToUse;
              }
          }
        }
      if(highestColorUsed == MAX_COLORS){
         throw new Exception("All colors have been used, the Max number of colors has been exceeded.");
      }
      return highestColorUsed+1;
   }

   /* helper methods */
    private int getColorToUse(Vertex curVertex) throws Exception{
      int colorToUse = -1;
      boolean[] adjColorsUsed = new boolean[MAX_COLORS];
      // initially all false.
      ArrayList<Vertex> adjVertsList = getAdjacentVertices(curVertex);
      for(int i = 0; i < adjVertsList.size(); i++){
         if(adjVertsList.get(i).getColor() != -1){
            adjColorsUsed[adjVertsList.get(i).getColor()] = true;
         }
      }
      // use the array to find the lowest color to use:
      for(int i = 0; i < adjColorsUsed.length; i++){
         if(adjColorsUsed[i] == false){
            colorToUse = i;
            break;
         }
      }
      if(colorToUse == -1){
         throw new Exception("All colors have been used, the Max number of colors has been exceeded.");
      }
      return colorToUse;
    }

    private ArrayList<Vertex> getAdjacentVertices(Vertex curVertex) {
      ArrayList<Vertex> adjVertsList = new ArrayList<Vertex>();
      int vertInd = -1;
      for(int i = 0; i < vertices.size(); i++){
        if(vertices.get(i).equals(curVertex)) {
          vertInd = i;
        }
      }
      if(vertInd == -1){
        return adjVertsList;
      }
      for(int i = 0; i < vertices.size(); i++){
        if(adjacencyMatrix.get(vertInd).get(i) == 1){
          adjVertsList.add(vertices.get(i));
        }
      }
      return adjVertsList;

   }
}