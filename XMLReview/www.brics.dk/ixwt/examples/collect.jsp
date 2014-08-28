<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Joke Client</title>
    <link href="style.css" rel="stylesheet" type="text/css"/>
  </head>
  <body>
    <form method="POST" action="Compose">
      <table border="0">
        <tr>
          <td>Please select the desired category:</td>
          <td>
            <select name="category">
              <c:forEach var="category" items="${categories}">
                <option>${category}</option>
              </c:forEach>
            </select>
          </td>
        </tr>
        <tr>
          <td>Or define a new category:</td>
          <td><input type="text" name="newcat" size="40"/>
        </tr>
        <tr>
          <td>Author:</td>
          <td><input type="text" name="author" size="40"/>
        </tr>
        <tr>
          <td>Title:</td>
          <td><input type="text" name="title" size="40"/>
        </tr>
        <tr>
          <td><input type="submit" value="Continue"/></td>
        </tr>
    </form>
  </body>
</html>
