import java.io.*;
import org.jdom.*;
import org.jdom.input.*;

public class ValidateXMLSchema {
  public static void main(String[] args) {
    try {
      SAXBuilder b = new SAXBuilder();
      b.setValidation(true);
      b.setProperty(
        "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
        "http://www.w3.org/2001/XMLSchema");
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
