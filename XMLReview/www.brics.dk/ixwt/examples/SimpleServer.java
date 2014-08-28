import java.net.*;
import java.io.*;

public class SimpleServer {
  public static void main(String[] args) {
    try {
      ServerSocket ss = new ServerSocket(Integer.parseInt(args[0]));

      while (true) {
        Socket con = ss.accept();
          
        InputStreamReader in = new InputStreamReader(con.getInputStream());
        StringBuffer msg = new StringBuffer();
        int c;
        while ((c = in.read())!=0) // we use 0 as end-of-message marker
          msg.append((char)c);
      
        PrintWriter out = new PrintWriter(con.getOutputStream());
        out.print("Simon says: "+msg);
        out.flush();
      
        con.close();
      }
    } catch (IOException e) {
      System.err.println(e); 
    }
  }
}
