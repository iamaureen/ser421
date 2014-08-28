<html>
  <head><title>Addition</title></head>
  <body>
    The sum of <%= request.getParameter("x") %>
    and <%= request.getParameter("y") %> is
    <%= Integer.parseInt(request.getParameter("x")) +
        Integer.parseInt(request.getParameter("y")) %>
  </body>
</html>
