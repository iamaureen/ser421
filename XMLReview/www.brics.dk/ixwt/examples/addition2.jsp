<%! int add(String x, String y) {
      return Integer.parseInt(x)+Integer.parseInt(y);
    } %>
<html>
  <head><title>Addition</title></head>
  <body>
    The sum of <%= request.getParameter("x") %>
    and <%= request.getParameter("y") %> is
    <%= add(request.getParameter("x"),request.getParameter("y")) %>
  </body>
</html>
