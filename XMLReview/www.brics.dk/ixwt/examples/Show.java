import java.io.*;
import java.util.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;

public class Show extends HttpServlet {
  static Namespace jml = 
    Namespace.getNamespace("http://www.brics.dk/ixwt/jokes");
  static Namespace cml = 
    Namespace.getNamespace("http://www.brics.dk/ixwt/category");
  static Namespace sml = 
    Namespace.getNamespace("http://www.brics.dk/ixwt/servers");
  
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws IOException, ServletException {
    try {
      Element jokes = new Element("collection", jml);
      response.setContentType("text/xml");
      ServletContext context = getServletContext();
      SAXBuilder b = new SAXBuilder();
      Element servers = 
        b.build(new URL(context.getInitParameter("DiscoverURL")))
         .getRootElement();
      String category = request.getParameter("category");
      Iterator i = servers.getChildren().iterator();
      while (i.hasNext()) {
        Element s = (Element)i.next();
        try {
          URL r = new URL(s.getChildText("retrieve", sml)+
                          URLEncoder.encode("?category="+category));
          Element j = b.build(r).getRootElement();
          jokes = jokes.addContent(j.removeContent());
        } catch (Exception e) {}
      }
      Document doc = new Document();
      Map m = new HashMap();
      m.put("type", "text/xsl");
      m.put("href", "jokes.xsl");   
      doc.addContent(new ProcessingInstruction("xml-stylesheet", m))
         .addContent(jokes);
      new XMLOutputter().output(doc, response.getWriter());
    } catch (Exception e) {
      response.sendError(500, "Internal Error");
    }
  }
}

