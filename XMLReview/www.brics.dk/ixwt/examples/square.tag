<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ variable name-given="n2" %>
<%@ attribute name="n" required="true" %>
<c:set var="n2" value="${n*n}"/>
<jsp:doBody/>
