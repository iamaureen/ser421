import java.util.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.io.*;

public class NonblockingServer {
  public static void main(String[] args) {
    try {
      // set up server sockets for the given ports
      ServerSocketChannel[] ss = new ServerSocketChannel[args.length];
      Selector sel = Selector.open();
      for (int i=0; i<args.length; i++) {
          int port = Integer.parseInt(args[i]);
          ss[i] = ServerSocketChannel.open();
          ss[i].configureBlocking(false);
          ss[i].socket().bind(new InetSocketAddress(port));
          ss[i].register(sel, SelectionKey.OP_ACCEPT);
      }

      ByteBuffer buf = ByteBuffer.allocateDirect(1024);
      while (true) {      
        // wait for a client to connect
        sel.select();
          
        // process each connection
        Iterator j = sel.selectedKeys().iterator();
        while (j.hasNext()) {
          SelectionKey k = (SelectionKey)j.next();
          j.remove();
          if (k.isAcceptable()) {

            // establish connection
            ServerSocketChannel s = (ServerSocketChannel)k.channel();
            SocketChannel c = s.accept();
            c.configureBlocking(false);

            try {
              // read input
              Selector readsel = Selector.open();
              c.register(readsel, SelectionKey.OP_READ);
              StringBuffer msg = new StringBuffer();
              boolean eos = false;
              while (!eos) {
                buf.clear();
                readsel.select(); // wait for a chunk of input
                c.read(buf);    
          
                int p = buf.position();
                if (p>0 && buf.get(p-1)==0) { 
                  eos = true; // we have reached the terminating 0
                  buf.position(p-1);
                }
                    
                buf.flip();
                byte[] bb = new byte[buf.remaining()];
                buf.get(bb);
                msg.append(new String(bb));
              }

              // write output
              Selector writesel = Selector.open();
              c.register(writesel, SelectionKey.OP_WRITE);
              buf.clear();
              buf.put((new String("Rumors at port "+
                                  c.socket().getLocalPort()+": "+msg))
                      .getBytes());
              buf.flip();
              while (buf.remaining()>0) {
                writesel.select(); // wait for client to be ready
                c.write(buf);
              }
              c.socket().shutdownOutput();
              
            } catch (Exception e) { // presumably lost connection
              continue;
            }
            c.close();
          }
        }
      }
    } catch (IOException e) {
      System.err.println(e); 
    }
  }
}
