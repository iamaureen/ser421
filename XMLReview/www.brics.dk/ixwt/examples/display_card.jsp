<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<html>
  <head>
    <title>The Requested Business Card</title>
  </head>
  <body>
    <x:forEach varStatus="i" 
               select="$cardlist//*[local-name()='card']">
      <c:if test="${param.i==i.index}">
        <table border="3">
          <tr>
            <td>
              <x:out select="*[local-name()='name']"/><br/>
              <x:out select="*[local-name()='title']"/><br/>
              <tt><x:out select="*[local-name()='email']"/></tt><br/> 
              <x:if select="*[local-name()='phone']">
                Phone: <x:out select="*[local-name()='phone']"/><br/>
              </x:if>
            </td>
            <td>
              <x:if select="*[local-name()='logo']">
                <c:set var="uri">
                  <x:out select="*[local-name()='logo']/@uri"/>
                </c:set>
                <img src="${uri}"/>
              </x:if>
            </td>
          </tr>
        </table>
      </c:if>
    </x:forEach>
  </body>
</html>
