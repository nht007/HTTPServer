package httpserver.packet;

import httpserver.packet.body.Body;
import httpserver.packet.header.Header;

import java.io.BufferedReader;
import java.io.IOException;

public class Packet {
	private Header header;
	private Body body;
	private boolean valid = true;

	// construct from stream
	public Packet(BufferedReader input) {
		this.header = parseHeader(input);
		
		if (this.header == null) {
			this.header = null;
			this.body = null;
			this.valid = false;
			return;
		}
		
		int contentLength = this.header.getContentLength();
		
		if (contentLength > 0) {
			this.body = parseRequestBody(input, contentLength);
		}
	}
	
	// construct manually
	public Packet(String version, int code, String reasonPhrase, String[] lines, String bodyText) {
		this.header = new Header(version, code, reasonPhrase, lines);
		this.body = new Body(bodyText);
		this.valid = true;
	}

	private Header parseHeader(BufferedReader input) {
		String headerString = "";
		try {
			String readLine;
			while ((readLine = input.readLine()) != null) {
				headerString += readLine + "\n";
				if (readLine != null && readLine.equals("")) {
					return new Header(headerString);
				}
			}
		} catch (IOException e) {

		}
		
		return null;

	}
	
	private Body parseRequestBody(BufferedReader input, int contentLength) {
		char[] bodyString = new char[contentLength];
		
		try {
			input.read(bodyString, 0, contentLength);
			return new Body(new String(bodyString));
		} catch (IOException e) {
			
		}
		
		return null;
	}

	public boolean isValid() {
		if (this.header == null) {
			return false;
		}
		else if (!this.header.isValid()) {
			return false;
		}
		return this.valid;
	}
	
	public Header getHeader() {
		return this.header;
	}
	
	public Body getBody() {
		return this.body;
	}
	
	public String getPath() {
		return this.header.getPath();
	}
	
	public String getText() {
		if (this.body != null) {
			return this.header.getText() + this.body.getText();	
		}
		else {
			return this.header.getText();
		}
	}
	
}
