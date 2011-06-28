
public class Packet {
	private PacketHeader header;
	private PacketBody body;

	public Packet(String request) {
		String[] split_request = request.split("[\r|\n]{3,}", 2);
		
		header = new PacketHeader(split_request[0]);
		body = new PacketBody(split_request[1]);
		
		return;
	}
	
	public PacketHeader getHeader() {
		return header;
	}
	
	public PacketBody getBody() {
		return body;
	}
}
