import java.net.*;
import java.io.*;
import javax.net.ssl.*;

public class SecureSimpleServer {
  public static void main(String[] args) {
    try {
      SSLServerSocketFactory sf = 
        (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
      SSLServerSocket ss = 
        (SSLServerSocket)sf.createServerSocket(Integer.parseInt(args[0]));

      while (true) {
        SSLSocket con = (SSLSocket)ss.accept();
          
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
