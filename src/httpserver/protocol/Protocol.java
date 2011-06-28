package httpserver.protocol;

import httpserver.packet.Packet;
import httpserver.packet.initialline.InitialResponseLine;


public class Protocol {
	private String version = "HTTP/1.0";
	private int statusCode;
	private String reasonPhrase;
	
    public String processInput(String request) {
    	Packet packet = new Packet(request);
    	
    	if (packet.isValid()) {
    		statusCode = 200;
    		reasonPhrase = "OK";
    	}
    	else {
    		statusCode = 400;
    		reasonPhrase = "Bad Request";
    	}
    	
    	InitialResponseLine response = new InitialResponseLine(version, statusCode, reasonPhrase);
    	
    	return response.getText();
    }
}
