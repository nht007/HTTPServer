package httpserver.packet.initialline;

public class RequestLine extends InitialLine {
	private String method;
	private String path;
	private String version;
	
	
	public RequestLine(String origText) {
		super(origText);
		String[] splitText = text.split("\\s+", 3);
		
		try {
			method = splitText[0];
			path = splitText[1];
			version = splitText[2];
		}
		catch (ArrayIndexOutOfBoundsException e) {
			return;
		}
		
		if (splitText.length != 3) {
			return;
		}
		
		valid = true;
	}
	
	public String getMethod() {
		return method;
	}
	
	public String getPath() {
		return path;
	}

	public String getVersion() {
		return version;
	}
}
