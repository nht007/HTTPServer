package httpserver.protocol;

import java.io.BufferedReader;
import httpserver.packet.Packet;


public class Protocol { 
	private String version = "HTTP/1.0";
	private int statusCode;
	private String reasonPhrase;
	
    public String processInput(BufferedReader input) {
    	Packet packet = new Packet(input);
    	
    	getStatusCode(packet);
    	
    	Packet response = new Packet(version, statusCode, reasonPhrase, null, "");
    	
    	return response.getText();
    }

	private void getStatusCode(Packet packet) {
		if (packet.isValid()) {
    		if (packet.getPath().equals("/foobar")) {
	    		statusCode = 404;
	    		reasonPhrase = "Not Found";
    		}
    		else {
    			statusCode = 200;
    			reasonPhrase = "OK";
    		}
    	}
    	else {
    		statusCode = 400;
    		reasonPhrase = "Bad Request";
    	}
	}
}
