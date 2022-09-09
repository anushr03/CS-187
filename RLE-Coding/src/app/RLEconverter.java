package app;

import java.util.Scanner;
import java.io.*;

public class RLEconverter {
   private final static int DEFAULT_LEN = 100; // used to create arrays.
  
  public void compressFile(String fileName) throws IOException{
    Scanner scan = new Scanner(new FileReader(fileName));
    String line = null;
    String[] decompressed = new String [DEFAULT_LEN];
    int dataSize = 0;
    while(scan.hasNext()){
      line = scan.next();
      if(line != null && line.length()>0)
        decompressed[dataSize]=line;
        dataSize++;
    }
    scan.close();
    char[] fileChars = discoverAllChars(decompressed, dataSize); 
    String[] compressed = compressLines(decompressed, dataSize, fileChars);
    writeFile(getCompressedFileStr(compressed, fileChars), "RLE_"+fileName);
  }
  
public String compressLine(String line, char[] fileChars){
   String compressedString = "";
   int count = 0;
    for(int i = 0; i < line.length(); i++){
        if(line.charAt(i) == fileChars[0]){
            count++;
        }
        else{
            compressedString+=count+",";
            count = 1;
            fileChars[0] = line.charAt(i);
        }
    }
    compressedString+=count;
    return compressedString;
}

  public String[] compressLines(String[] lines, int dataSize, char[] fileChars){
      //TODO: Implement this method
      String[] compressed = new String[dataSize];
      for(int i = 0; i < dataSize; i++){
          compressed[i] = compressLine(lines[i], fileChars);
      }
      return compressed;
}


public String getCompressedFileStr(String[] compressed, char[] fileChars) {
    //TODO: Implement this method
    String compressedFileStr = "";
    compressedFileStr+=fileChars[0]+","+fileChars[1]+"\n";
    for (int i =0; i<fileChars.length; i++){
      if(i == fileChars.length-1) {
          compressedFileStr += fileChars[i];
          break;
      }
      compressedFileStr+= fileChars[i]+",";
  }
    for(int i = 0; i < compressed.length; i++){
        compressedFileStr+=compressed[i]+"\n";
    }
    return compressedFileStr;
}

  public void decompressFile(String fileName) throws IOException{
    Scanner scan = new Scanner(new FileReader(fileName));
    String line = null;
    String[] compressed = new String [DEFAULT_LEN];
    int dataSize =0;
    while(scan.hasNext()){
      line = scan.next();
      if(line != null && line.length()>0)
        compressed[dataSize]=line;
        dataSize++;
    }
    scan.close();
    String[] decompressed = decompressLines(compressed, dataSize);
    writeFile(getDecompressedFileStr(decompressed), "DECOMP_"+fileName);
  }
 
   public String decompressLine(String line, char[] fileChars){
      //TODO: Implement this method
      String decompressedString = "";
      int charArray = 0;
      String[] charCount = line.split(",");


      for (int i =0; i<charCount.length; i++){
        if(Integer.parseInt(charCount[i]) == 0){;
          charArray++;
          continue;
        }
        
        for(int j = 0; j < Integer.parseInt(charCount[i]); j++){
          decompressedString+=fileChars[charArray];
        }
        if (charArray == fileChars.length-1){
          charArray = 0;
        }
        else{
          charArray++;
        }
      }
      return decompressedString;
   }

  public String[] decompressLines(String[] lines, int dataSize){
     //TODO: Implement this method
      char[] fileChars = lines[0].split(",")[0].toCharArray();
      String[] decompressed = new String[dataSize];
      for(int i = 1; i < dataSize; i++){
          decompressed[i] = decompressLine(lines[i], fileChars);
      }
      return decompressed;
   }
  
  public String getDecompressedFileStr(String[] decompressed){
    
     String decompressedFile = "";
      for(int i = 0; i < decompressed.length; i++){
        if(decompressed[i] == null){
          break;
        }
          decompressedFile+=decompressed[i]+"\n";
      }
      return decompressedFile;
  }

  // assume the file contains only 2 different ascii characters.
  public char[] discoverAllChars(String[] decompressed, int dataSize){
//TODO: Implement this method
    char[] fileChars = new char[2];
    fileChars[0] = decompressed[0].charAt(0);
    fileChars[1] = decompressed[0].charAt(2);
    for(int i = 1; i < dataSize; i++){
      for(int j = 0; j < decompressed[i].length(); j++){
        if(!(fileChars[0] == decompressed[i].charAt(j))){
          fileChars[1] = decompressed[i].charAt(j);
          break;
        }
      }
    }
    return fileChars;
}

   public void writeFile(String data, String fileName) throws IOException{
		PrintWriter pw = new PrintWriter(fileName);
      pw.print(data);
      pw.close();
   }
}