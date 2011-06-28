package httpserver.packet;

public class PacketBody {
	private String text;
	
	public PacketBody(String origText) {
		text = origText;
	}
	
	public String getText() {
		return text;
	}
}
