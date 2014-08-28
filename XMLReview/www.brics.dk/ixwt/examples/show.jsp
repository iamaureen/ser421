<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<html>
 <head><title>Nutrition Overview</title></head>
 <body>
  <table border="1">
   <tr>
    <td>Title</td>
    <td>Calories</td>
    <td>Fat</td>
    <td>Carbohydrates</td>
    <td>Protein</td>
    <td>Alcohol</td>
   </tr>
   <x:forEach select="$recipes//*[local-name()='recipe']">
    <c:forEach var="id" items="${paramValues.selected}">
     <x:if select="self::node()[@id=$id]">
      <tr>
       <td>
        <x:out select=".//*[local-name()='title']"/>
       </td>
       <td align="right">
        <x:out select=".//*[local-name()='nutrition']/@calories"/>
       </td>
       <td align="right">
        <x:out select=".//*[local-name()='nutrition']/@fat"/>
       </td>
       <td align="right">
        <x:out select=".//*[local-name()='nutrition']/@carbohydrates"/>
       </td>
       <td align="right">
        <x:out select=".//*[local-name()='nutrition']/@protein"/>
       </td>
       <td align="right">
        <x:out select=".//*[local-name()='nutrition']/@alcohol"/>
        <x:if select="not(.//*[local-name()='nutrition']/@alcohol)">
         0%
        </x:if>
       </td>
      </tr>
     </x:if>
    </c:forEach>
   </x:forEach>
  </table>
 </body>
</html>
