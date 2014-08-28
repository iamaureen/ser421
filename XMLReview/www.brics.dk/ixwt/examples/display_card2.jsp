<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
     <title>The Requested Information</title>
  </head>
  <body>
    <table border="3">
      <tr>
        <td>
          ${model.name}<br/>
          ${model.title}<br/>
          <tt>${model.email}</tt><br/>
          <c:if test='${model.phone != ""}'>
            Phone: ${model.phone}<br/>
          </c:if>
        </td>
        <td>
          <c:if test='${model.uri != ""}'>
            <img src="${model.uri}"/>
          </c:if>
        </td>
      </tr>
    </table>
  </body>
</html>
