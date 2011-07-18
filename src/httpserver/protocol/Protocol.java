package httpserver.protocol;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import httpserver.packet.Packet;
import httpserver.protocol.filehandler.FileHandler;


public class Protocol {
	private FileHandler fileHandler;
	
    public Protocol(FileHandler fileHandler) {
    	this.fileHandler = fileHandler;
	}

	public String processInput(BufferedReader input) {
    	Packet packet = new Packet(input);
    	
    	Packet response = processPacket(packet);
    	
    	if (packet.isValid()) {
    		System.err.println(packet.getText());
    	}
    	
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
				body = fileHandler.retrieveFile(packet.getPath());
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
}
