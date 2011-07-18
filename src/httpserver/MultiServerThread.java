package httpserver;
import httpserver.protocol.Protocol;
import httpserver.protocol.filehandler.FileHandler;

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

			FileHandler fileHandler = new FileHandler();
			Protocol protocol = new Protocol(fileHandler);
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
