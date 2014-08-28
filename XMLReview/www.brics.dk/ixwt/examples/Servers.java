import java.io.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
import javax.servlet.*;

public class Servers {
  String serverFile;
  static Namespace sml = 
    Namespace.getNamespace("http://www.brics.dk/ixwt/servers");
  ServletContext context;
  Document servers;

  public Servers(ServletContext context) 
      throws JDOMException, IOException {
    this.context = context;
    serverFile = context.getInitParameter("ServerFile");
    servers = (Document)context.getAttribute("servers");
    if (servers==null) {
      try {
        servers = new SAXBuilder().build(new File(serverFile));
      } catch (Exception e) {
        servers = new Document(new Element("servers", sml));
      }
      context.setAttribute("servers", servers);
    }
  }

  public Document getServers() {
    return servers;
  }

  public void addServer(String l, String r) 
      throws JDOMException, IOException {
    Element server = new Element("server", sml);
    Element list = new Element("list", sml);
    Element retrieve = new Element("retrieve", sml);
    list.addContent(l);
    retrieve.addContent(r);
    server.addContent(list).addContent(retrieve);
    synchronized(context) {
      servers.getRootElement().addContent(server);
      new XMLOutputter()
        .output(servers, 
                new FileOutputStream(new File(serverFile)));
    }
  }
}

