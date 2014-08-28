import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Controller extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response) 
      throws IOException, ServletException {
    HttpSession session = request.getSession();
    String command = request.getServletPath();
    Model model;
    if (command.equals("/search.do")) {
      model = new Model();
      session.setAttribute("model", model);
      getServletContext().getRequestDispatcher("/search_card2.jsp")
                         .forward(request, response);  
    } else if (command.equals("/select.do")) {
      model = (Model)session.getAttribute("model");
      model.setPattern(request.getParameter("pattern"));
      getServletContext().getRequestDispatcher("/select_card2.jsp")
                         .forward(request, response);  
    } else if (command.equals("/display.do")) {
      model = (Model)session.getAttribute("model");
      model.setSelected(Integer.parseInt(request.getParameter("i")));
      getServletContext().getRequestDispatcher("/display_card2.jsp")
                         .forward(request, response);  
    }
  }
}
