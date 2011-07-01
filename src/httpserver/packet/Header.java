package httpserver.packet;
import httpserver.packet.initialline.InitialLine;
import httpserver.packet.initialline.RequestLine;


public class Header {
	private String text;
	private String[] lines;
	private InitialLine initialLine;
	private boolean valid = false;
	
	public Header(String origText) {
		text = origText;
		lines = text.split("[\r|\n]+");
		initialLine = new RequestLine(lines[0]); // TODO: extract to subclasses RequestHeader and ResponseHeader
		
		valid = initialLine.isValid();
	}
	
	public int getContentLength() {
		String[][] splitLines = new String[lines.length][];
		for (int i = 0; i < lines.length; i++) {
			splitLines[i] = lines[i].split(":", 2);
		}
		for (String[] line : splitLines) {
			if (line[0].equals("Content-Length"))
				return Integer.parseInt(line[1].trim());
		}
		return -1;
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
