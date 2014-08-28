import java.io.*;
import org.jdom.*;
import org.jdom.input.*;

public class ValidateExternalXMLSchema {
  public static void main(String[] args) {
    try {
      SAXBuilder b = new SAXBuilder();
      b.setValidation(true);
      b.setProperty(
        "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
        "http://www.w3.org/2001/XMLSchema");
      b.setProperty(
        "http://java.sun.com/xml/jaxp/properties/schemaSource",
        args[1]);
      String msg = "No errors!"; 
      try {
        Document d = b.build(new File(args[0]));
        if (!d.getRootElement().getNamespaceURI().equals(args[2]))
          msg = "Wrong namespace of root element!";
      } catch (JDOMParseException e) {
        msg = e.getLineNumber()+":"+e.getColumnNumber()+" "+
              e.getMessage();
      }
      System.out.println(msg);
    } catch (Exception e) { 
      System.err.println(e); 
    }
  }
}
