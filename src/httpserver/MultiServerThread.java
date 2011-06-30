package httpserver;
import httpserver.protocol.Protocol;

import java.net.*;
import java.io.*;

public class MultiServerThread extends Thread {
	private Socket socket = null;

	public MultiServerThread(Socket socket) {
		super("MultiServerThread");
		this.socket = socket;
	}

	public void run() {

		try {
			PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader input = new BufferedReader(
					new InputStreamReader(
							socket.getInputStream()));

			Protocol protocol = new Protocol();
			String response = protocol.processInput(input);
			
			output.println(response);
			System.err.println(response);
			
			output.close();
			input.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
