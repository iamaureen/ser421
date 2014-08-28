<html>
  <head><title>Numbers</title></head>
  <body>
    <ul>
      <% int n = Integer.parseInt(request.getParameter("n"));
         for (int i=0; i<n; i++)
           out.println("<li>"+i+"</li>"); %>
    </ul>
  </body>
</html>
