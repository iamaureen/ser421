import java.io.*;
import org.jdom.*;
import org.jdom.output.*;
import org.jdom.transform.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class List extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws IOException, ServletException {
    try {
      response.setContentType("text/xml");
      Document doc = new Jokes(getServletContext()).getJokes();
      String xslt =
        getServletContext().getInitParameter("CategoriesXSLT");
      XSLTransformer t = new XSLTransformer(xslt);
      new XMLOutputter().output(t.transform(doc),
                                response.getWriter());
    } catch (Exception e) {
      response.sendError(500, "Internal error");
    }
  }
}

