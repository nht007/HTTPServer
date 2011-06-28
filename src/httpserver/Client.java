package httpserver;
import java.io.*;
import java.net.*;

public class Client {
	private static final String HOST = "localhost";
	private static final int PORT = 3333;

	public static void main(String[] args) throws IOException {

		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try {
			socket = new Socket(HOST, PORT);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + HOST);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: " + HOST);
			System.exit(1);
		}

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String request;
		String response;

		if ((request = stdIn.readLine()) != null) {
			System.out.println("Client: " + request);
			out.println(request);
		}
		
		if ((response = in.readLine()) != null) {
			System.out.println("Server: " + response);			
		}

		out.close();
		in.close();
		stdIn.close();
		socket.close();
	}
}
