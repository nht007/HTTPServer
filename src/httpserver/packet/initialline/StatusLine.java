package httpserver.packet.initialline;

public class StatusLine extends InitialLine {
	private String version;
	private int statusCode;
	private String reasonPhrase;
	
	public StatusLine(String newVersion, int newCode, String newReasonPhrase) {
		super(newVersion + " " + newCode + " " + newReasonPhrase);
		version = newVersion;
		statusCode = newCode;
		reasonPhrase = newReasonPhrase;
		
		valid = true;
	}

	public String getVersion() {
		return version;
	}
	
	public int getCode() {
		return statusCode;
	}
	
	public String getReasonPhrase() {
		return reasonPhrase;
	}
}
