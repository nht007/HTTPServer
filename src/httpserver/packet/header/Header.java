package httpserver.packet.header;
import httpserver.packet.header.initialline.InitialLine;
import httpserver.packet.header.initialline.InitialLineFactory;


public class Header {
	protected String text;
	protected String[] lines;
	protected InitialLine initialLine;
	protected boolean valid = false;
	
	public Header(String text) {
		this.text = text;
		this.lines = text.split("[\r|\n]+");
		this.initialLine = InitialLineFactory.createInitialLine(lines[0]);
		
		this.valid = initialLine.isValid();
	}
	
	public Header(String version, int code, String reasonPhrase, String[] lines) {
		this.initialLine = InitialLineFactory.createInitialLine(version, code, reasonPhrase);
		this.lines = lines;
		this.text = constructResponseText();
			
		this.valid = this.initialLine.isValid();
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
	
	public String getPath() {
		return initialLine.getPath();
	}
	
	public InitialLine getInitialLine() {
		return initialLine;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	private String constructResponseText() {
		String text = this.initialLine.getText() + "\n";
		
		if (this.lines != null) {
			for (String line : this.lines) {
				text += line + "\n";
			}
		}
		
		text += "\r\n";
		
		return text;
	}
}
