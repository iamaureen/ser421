import java.io.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
import org.jdom.transform.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class Retrieve extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws IOException, ServletException {
    try {
      response.setContentType("text/xml");
      Document doc = new Jokes(getServletContext()).getJokes();
      Transformer t =
        TransformerFactory.newInstance()
          .newTransformer(new StreamSource(new File(
            getServletContext().getInitParameter("RetrieveXSLT"))));
      JDOMSource in = new JDOMSource(doc);
      JDOMResult out = new JDOMResult();
      t.setParameter("category", request.getParameter("category"));
      t.transform(in, out);
      new XMLOutputter().output(out.getDocument(),
                                response.getWriter());
    } catch (Exception e) {
      response.sendError(500, "Internal error");
    }
  }
}

