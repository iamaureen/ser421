import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.jdom.*; 
import org.jdom.transform.*; 
import org.jdom.input.*; 
import org.jdom.output.*; 

public class XSLTFilter implements Filter {
  ServletContext context;

  public void init(FilterConfig c) throws ServletException {
    context = c.getServletContext();
  }

  public void destroy() {}

  public void doFilter(ServletRequest request, 
                       ServletResponse response, 
                       FilterChain chain) 
      throws IOException, ServletException {
    HttpServletRequest hreq = (HttpServletRequest)request;
    HttpServletResponse hresp = (HttpServletResponse)response;
    boolean client_capable = 
      checkXSLTSupport(hreq.getHeader("User-Agent"));
    ServletResponse res;
    if (client_capable)
      res = response;
    else
      res = new BufferingResponseWrapper(hresp);
    chain.doFilter(request, res);
    if (!client_capable) {
      try {
        hresp.setContentType("application/xhtml+xml");
        transform(((BufferingResponseWrapper)res).getReader(), 
                  response.getWriter());
      } catch (Throwable e) {
        context.log("XSLT transformation error", e);
        hresp.sendError(500, "XSLT transformation error");
      }
    }
  }

  boolean checkXSLTSupport(String user_agent) {
    if (user_agent==null)
      return false;
    return
      user_agent.indexOf("MSIE 5.5")!=-1 ||
      user_agent.indexOf("MSIE 6")!=-1 ||
      user_agent.indexOf("Gecko")!=-1;
  }

  void transform(Reader in, Writer out) 
      throws JDOMException, IOException {
    System.setProperty("javax.xml.transform.TransformerFactory", 
                       "net.sf.saxon.TransformerFactoryImpl");
    SAXBuilder b = new SAXBuilder();
    Document d = b.build(in); 
    List pi = d.getContent(new org.jdom.filter.ContentFilter
                           (org.jdom.filter.ContentFilter.PI));
    String xsl = ((ProcessingInstruction)(pi.get(0)))
                 .getPseudoAttributeValue("href");
    XSLTransformer t = new XSLTransformer(xsl);
    Document h = t.transform(d);
    (new XMLOutputter()).output(h, out);
  }
}

class BufferingResponseWrapper extends HttpServletResponseWrapper {
  CharArrayWriter buffer;
  PrintWriter writer;

  public BufferingResponseWrapper(HttpServletResponse res) {
    super(res);
    buffer = new CharArrayWriter();
    writer = new PrintWriter(buffer);
  }

  public PrintWriter getWriter() {
    return writer;
  }

  Reader getReader() {
    return new CharArrayReader(buffer.toCharArray());
  }
}
