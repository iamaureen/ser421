import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class Unique extends HttpServlet {
  public void doGet(HttpServletRequest request, 
                    HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("text/xml");
    long x = Math.abs(new Random().nextLong());
    response.getWriter().println("<id>"+x+"</id>");
  }
}

