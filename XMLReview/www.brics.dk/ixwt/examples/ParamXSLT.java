import java.io.*;
import org.jdom.*; 
import org.jdom.transform.*; 
import org.jdom.input.*; 
import org.jdom.output.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class ParamXSLT {
  public static void main(String[] args) {
    try {
      SAXBuilder b = new SAXBuilder();
      Document d = b.build(new File(args[0])); 
      Transformer t = 
        TransformerFactory
          .newInstance()
          .newTransformer(new StreamSource(new File(args[1])));
      JDOMSource in = new JDOMSource(d);
      JDOMResult out = new JDOMResult();
      t.setParameter(args[2], args[3]);
      t.transform(in, out);
      Document h = out.getDocument();
      XMLOutputter outputter = new XMLOutputter();
      outputter.output(h, System.out);
    } catch (Exception e) { 
      System.err.println(e); 
    }
  }
}
