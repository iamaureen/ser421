<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<c:import url="cardlist.xml" var="xml"/>
<x:parse xml="${xml}" var="cardlist" scope="session"/>
<html>
  <head>
    <title>Select A Card</title>
  </head>
  <body>
    <ul>
      <c:set var="pattern" value="${param.pattern}"/>
      <x:forEach varStatus="i" 
                 select="$cardlist//*[local-name()='card']">
        <x:if select="*[contains(.,$pattern)]">
          <li>
          <a href="display_card.jsp?i=${i.index}">
            <x:out select="*[local-name()='name']/text()"/>
          </a>
        </x:if>
      </x:forEach>
    </ul>
  </body>
</html>
