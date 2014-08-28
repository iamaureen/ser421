import java.net.*;
import java.io.*;
import javax.net.ssl.*;

public class SecureSimpleClient {
  public static void main(String[] args) {
    try {
      SSLSocketFactory sf =
        (SSLSocketFactory)SSLSocketFactory.getDefault();
      SSLSocket con =
        (SSLSocket)sf.createSocket(args[0], Integer.parseInt(args[1]));

      PrintStream out = new PrintStream(con.getOutputStream());
      out.print(args[2]);
      out.write(0); // mark end of message
      out.flush();
      
      InputStreamReader in = new InputStreamReader(con.getInputStream());
      StringBuffer buf = new StringBuffer();
      int c;
      while ((c = in.read())!=-1)
        System.out.print((char)c);
      
      con.close();
    } catch (IOException e) {
      System.err.println(e); 
    }
  }
}
