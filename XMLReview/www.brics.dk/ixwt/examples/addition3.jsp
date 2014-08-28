<%! String title = "Addition"; %>
<%@ include file="header.jsp" %>
  The sum of <%= request.getParameter("x") %>
  and <%= request.getParameter("y") %> is
  <%= Integer.parseInt(request.getParameter("x")) +
      Integer.parseInt(request.getParameter("y")) %>
<%@ include file="footer.jsp" %>
