import java.io.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
import javax.servlet.*;

public class Jokes {
  static Namespace jml =
    Namespace.getNamespace("http://www.brics.dk/ixwt/jokes");

  ServletContext context;
  String jokeFile;
  Document jokes;

  public Jokes(ServletContext context)
      throws JDOMException, IOException {
    this.context = context;
    jokes = (Document)context.getAttribute("jokes");
    jokeFile = context.getInitParameter("JokeFile");
    if (jokes==null) {
      try {
        jokes = new SAXBuilder().build(new File(jokeFile));
      } catch (Exception e) {
        jokes = new Document(new Element("collection", jml));
      }
      context.setAttribute("jokes", jokes);
    }
  }

  public Document getJokes() {
    return jokes;
  }

  public void addJokes(Document more)
      throws JDOMException, IOException {
    synchronized(context) {
      jokes.getRootElement()
           .addContent(more.getRootElement().removeContent());
      new XMLOutputter()
          .output(jokes, new FileOutputStream(new File(jokeFile)));
    }
  }
}

