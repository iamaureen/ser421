import java.io.*;
import java.util.*;
import org.jdom.*;
import org.jdom.input.*;

public class Model {
  static Document cardlist;
  static Namespace b = 
           Namespace.getNamespace("http://businesscard.org");
  String pattern;
  ArrayList cards = new ArrayList();
  int selected;

  static {
    try {
      cardlist = new SAXBuilder().build(new File("cardlist.xml"));
    } catch (Exception e) { 
      cardlist = new Document(new Element("cardlist", b));
    }
  }

  public Model() {}

  public void setPattern(String s) {
    pattern = s;
  }

  public List getMatches() {
    Iterator i = cardlist.getRootElement()
                         .getChildren("card", b).iterator();
    ArrayList matches = new ArrayList();
    while (i.hasNext()) {
      Element card = (Element)i.next();
      String name = card.getChildText("name", b);
      if (name.indexOf(pattern)!=-1) {
         cards.add(card);
         matches.add(name);
      }
    }
    return matches;
  }

  public void setSelected(int i) {
    selected = i;
  }

  String cs(String s) {
    if (s==null) 
      return "";
    return s;
  }

  public String getName() {
    return cs(((Element)cards.get(selected)).getChildText("name", b));
  }

  public String getTitle() {
    return cs(((Element)cards.get(selected)).getChildText("title", b));
  }

  public String getEmail() {
    return cs(((Element)cards.get(selected)).getChildText("email", b));
  }

  public String getPhone() {
    return cs(((Element)cards.get(selected)).getChildText("phone", b));
  }

  public String getUri() {
    Element logo = ((Element)cards.get(selected)).getChild("logo", b);
    if (logo==null) 
      return "";
    return logo.getAttributeValue("uri");
  }
}
