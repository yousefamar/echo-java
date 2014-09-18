import java.io.*;
import java.net.*;

public class Server {
	public static void main(String[] args) {
		try {
			Socket clientSoc = new ServerSocket(8000).accept();
			final ObjectOutputStream out = new ObjectOutputStream(clientSoc.getOutputStream());
			out.flush();
			final ObjectInputStream in = new ObjectInputStream(clientSoc.getInputStream());
			Object obj;
			while ((obj = in.readObject()) != null) {
				out.writeObject(obj);
				out.flush();
			}
			in.close();
			out.close();
			clientSoc.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}