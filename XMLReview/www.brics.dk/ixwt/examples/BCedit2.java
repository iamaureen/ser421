import java.awt.*;
import java.awt.event.*;
import java.io.*; 
import java.util.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller;
import org.businesscard.*;

public class BCedit2 extends Frame implements ActionListener {
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
  Panel cardpanel = new Panel(new GridLayout(0,1));

  String cardfile;
  java.util.List cardlist;
  Cardlist cl;
  int current = -1;
  JAXBContext jc;
  ObjectFactory objFactory = new ObjectFactory();

  public static void main(String[] args) { new BCedit2(args[0]); }

  void addCards() {
    cardpanel.removeAll();
    Iterator i = cardlist.iterator();
    int j = 0;
    while (i.hasNext()) {
      Card c = (Card)i.next();
      Button b = new Button(c.getName());
      b.setActionCommand(String.valueOf(j++));
      b.addActionListener(this);
      cardpanel.add(b);
    }
    pack();
  }

  public BCedit2(String cardfile) {
    super("BCedit2");
    this.cardfile = cardfile;
    try {
      jc = JAXBContext.newInstance("org.businesscard");
      Unmarshaller u = jc.createUnmarshaller();  
      cl = (Cardlist)u.unmarshal(new FileInputStream(cardfile));
      cardlist = cl.getCard();
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
    add(l, BorderLayout.CENTER);
    Panel f = new Panel(new GridLayout(5, 1));
    f.add(name);    
    f.add(title);    
    f.add(email);    
    f.add(phone);    
    f.add(logo);    
    add(f, BorderLayout.EAST);
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
     Card c = null;
     String command = event.getActionCommand();
     if (command.equals("ok")) {
       try {
         c = objFactory.createCard();
         c.setName(name.getText());
         c.setTitle(title.getText());
         c.setEmail(email.getText());
         c.setPhone(phone.getText());
         Logo l = objFactory.createLogo();
         l.setUri(logo.getText());
         c.setLogo(l);
       } catch (Exception e) { 
         System.err.println(e); 
       }
       if (current==-1) 
          cardlist.add(c);
       else
          cardlist.set(current, c);
       addCards();
     } else if (command.equals("delete")) {
        if (current!=-1) {
          cardlist.remove(current);
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
          Marshaller m = jc.createMarshaller();
          m.marshal(cl,new FileOutputStream(cardfile));
        } catch (Exception e) { 
          System.err.println(e); 
        }
     } else if (command.equals("quit")) {
        System.exit(0);
     } else {
        current = Integer.parseInt(command);
        c = (Card)cardlist.get(current);
        name.setText(c.getName());
        title.setText(c.getTitle());
        email.setText(c.getEmail());
        phone.setText(c.getPhone());
        LogoType lt = c.getLogo();
        if (lt!=null) 
          logo.setText(lt.getUri());
        else 
          logo.setText("");
     }
  }
}
