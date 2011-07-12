package httpserver.protocol;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import httpserver.packet.Packet;


public class Protocol { 
	
    public String processInput(BufferedReader input) {
    	Packet packet = new Packet(input);
    	
    	Packet response = processPacket(packet); 
    	
    	return response.getText();
    }

	private Packet processPacket(Packet packet) {
		int statusCode;
		String version = "HTTP/1.0";
		String body = "";
		String reasonPhrase;
		
		if (packet.isValid()) {
			statusCode = 200;
			reasonPhrase = "OK";
			
			try {
				body = retrieveFile(packet);
			} catch (FileNotFoundException e) {
	    		statusCode = 404;
	    		reasonPhrase = "Not Found";
			}
    	}
    	else {
    		statusCode = 400;
    		reasonPhrase = "Bad Request";
    	}
		
		return new Packet(version, statusCode, reasonPhrase, null, body);
	}

	private String retrieveFile(Packet packet) throws FileNotFoundException {
		String path = packet.getPath();
		if (path.equals("/")) return "";
		
		BufferedReader file = new BufferedReader(
				new FileReader("public" + path));
		
		String body = "";
		try {
			String readLine;
			while ((readLine = file.readLine()) != null) {
				body += readLine + "\n";
			}
			
			file.close();
		} catch (IOException e) {
			
		}
		
		return body;
	}
}
