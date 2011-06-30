package httpserver;

import httpserver.protocol.Protocol;

import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(3333);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 3333.");
			System.exit(1);
		}

		Socket clientSocket = null;
		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}

		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(
				new InputStreamReader(
						clientSocket.getInputStream()));
		
		Protocol protocol = new Protocol();

		String response = protocol.processInput(in);
		out.println(response);
		System.err.print(response);
		
		out.close();
		in.close();
		clientSocket.close();
		serverSocket.close();
	}
}

