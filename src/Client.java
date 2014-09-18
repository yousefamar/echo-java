import java.io.*;
import java.net.*;

public class Client {
	
	private static long sendTime;
	
	public static void main(String[] args) {
		try {
			final Socket clientSoc = new Socket("localhost", 8000);
			final ObjectOutputStream out = new ObjectOutputStream(clientSoc.getOutputStream());
			out.flush();
			final ObjectInputStream in = new ObjectInputStream(clientSoc.getInputStream());
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						while (in.readObject() != null)
							System.out.println(System.nanoTime() - sendTime);
						in.close();
						out.close();
						clientSoc.close();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}, "Server Listening Thread").start();

			sendTime = System.nanoTime();
			out.writeObject("Hi");
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
