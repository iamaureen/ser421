<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<c:import url="http://www.brics.dk/ixwt/examples/recipes.xml" 
          var="xml"/>
<x:parse xml="${xml}" var="recipes" scope="session"/>
<html>
  <head><title>Select Some Recipes</title></head>
  <body>
    <form method="post" action="show.jsp">
      <x:forEach select="$recipes//*[local-name()='recipe']">
        <c:set var="id">
          <x:out select="@id"/>
        </c:set>
        <input type="checkbox" name="selected" value="${id}"/>
        <x:out select="*[local-name()='title']/text()"/>
        <br/>
      </x:forEach>
      <input type="submit" value="Select"/>
    </form>
  </body>
</html>
