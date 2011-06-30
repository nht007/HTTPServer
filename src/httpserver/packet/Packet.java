package httpserver.packet;

import java.io.BufferedReader;
import java.io.IOException;

public class Packet {
	private Header header;
	private Body body;
	private boolean valid = true;

	public Packet(BufferedReader input) {
		header = parseHeader(input);
//		body = parseBody(input);
		body = new Body("");
		
		if (header == null || body == null) {
			header = null;
			body = null;
			valid = false;
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
	
	private Body parseBody(BufferedReader input) {
		String bodyString = "";
		try {
			String readLine;
			while ((readLine = input.readLine()) != null) {
				bodyString += readLine + "\n";
				
				if (readLine != null && readLine.equals("")) {
					return new Body(bodyString);
				}
			}
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
		if (header == null || body == null) {
			return false;
		}
		else if (!header.isValid() || !body.isValid()) {
			return false;
		}
		return valid;
	}
}
