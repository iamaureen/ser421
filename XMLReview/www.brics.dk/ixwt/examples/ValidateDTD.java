import java.io.*;
import org.jdom.*;
import org.jdom.input.*;

public class ValidateDTD {
  public static void main(String[] args) {
    try {
      SAXBuilder b = new SAXBuilder();
      b.setValidation(true);
      String msg = "No errors!";
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
