<%@ page import="org.jdom.*" %>
<%@ page import="org.jdom.input.*" %>
<%@ page import="java.net.*" %>
<%@ page import="java.util.*" %>
<html>
  <head>
    <title>Joke Client</title>
    <link href="style.css" rel="stylesheet" type="text/css"/>
  </head>
  <body>
    <form method="GET" action="Compose">
      <input type="radio" name="part" value="setup" checked="checked"/>Setup
      <input type="radio" name="part" value="punchline"/>Punchline
      <p/>
      <textarea name="contents" cols="40" rows="20"></textarea>
      <p/>
      <input type="submit" value="Continue"/>
    </form>
  </body>
</html>

