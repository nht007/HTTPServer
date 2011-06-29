package httpserver.packet;
import httpserver.packet.initialline.InitialLine;
import httpserver.packet.initialline.InitialRequestLine;


public class Header {
	private String text;
	private String[] lines;
	private InitialLine initialLine;
	private boolean valid = false;
	
	public Header(String origText) {
		text = origText;
		lines = text.split("[\r|\n]+");
		initialLine = new InitialRequestLine(lines[0]); // TODO: extract to subclasses RequestHeader and ResponseHeader
		
		valid = initialLine.isValid();
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
	
	public boolean isValid() {
		return valid;
	}
}
