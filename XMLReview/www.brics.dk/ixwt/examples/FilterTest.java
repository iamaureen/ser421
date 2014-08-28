import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

class PIFilter extends XMLFilterImpl {
  public void processingInstruction(String target, String data)
      throws SAXException {}
}

class IDFilter extends XMLFilterImpl {
  int id = 0;
  public void startElement(String uri, String localName,
                           String qName, Attributes atts) 
      throws SAXException {
    AttributesImpl idatts = new AttributesImpl(atts);
    idatts.addAttribute("", "id", "id", "ID",
                        new Integer(id++).toString());
    super.startElement(uri, localName, qName, idatts);
  }
}

class CountFilter extends XMLFilterImpl {
  public int count = 0;
  public void characters(char[] ch, int start, int length) 
      throws SAXException {
    count = count+length;
    super.characters(ch, start, length);
  }
}

public class FilterTest {
  public static void main(String[] args) {
    try {
      XMLReader reader = XMLReaderFactory.createXMLReader();
      PIFilter pi = new PIFilter();
      pi.setParent(reader);
      IDFilter id = new IDFilter();
      id.setParent(pi);
      CountFilter count = new CountFilter();
      count.setParent(id);
      count.parse(args[0]);
      System.out.println(count.count);
    } catch (Exception e) { 
      System.err.println(e); 
    }
  }
}
