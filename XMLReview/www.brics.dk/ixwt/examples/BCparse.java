import java.io.*;
import java.util.*;
import org.jdom.*; 
import org.jdom.output.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class BCparse extends DefaultHandler {
  Vector contents = new Vector();
  Document doc = null;
  Element card = null;
  Element field = null;
  Namespace b = Namespace.getNamespace("http://businesscard.org");

  public void startElement(String uri, String localName,
                           String qName, Attributes atts) {
    if (localName.equals("card")) 
      card = new Element("card", b);
    else if (localName.equals("name")) 
      field = new Element("name", b);
    else if (localName.equals("title")) 
      field = new Element("title", b);
    else if (localName.equals("email")) 
      field = new Element("email", b);
    else if (localName.equals("phone")) 
      field = new Element("phone", b);
    else if (localName.equals("logo")) { 
      field = new Element("logo", b);
      field.setAttribute("uri", atts.getValue("", "uri"));
    }
  }

  public void endElement(String uri, String localName, String qName) {
    if (localName.equals("card")) 
      contents.add(card);
    else if (localName.equals("cardlist")) {
      Element cardlist = new Element("cardlist", b);
      cardlist.setContent(contents);
      doc = new Document(cardlist);
    } else {
      card.addContent(field);
      field = null;
    }
  }

  public void characters(char[] ch, int start, int length) {
    if (field!=null)
      field.addContent(new String(ch, start, length));
  }

  public static void main(String[] args) {
    try {
      BCparse handler = new BCparse();
      XMLReader reader = XMLReaderFactory.createXMLReader();
      reader.setContentHandler(handler);
      reader.parse(args[0]);
      XMLOutputter outputter = new XMLOutputter();
      outputter.output(handler.doc, System.out);
    } catch (Exception e) { 
      System.err.println(e); 
    }
  }
}
