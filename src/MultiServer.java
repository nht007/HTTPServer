import java.net.*;
import java.io.*;

public class MultiServer {
    private static final int PORT = 3333;

	public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
        	System.err.println(e.getMessage());
            System.err.println("Could not listen on port: " + PORT);
            System.exit(-1);
        }

        while (listening)
	    new MultiServerThread(serverSocket.accept()).start();

        serverSocket.close();
    }
}
