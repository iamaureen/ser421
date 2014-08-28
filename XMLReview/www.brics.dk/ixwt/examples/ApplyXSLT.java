import java.io.*; 
import org.jdom.*; 
import org.jdom.transform.*; 
import org.jdom.input.*; 
import org.jdom.output.*; 

public class ApplyXSLT {
  public static void main(String[] args) {
    try {
      System.setProperty("javax.xml.transform.TransformerFactory", 
                         "net.sf.saxon.TransformerFactoryImpl");
      SAXBuilder b = new SAXBuilder();
      Document d = b.build(new File(args[0])); 
      XSLTransformer t = new XSLTransformer(args[1]);
      Document h = t.transform(d);
      XMLOutputter outputter = new XMLOutputter();
      outputter.output(h, System.out);
    } catch (Exception e) { 
      System.err.println(e); 
    }
  }
}
