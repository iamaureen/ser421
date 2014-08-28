import java.net.*;
import java.io.*;

public class SimpleClient {
  public static void main(String[] args) {
    try {
      Socket con = new Socket(args[0], Integer.parseInt(args[1]));
        
      PrintStream out = new PrintStream(con.getOutputStream());
      out.print(args[2]);
      out.write(0); // mark end of message
      out.flush();
      
      InputStreamReader in = new InputStreamReader(con.getInputStream());
      int c;
      while ((c = in.read())!=-1)
        System.out.print((char)c);
      
      con.close();
    } catch (IOException e) {
      System.err.println(e); 
    }
  }
}
