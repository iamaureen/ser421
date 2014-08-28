<%@ tag import="java.util.*" %>
<%@ variable name-given="date" %>
<%@ variable name-given="month" %>
<%@ variable name-given="year" %>
<% Calendar cal = new GregorianCalendar();
   int date = cal.get(Calendar.DATE);
   int month = cal.get(Calendar.MONTH)+1;
   int year = cal.get(Calendar.YEAR);
   jspContext.setAttribute("date", String.valueOf(date));
   jspContext.setAttribute("month", String.valueOf(month));
   jspContext.setAttribute("year", String.valueOf(year));
%>
<jsp:doBody/>
