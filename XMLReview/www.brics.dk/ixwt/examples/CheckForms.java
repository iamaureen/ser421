import java.io.*;
import java.util.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class CheckForms extends DefaultHandler {
  int formheight = 0;
  HashSet formnames = new HashSet(); 
  Locator locator;

  public void setDocumentLocator(Locator locator) {
    this.locator = locator;
  }

  void report(String s) {
    System.out.print(locator.getLineNumber());
    System.out.print(":");
    System.out.print(locator.getColumnNumber());
    System.out.println(" ---"+s);
  }

  public void startElement(String uri, String localName,
                           String qName, Attributes atts) {
    if (uri.equals("http://www.w3.org/1999/xhtml")) { 
      if (localName.equals("form")) {
        if (formheight>0) 
          report("nested forms");
        String name = atts.getValue("", "name");
        if (formnames.contains(name)) 
          report("duplicate form name");
        else
          formnames.add(name);
        formheight++;
      } else if (localName.equals("input") ||
                 localName.equals("select") ||
                 localName.equals("textarea"))
        if (formheight==0) 
          report("form field outside form");
    }
  }

  public void endElement(String uri, String localName, String qName) {
    if (uri.equals("http://www.w3.org/1999/xhtml")) 
      if (localName.equals("form"))
        formheight--;
  }

  public static void main(String[] args) {
    try {
      CheckForms handler = new CheckForms();
      XMLReader reader = XMLReaderFactory.createXMLReader();
      reader.setContentHandler(handler);
      reader.parse(args[0]);
    } catch (Exception e) { 
      System.err.println(e); 
    }
  }
}
