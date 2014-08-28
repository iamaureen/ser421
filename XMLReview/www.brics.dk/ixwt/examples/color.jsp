<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns="http://http://www.w3.org/1999/xhtml">
  <jsp:directive.page contentType="text/html"/>
  <jsp:scriptlet>
    response.addDateHeader("Expires", 0);
  </jsp:scriptlet>
  <html>
    <head><title>JSP</title></head>
    <jsp:element name="body">
      <jsp:attribute name="bgcolor">
        <jsp:expression>
          request.getParameter("color")
        </jsp:expression>
      </jsp:attribute>
      <h1>Hello World!</h1>
      <jsp:declaration>
        int hits = 0;
      </jsp:declaration>
      You are visitor number
      <jsp:scriptlet>
        synchronized(this) { out.println(++hits); }
      </jsp:scriptlet>
      since the last time the service was restarted.
      <p/>
      This page was last updated:
      <jsp:expression>
        new java.util.Date().toLocaleString()
      </jsp:expression>
    </jsp:element>
  </html>
</jsp:root>
