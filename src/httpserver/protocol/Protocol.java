package httpserver.protocol;

import java.io.BufferedReader;

import httpserver.packet.Packet;
import httpserver.packet.initialline.StatusLine;


public class Protocol {
	private String version = "HTTP/1.0";
	private int statusCode;
	private String reasonPhrase;
	
    public String processInput(BufferedReader input) {
    	Packet packet = new Packet(input);
    	
    	if (packet.isValid()) {
    		statusCode = 200;
    		reasonPhrase = "OK";
    	}
    	else {
    		statusCode = 400;
    		reasonPhrase = "Bad Request";
    	}
    	
    	StatusLine response = new StatusLine(version, statusCode, reasonPhrase);
    	
    	return response.getText();
    }
}
