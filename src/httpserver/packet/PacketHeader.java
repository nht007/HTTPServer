package httpserver.packet;
import httpserver.packet.initialline.InitialLine;


public class PacketHeader {
	private String text;
	private String[] lines;
	private InitialLine initialLine;
	
	public PacketHeader(String origText) {
		text = origText;
		lines = text.split("[\r|\n]+");
		initialLine = new InitialLine(lines[0]);
	}
	
	public String getText() {
		return text;
	}
	
	public String[] getLines() {
		return lines;
	}
	
	public InitialLine getInitialLine() {
		return initialLine;
	}
}
