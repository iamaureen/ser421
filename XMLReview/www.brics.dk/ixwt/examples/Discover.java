import java.io.*;
import org.jdom.*;
import org.jdom.output.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Discover extends HttpServlet {
  public void doGet(HttpServletRequest request, 
                    HttpServletResponse response)
      throws IOException, ServletException {
    try {
      response.setContentType("text/xml");
      Document doc = new Servers(getServletContext()).getServers();
      new XMLOutputter().output(doc, response.getWriter());
    } catch (Exception e) { 
      response.sendError(500, "Internal error");
    }
  }
}
