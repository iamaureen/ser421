<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Joke Client</title>
    <link href="style.css" rel="stylesheet" type="text/css"/>
  </head>
  <body>
    <form method="GET" action="show">
      Please select the desired category:
      <select name="category">
        <c:forEach var="category" items="${categories}">
          <option>${category}</option>
        </c:forEach>
      </select>
      <br/>
      <input type="submit" value="Select"/>
    </form>
  </body>
</html>
