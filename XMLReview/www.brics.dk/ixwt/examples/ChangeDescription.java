import java.io.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

public class ChangeDescription {
  public static void main(String[] args) {
    try {
      SAXBuilder b = new SAXBuilder();
      Document d = b.build(new File("recipes.xml"));
      Namespace rcp = 
        Namespace.getNamespace("http://www.brics.dk/ixwt/recipes");
      d.getRootElement()
       .getChild("description", rcp)
       .setText("Cool recipes!");
      XMLOutputter outputter = new XMLOutputter();
      outputter.output(d, System.out);
    } catch (Exception e) { 
      System.err.println(e); 
    }
  }
}
