import java.net.*;
import java.io.*;

public class SimpleGrabHttpURL {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("USAGE: java SimpleGrabHttpURL <url>");
			System.exit(0);
		}

		HttpURLConnection conn = null;
		BufferedReader instream = null;
		try {
			conn = (HttpURLConnection) new URL(args[0]).openConnection();

			instream = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = instream.readLine()) != null) {
				System.out.println(line);
			}
		}
		catch (IOException exc) {
			exc.printStackTrace();
		}
		finally {
			try {
				if (instream != null) instream.close();
			}
			catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}
}
