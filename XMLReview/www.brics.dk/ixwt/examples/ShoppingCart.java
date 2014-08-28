import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ShoppingCart extends HttpServlet {
  public void doGet(HttpServletRequest request, 
                    HttpServletResponse response)
      throws IOException, ServletException {
    doPost(request, response);
  }

  public void doPost(HttpServletRequest request, 
                     HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>Widget Inc.</title></head><body>"+
                "<h1>Widget Inc. Online Shopping</h1>");

    HttpSession session = request.getSession();
    Map cart;
    if (session.isNew()) {
      cart = new TreeMap();
      session.setAttribute("cart", cart);
    } else
      cart = (Map)session.getAttribute("cart");

    if (request.getMethod().equals("POST")) {
      String item = request.getParameter("item");
      String amount = request.getParameter("amount");
      if (item!=null)
        try {
          addToCart(cart, item, Integer.parseInt(amount));
        } catch (Exception e) {
          response.sendError(response.SC_BAD_REQUEST, 
                             "malformed request");
        }
    }

    String url = request.getRequestURI();
    out.println("<form method=post action=\""+response.encodeURL(url)+"\">"+
                "Item: <input type=text name=item size=20>"+
                "Amount: <input type=text name=amount size=5>"+
                "<input type=submit name=submit value=\"Add to shopping cart\">"+
                "</form><p>");
      
    if (cart.isEmpty())
      out.println("Your shopping cart is empty.");
    else {
      out.println("Your shopping cart now contains:<p>"+
                  "<table border=1><tr><th>Item<th>Amount");
      Iterator i = cart.entrySet().iterator();
      while (i.hasNext()) {
        Map.Entry me = (Map.Entry)i.next();
        out.println("<tr><td>"+me.getKey()+
                    "<td align=right>"+me.getValue());
      }
      out.println("</table><p>");
      
      out.println("<form method=post action=\""+response.encodeURL("buy")+"\">"+
                  "<input type=submit name=submit value=\"Proceed to cashier\">"+
                  "</form>");
    }
           
    out.println("</body></html>");
  }

  void addToCart(Map cart, String item, int amount) {
    if (amount<0)
      return;
    Integer a = (Integer)cart.get(item);
    if (a==null)
      a = new Integer(0);
    cart.put(item, new Integer(a.intValue()+amount));
  }
}
