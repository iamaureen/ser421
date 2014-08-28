import java.net.*;

public class MyAddress {
  public static void main(String[] args) {
    try {
      InetAddress a = InetAddress.getLocalHost();
      System.out.println("domain name: "+a.getHostName());
      System.out.println("IP address:  "+a.getHostAddress());
    } catch (UnknownHostException e) {
      System.out.println("Help! I don't know who I am!");
    }
  }
}
