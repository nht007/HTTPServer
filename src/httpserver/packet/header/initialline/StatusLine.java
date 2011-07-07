package httpserver.packet.header.initialline;

public class StatusLine extends InitialLine {
	private String version;
	private int statusCode;
	private String reasonPhrase;
	
	public StatusLine(String version, int code, String reasonPhrase) {
		super(version + " " + code + " " + reasonPhrase);
		this.version = version;
		this.statusCode = code;
		this.reasonPhrase = reasonPhrase;
		
		this.valid = true;
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
