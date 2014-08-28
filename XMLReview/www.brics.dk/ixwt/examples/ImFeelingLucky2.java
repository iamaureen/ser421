import java.net.*;
import java.io.*;

public class ImFeelingLucky2 {
  public static void main(String[] args) {
    try {
      String req = "http://www.google.com/search?"+
        "q="+URLEncoder.encode(args[0], "UTF8")+"&"+
        "btnI="+URLEncoder.encode("I'm Feeling Lucky", "UTF8");

      HttpURLConnection con = 
        (HttpURLConnection) (new URL(req)).openConnection();

      con.setRequestProperty("User-Agent", "IXWT");
      con.setInstanceFollowRedirects(false);

      String loc = con.getHeaderField("Location"); 

      System.out.print("The prophet spoke thus: ");
      if (loc!=null)
        System.out.println("Direct your browser to "+loc+
                           " and you shall find great happiness in life.");
      else
        System.out.println("I am sorry - my crystal ball is blank.");

    } catch (IOException e) {
      System.err.println(e); 
    }
  }
}
