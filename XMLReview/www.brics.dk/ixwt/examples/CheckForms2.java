import java.io.*;
import java.util.*;
import org.xmlpull.v1.*;

public class CheckForms2 {
  static void report(XmlPullParser xpp, String s) {
    System.out.print(xpp.getLineNumber());
    System.out.print(":");
    System.out.print(xpp.getColumnNumber());
    System.out.println(" ---"+s);
  }

  public static void main (String args[]) 
      throws XmlPullParserException, IOException {
    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    factory.setNamespaceAware(true);
    factory.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);

    XmlPullParser xpp = factory.newPullParser();

    int formheight = 0;
    HashSet formnames = new HashSet();

    xpp.setInput(new FileReader(args[0]));
    int eventType = xpp.getEventType();
    while (eventType!=XmlPullParser.END_DOCUMENT) {
      if (eventType==XmlPullParser.START_TAG) {
        if (xpp.getNamespace().equals("http://www.w3.org/1999/xhtml") 
            && xpp.getName().equals("form")) {
          if (formheight>0) 
            report(xpp,"nested forms");
          String name = xpp.getAttributeValue("","name");
          if (formnames.contains(name)) 
            report(xpp,"duplicate form name");
          else
            formnames.add(name);
          formheight++;
        } else if (xpp.getName().equals("input") ||
                   xpp.getName().equals("select") ||
                   xpp.getName().equals("textarea"))
          if (formheight==0) 
            report(xpp,"form field outside form");
      }
      else if (eventType==XmlPullParser.END_TAG) {
        if (xpp.getNamespace().equals("http://www.w3.org/1999/xhtml") 
            && xpp.getName().equals("form"))
          formheight--;
      }
      eventType = xpp.next();
    }
  }
}
