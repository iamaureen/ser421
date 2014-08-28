import java.awt.*;
import java.awt.event.*;
import java.io.*; 
import java.util.*;
import org.jdom.*; 
import org.jdom.input.*; 
import org.jdom.output.*; 

class Card {
  public String name, title, email, phone, logo;

  public Card(String name, String title, String email, 
              String phone, String logo) {
    this.name = name;
    this.title = title;
    this.email = email;
    this.phone = phone;
    this.logo = logo;
  }
}

public class BCedit extends Frame implements ActionListener {
  // declare the user interface
  Button ok = new Button("ok");
  Button delete = new Button("delete");
  Button clear = new Button("clear");
  Button save = new Button("save");
  Button quit = new Button("quit");
  TextField name = new TextField(20);
  TextField title = new TextField(20);
  TextField email = new TextField(20);
  TextField phone = new TextField(20);
  TextField logo = new TextField(20);
  Panel cardpanel = new Panel(new GridLayout(0, 1));

  String cardfile;
  Vector cardvector;
  int current = -1;
  Namespace b = Namespace.getNamespace("http://businesscard.org");

  public static void main(String[] args) { new BCedit(args[0]); }

  Vector doc2vector(Document d) {
    Vector v = new Vector();
    Iterator i = d.getRootElement().getChildren("card", b).iterator();
    while (i.hasNext()) {
      Element e = (Element)i.next();
      String phone = e.getChildText("phone", b);
      if (phone==null) 
        phone="";
      Element logo = e.getChild("logo", b);
      String uri;
      if (logo==null) 
        uri = ""; 
      else 
        uri = logo.getAttributeValue("uri");
      Card c = new Card(e.getChildText("name", b),
                        e.getChildText("title", b),
                        e.getChildText("email", b),
                        phone,
                        uri);
      v.add(c);
    }
    return v;
  }

  Document vector2doc() {
    Element cardlist = new Element("cardlist", b);
    for (int i=0; i<cardvector.size(); i++) {
      Card c = (Card)cardvector.elementAt(i);
      if (c!=null) {
        Element card = new Element("card", b);
        Element name = new Element("name", b);
        name.addContent(c.name);
        card.addContent(name);
        Element title = new Element("title", b);
        title.addContent(c.title);
        card.addContent(title);
        Element email = new Element("email", b);
        email.addContent(c.email);
        card.addContent(email);
        if (!c.phone.equals("")) {
          Element phone = new Element("phone", b);
          phone.addContent(c.phone);
          card.addContent(phone);
        }
        if (!c.logo.equals("")) {
          Element logo = new Element("logo", b);
          logo.setAttribute("uri", c.logo);
          card.addContent(logo);
        }
        cardlist.addContent(card);
      }
    }
    return new Document(cardlist);
  }

  void addCards() {
    cardpanel.removeAll();
    for (int i=0; i<cardvector.size(); i++) {
      Card c = (Card)cardvector.elementAt(i);
      if (c!=null) {
        Button b = new Button(c.name);
        b.setActionCommand(String.valueOf(i));
        b.addActionListener(this);
        cardpanel.add(b);
      }
    }
    pack();
  }

  public BCedit(String cardfile) {
    super("BCedit");
    this.cardfile = cardfile;
    try {
      SAXBuilder b = new SAXBuilder();
      b.setValidation(true);
      b.setProperty(
        "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
        "http://www.w3.org/2001/XMLSchema");
      b.setProperty(
        "http://java.sun.com/xml/jaxp/properties/schemaSource",
        "business_card_list1.xsd");
      Document d = b.build(new File(cardfile));
      if (!d.getRootElement().getNamespaceURI().equals("http://businesscard.org"))
        throw new JDOMException("Wrong namespace of root element!");
      cardvector = doc2vector(d);
    } catch (Exception e) { 
      System.err.println(e); 
      System.exit(-1);
    }
    // initialize the user interface
    setLayout(new BorderLayout());
    ScrollPane s = new ScrollPane();
    s.setSize(200, 0);
    s.add(cardpanel);
    add(s,BorderLayout.WEST);
    Panel l = new Panel(new GridLayout(5, 1));
    l.add(new Label("Name"));                  
    l.add(new Label("Title"));                  
    l.add(new Label("Email"));                  
    l.add(new Label("Phone"));                  
    l.add(new Label("Logo"));                  
    add(l,BorderLayout.CENTER);
    Panel f = new Panel(new GridLayout(5, 1));
    f.add(name);    
    f.add(title);    
    f.add(email);    
    f.add(phone);    
    f.add(logo);    
    add(f,BorderLayout.EAST);
    Panel p = new Panel();
    ok.addActionListener(this);
    p.add(ok);
    delete.addActionListener(this);
    p.add(delete);
    clear.addActionListener(this);
    p.add(clear);
    save.addActionListener(this);
    p.add(save);
    quit.addActionListener(this);
    p.add(quit);
    add(p,BorderLayout.SOUTH);
    addCards();
    show();
  }

  public void actionPerformed(ActionEvent event) {
     Card c;
     String command = event.getActionCommand();
     if (command.equals("ok")) {
       c = new Card(name.getText(),
                    title.getText(),
                    email.getText(),
                    phone.getText(),
                    logo.getText());
       if (current==-1) 
         cardvector.add(c);
       else 
         cardvector.setElementAt(c, current);
       addCards();
     } else if (command.equals("delete")) {
        if (current!=-1) {
          cardvector.setElementAt(null, current);
          current = -1;
          addCards();
        }
     } else if (command.equals("clear")) {
        current = -1;
        name.setText("");
        title.setText("");
        email.setText("");
        phone.setText("");
        logo.setText("");
     } else if (command.equals("save")) {
        try {
          new XMLOutputter().output(vector2doc(),
                                    new FileOutputStream(cardfile));
        } catch (Exception e) { 
          System.err.println(e); 
        }
     } else if (command.equals("quit")) {
        System.exit(0);
     } else {
        current = Integer.parseInt(command);
        c = (Card)cardvector.elementAt(current);
        name.setText(c.name);
        title.setText(c.title);
        email.setText(c.email);
        phone.setText(c.phone);
        logo.setText(c.logo);
     }
  }
}
