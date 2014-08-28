import java.io.*;
import org.jdom.*;
import org.jdom.input.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Submit extends HttpServlet {  
  static Namespace jml = 
    Namespace.getNamespace("http://www.brics.dk/ixwt/jokes");

  public void doPost(HttpServletRequest request,
                     HttpServletResponse response)
      throws IOException, ServletException {
    PrintWriter out = response.getWriter();
    SAXBuilder b = new SAXBuilder();
    b.setValidation(true);
    b.setProperty(
      "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
      "http://www.w3.org/2001/XMLSchema");
    b.setProperty(
      "http://java.sun.com/xml/jaxp/properties/schemaSource",
      getServletContext().getInitParameter("JokesXSD"));
    try {
      Document doc = b.build(request.getInputStream());
      if (!doc.getRootElement().getNamespaceURI().equals(jml))
        response.sendError(400, "Wrong namespace of root element!");
      else 
        new Jokes(getServletContext()).addJokes(doc);
    } catch (Exception e) { 
      response.sendError(500, "Internal error");
    }
  } 
}   

