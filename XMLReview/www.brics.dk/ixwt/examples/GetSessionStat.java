import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class GetSessionStat extends HttpServlet {
  public void doGet(HttpServletRequest request, 
                    HttpServletResponse response)
      throws IOException, ServletException {
    ServletContext c = getServletContext();
    int active = ((Integer)c.getAttribute("sessions_active")).intValue();
    int max = ((Integer)c.getAttribute("sessions_max")).intValue();
    response.setContentType("text/html");
    response.setDateHeader("Expires", 0);
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>Widget Inc.</title></head><body>"+
                "<h1>Session Statistics</h1>"+
                "Current number of active sessions: "+active+"<br>"+
                "Maximum number of active sessions: "+max+
                "</body></html>");
  }
}
