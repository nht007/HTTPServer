package httpserver.packet;

public class Packet {
	private Header header;
	private Body body;
	private boolean valid = false;

	public Packet(String request) {
		String[] splitRequest = request.split("[\r|\n]{3,}", 2); 
		try {
			header = new Header(splitRequest[0]);
			body = new Body(splitRequest[1]);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			header = null;
			body = null;
			return;
		}
		
		if (splitRequest.length != 2) {
			return;
		}
		
		valid = true;
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
