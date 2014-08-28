<%@ page isErrorPage="true" %>
<html>
  <head><title>Error</title></head>
  <body>
    Something bad happened:
    <%= exception.getMessage() %>
  </body>
</html>
