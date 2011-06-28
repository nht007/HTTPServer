
public class InitialRequestLine extends InitialLine {
	private String method;
	private String path;
	private String version;
	
	public InitialRequestLine(String origText) {
		super(origText);
		String[] splitText = text.split("\\s+", 3);
		method = splitText[0];
		path = splitText[1];
		version = splitText[2];
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
