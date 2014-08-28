import java.io.*;
import org.jdom.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Register extends HttpServlet {  
   public void doGet(HttpServletRequest request,
                     HttpServletResponse response)
       throws IOException, ServletException {
     response.setContentType("text/html");
     PrintWriter out = response.getWriter();
     String list = request.getParameter("list");
     String retrieve = request.getParameter("retrieve");
     out.println("<html>");
     out.println("<head><title>Joke MetaServer</title></head>");
     out.println("<body>");
     try {
       new Servers(getServletContext()).addServer(list, retrieve);
       out.println("Joke Server successfully registered.");
     } catch (Exception e) {
       out.println("Joke Server failed to register.");
     }
     out.println("</body>");
     out.println("</html>");
  } 
}   

