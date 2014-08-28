import java.net.*;
import java.io.*;

public class ImFeelingLucky {
  public static void main(String[] args) {
    try {
      Socket con = new Socket("www.google.com", 80);
        
      String req = "/search?"+
        "q="+URLEncoder.encode(args[0], "UTF8")+"&"+
        "btnI="+URLEncoder.encode("I'm Feeling Lucky", "UTF8");

      BufferedWriter out = 
        new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), 
                                                  "UTF8"));
      out.write("GET "+req+" HTTP/1.1\r\n");
      out.write("Host: www.google.com\r\n");
      out.write("User-Agent: IXWT\r\n\r\n");
      out.flush();

      BufferedReader in = 
      new BufferedReader(new InputStreamReader(con.getInputStream()));
      String line;
      System.out.print("The prophet spoke thus: ");
      while ((line = in.readLine()) != null) {
        if (line.startsWith("Location:")) {
          System.out.println("Direct your browser to "+
                             line.substring(9).trim()+
                             " and you shall find great happiness in life.");
          break;
        } else if (line.trim().length()==0) {
          System.out.println("I am sorry - my crystal ball is blank.");
          break;
        }
      }
      con.close();
    } catch (IOException e) {
      System.err.println(e); 
    }
  }
}
