<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
     <title>Select A Card</title>
  </head>
  <body>
    <ul>
      <c:forEach varStatus="i" items="${model.matches}" var="match">
        <li>
        <a href="display.do?i=${i.index}">
          ${match}
        </a>
      </c:forEach>
    </ul>
  </body>
</html>
