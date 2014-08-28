<% response.addDateHeader("Expires", 0); %>
<html><head><title>Goodbye</title></head>
<body><h1>Goodbye <%= application.getAttribute("name") %>!</h1>
<% if (request.getParameter("feel").equals("fine")) { %>
  I'm glad that you are fine.
<% } else { %>
  I hope you feel better soon.
<% } %>
</body></html>
