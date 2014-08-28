import java.io.*;
import java.util.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;

public class Compose extends HttpServlet {
  static Namespace jml = 
    Namespace.getNamespace("http://www.brics.dk/ixwt/jokes");

  String pad2(int i) {
    if (i<10) 
      return "0"+i; 
    else 
      return String.valueOf(i);
  }
  
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws IOException, ServletException {
    try {
      response.setContentType("text/html");
      HttpSession session = request.getSession(true);
      ServletContext context = getServletContext();
      String part = request.getParameter("part");
      Element joke;
      if (part==null) {
        String category = request.getParameter("category");
        String newcat = request.getParameter("newcat");
        if (!newcat.equals("")) 
          category = newcat;
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        SAXBuilder b = new SAXBuilder();
        String id = 
          b.build(new URL(context.getInitParameter("UniqueURL"))) 
           .getRootElement().getText();
        Calendar calendar = new GregorianCalendar();
        String date = calendar.get(Calendar.YEAR)+"-"+
                      pad2(calendar.get(Calendar.MONTH))+"-"+
                      pad2(calendar.get(Calendar.DATE));
        joke = new Element("joke", jml)
                 .setAttribute("id", id)
                 .setAttribute("category", category)
                 .addContent(new Element("title", jml)
                                 .setText(title))
                 .addContent(new Element("date", jml)
                                 .setText(date))
                 .addContent(new Element("author", jml)
                                 .setText(author));
        session.setAttribute("joke", joke);
      } else {
        joke = (Element)session.getAttribute("joke");
        String contents = request.getParameter("contents");
        Element e;
        if (part.equals("setup"))
          e = new Element("setup", jml).setText(contents);
        else
          e = new Element("punchline", jml).setText(contents);
        joke.addContent(e);
      }
      if (part==null || part.equals("setup")) {
        RequestDispatcher dispatcher =
          context.getRequestDispatcher("/contents.jsp");
        dispatcher.forward(request, response);
      } else {
        URL url = new URL(context.getInitParameter("SubmitURL"));
        HttpURLConnection connection = 
          (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        OutputStream outs = connection.getOutputStream();
        Document doc = new Document(new Element("collection", jml)
                        .addContent(joke));
        new XMLOutputter().output(doc, outs);
        outs.close();
        if (connection.getResponseCode()!=200) 
          throw new ServletException("Submit failed");
        RequestDispatcher dispatcher = 
          getServletContext().getRequestDispatcher("/thanks.jsp"); 
        dispatcher.forward(request, response); 
      }
    } catch (Exception e) { 
      response.sendError(500, "Internal error");
    }
  }
}

