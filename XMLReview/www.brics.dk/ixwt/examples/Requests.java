import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Requests extends HttpServlet {
  public void doGet(HttpServletRequest request, 
                    HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>Requests</title></head><body>");
    out.println("<h1>Hello, visitor from "+
                request.getRemoteHost()+"</h1>");
    String useragent = request.getHeader("User-Agent");
    if (useragent!=null)
      out.println("You seem to be using "+useragent+"<p>");
    String name = request.getParameter("name");
    if (name==null)
      out.println("No <tt>name</tt> field was given!");
    else
      out.println("The value of the <tt>name</tt> field is: <tt>"+
                  htmlEscape(name)+"</tt>");
    out.println("</body></html>");
  }

  public void doPost(HttpServletRequest request, 
                     HttpServletResponse response)
      throws IOException, ServletException {
    doGet(request, response);
  }

  private String htmlEscape(String s) {
    StringBuffer b = new StringBuffer();
    for (int i = 0; i<s.length(); i++) {
      char c = s.charAt(i);
      switch (c) {
      case '<': b.append("&lt;"); break;
      case '>': b.append("&gt;"); break;
      case '"': b.append("&quot;"); break;
      case '\'': b.append("&apos;"); break;
      case '&': b.append("&amp;"); break;
      default: b.append(c);
      }
    }
    return b.toString();
  }
}
