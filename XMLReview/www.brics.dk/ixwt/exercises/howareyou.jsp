<% response.addDateHeader("Expires", 0); %>
<html><head><title>How are you?</title></head>
<body><h1>How are you, <%= request.getParameter("name") %>?</h1>
<% application.setAttribute("name",request.getParameter("name")); %>
<form method="POST" action="goodbye.jsp">
  Please tell me how you are: 
  <select name="feel">
    <option value="fine">fine
    <option value="lousy">lousy
  </select>
  <input type="submit" value="Continue">
</form>
</body></html>
