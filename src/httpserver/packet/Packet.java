package httpserver.packet;

import java.io.BufferedReader;
import java.io.IOException;

public class Packet {
	private Header header;
	private Body body;
	private boolean valid = true;

	public Packet(BufferedReader input) {
		header = parseHeader(input);
		
		if (header == null) {
			header = null;
			body = null;
			valid = false;
			return;
		}
		
		int contentLength = header.getContentLength();
		
		if (contentLength > 0) {
			body = parseBody(input, contentLength);
		}

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
	
	private Body parseBody(BufferedReader input, int contentLength) {
		char[] bodyString = new char[contentLength];
		
		try {
			input.read(bodyString, 0, contentLength);
			return new Body(new String(bodyString));
		} catch (IOException e) {
			
		}
		
		return null;
	}
	
	public Header getHeader() {
		return header;
	}
	
	public Body getBody() {
		return body;
	}
	
	public boolean isValid() {
		if (header == null) {
			return false;
		}
		else if (!header.isValid()) {
			return false;
		}
		return valid;
	}
}
