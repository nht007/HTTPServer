package httpserver.packet.header.initialline;

public class RequestLine extends InitialLine {
	private String method;
	private String path;
	private String version;
	
	
	public RequestLine(String origText) {
		super(origText);
		String[] splitText = text.split("\\s+", 3);
		
		try {
			this.method = splitText[0];
			this.path = splitText[1];
			this.version = splitText[2];
		}
		catch (ArrayIndexOutOfBoundsException e) {
			return;
		}
		
		if (splitText.length != 3) {
			return;
		}
		
		this.valid = true;
	}
	
	public String getMethod() {
		return this.method;
	}
	
	public String getPath() {
		return this.path;
	}

	public String getVersion() {
		return this.version;
	}
}
