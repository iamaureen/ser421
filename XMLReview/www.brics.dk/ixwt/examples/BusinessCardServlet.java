import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.jdom.*;
import org.jdom.output.*;

public class BusinessCardServlet extends HttpServlet {
  public void doGet(HttpServletRequest request, 
                    HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("text/xml;charset=UTF-8");
    long expires = new Date().getTime() + 1000*60*60*24; // now + 1 day
    response.addDateHeader("Expires", expires);
    XMLOutputter outputter = new XMLOutputter();
    outputter.output(getBusinessCard(), response.getOutputStream());
  }

  Document getBusinessCard() {
    Namespace ns = Namespace.getNamespace("http://businesscard.org");
    Element card = 
      (new Element("card", ns))
      .addContent((new Element("name", ns))
                  .setText("John Doe"))
      .addContent((new Element("title", ns))
                  .setText("CEO, Widget Inc."))
      .addContent((new Element("email", ns))
                  .setText("john.doe@widget.inc"))
      .addContent((new Element("phone", ns))
                  .setText("(202) 555-1414"))
      .addContent((new Element("logo", ns))
                  .setAttribute("uri", "widget.gif"));
    Map m = new HashMap();
    m.put("type", "text/xsl");
    m.put("href", "business_card.xsl");
    return (new Document())
      .addContent(new ProcessingInstruction("xml-stylesheet", m))
      .addContent(card);
  }
}
