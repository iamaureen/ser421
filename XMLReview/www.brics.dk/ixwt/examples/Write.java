import java.io.*;
import java.util.*;
import org.jdom.*;
import org.jdom.filter.*;
import org.jdom.input.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;

public class Write extends HttpServlet {
  static Namespace cml = 
    Namespace.getNamespace("http://www.brics.dk/ixwt/category");
  static Namespace sml = 
    Namespace.getNamespace("http://www.brics.dk/ixwt/servers");
  
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws IOException, ServletException {
    try {
      HashSet categories = new HashSet();
      ServletContext context = getServletContext();
      SAXBuilder b = new SAXBuilder();
      Element servers = 
        b.build(new URL(context.getInitParameter("DiscoverURL")))
         .getRootElement();
      Iterator i = 
        servers.getDescendants(new ElementFilter("list", sml));
      while (i.hasNext()) {
        Element s = (Element)i.next();
        try {
          Element c = b.build(new URL(s.getText())).getRootElement();
          Iterator j = c.getChildren().iterator();
          while (j.hasNext()) 
            categories.add(((Element)j.next()).getText());
        } catch (Exception e) {}
      }
      request.setAttribute("categories", categories);
      RequestDispatcher dispatcher = 
        context.getRequestDispatcher("/collect.jsp");
      dispatcher.forward(request, response);
    } catch (Exception e) {         
      response.sendError(500, "Internal Error");
    }
  }
}

