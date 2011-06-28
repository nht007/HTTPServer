package httpserver.packet.initialline;

public class InitialRequestLine extends InitialLine {
	private String method;
	private String path;
	private String version;
	private boolean valid = false;
	
	public InitialRequestLine(String origText) {
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
	
	public boolean isValid() {
		return valid;
	}
}
