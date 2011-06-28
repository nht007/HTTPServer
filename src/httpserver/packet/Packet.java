package httpserver.packet;

public class Packet {
	private PacketHeader header;
	private PacketBody body;
	private boolean valid = false;

	public Packet(String request) {
		String[] splitRequest = request.split("[\r|\n]{3,}", 2); 
		try {
			header = new PacketHeader(splitRequest[0]);
			body = new PacketBody(splitRequest[1]);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			return;
		}
		
		if (splitRequest.length != 2) {
			return;
		}
		
		valid = true;
	}
	
	public PacketHeader getHeader() {
		return header;
	}
	
	public PacketBody getBody() {
		return body;
	}
	
	public boolean isValid() {
		if (header == null || body == null) {
			return false;
		}
		else if (!header.isValid() || !body.isValid()) {
			return false;
		}
		return true;
	}
}
