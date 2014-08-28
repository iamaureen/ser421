import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloWorld extends HttpServlet {
  public void doGet(HttpServletRequest request, 
                    HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>Servlet Example</title></head>"+
                "<body><h1>Hello World!</h1>"+
                "This page was last updated: "+new java.util.Date()+
                "</body></html>");
  }
}
