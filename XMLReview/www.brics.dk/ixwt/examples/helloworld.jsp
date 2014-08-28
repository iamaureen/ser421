<% response.addDateHeader("Expires", 0); %>
<html>
  <head><title>JSP</title></head>
  <body>
    <h1>Hello World!</h1>
    <%! int hits = 0; %>
    You are visitor number 
    <% synchronized(this) { out.println(++hits); } %>
    since the last time the service was restarted.
    <p>
    This page was last updated: 
    <%= new java.util.Date().toLocaleString() %>
  </body>
</html>
