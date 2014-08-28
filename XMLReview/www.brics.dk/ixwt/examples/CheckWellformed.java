import java.io.*;
import org.jdom.*;
import org.jdom.input.*;

public class CheckWellformed {
  public static void main(String[] args) {
    try {
      SAXBuilder b = new SAXBuilder();
      String msg = "Document is well-formed XML!";
      try {
        Document d = b.build(new File(args[0]));
      } catch (JDOMParseException e) {
        msg = e.getMessage();
      }
      System.out.println(msg);
    } catch (Exception e) { 
      System.err.println(e); 
    }
  }
}
