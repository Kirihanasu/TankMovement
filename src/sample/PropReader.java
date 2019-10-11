package sample;

import java.io.*;

public class PropReader{
  File file;
  BufferedReader reader;

  public PropReader(File file){
    try{
      this.file = file;
      reader = new BufferedReader(new FileReader(file));
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }
  }

  public String get(String key){
    try{
      String st;
      while((st = reader.readLine()) != null)
        if(st.startsWith(key + "=")) return st.replace(key + "=", "");
      return null;
    }catch(IOException e){
      e.printStackTrace();
      return null;
    }
  }
}