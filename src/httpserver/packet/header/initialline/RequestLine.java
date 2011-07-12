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
			return; // invalid
		}
		
		if (splitText.length != 3 || !validMethod()) {
			return; // invalid
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
	
	private boolean validMethod() {
		String[] validMethods = {"GET", "POST"};
		for (String method : validMethods) {
			if (this.method.equals(method)) {
				return true;
			}
		}
		return false;
	}
}
