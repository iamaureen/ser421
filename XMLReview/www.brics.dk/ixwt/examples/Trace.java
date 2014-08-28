import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class Trace extends DefaultHandler {
  int indent = 0;

  void printIndent() {
    for (int i=0; i<indent; i++) 
      System.out.print("-");
  }

  public void startDocument() {
    System.out.println("start document");
    indent++;
  }

  public void endDocument() {
    indent--;
    System.out.println("end document");
  }

  public void startElement(String uri, String localName,
                           String qName, Attributes atts) {
    printIndent();
    System.out.println("start element: "+qName);
    indent++;
  }

  public void endElement(String uri, String localName, String qName) {
    indent--;
    printIndent();
    System.out.println("end element: "+qName);
  }

  public void ignorableWhitespace(char[] ch, int start, int length) {
    printIndent();
    System.out.println("whitespace, length "+length);
  }

  public void processingInstruction(String target, String data) {
    printIndent();
    System.out.println("processing instruction: "+target);
  }

  public void characters(char[] ch, int start, int length) {
    printIndent();
    System.out.println("character data, length "+length);
  }

  public static void main(String[] args) {
    try {
      Trace tracer = new Trace();
      XMLReader reader = XMLReaderFactory.createXMLReader();
      reader.setContentHandler(tracer);
      reader.parse(args[0]); 
    } catch (Exception e) { 
      System.err.println(e); 
    }
  }
}
