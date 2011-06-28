package httpserver.packet.initialline;

public class InitialResponseLine extends InitialLine {
	private String version;
	private int statusCode;
	private String reasonPhrase;
	
	public InitialResponseLine(String newVersion, int newCode, String newReasonPhrase) {
		super(newVersion + " " + newCode + " " + newReasonPhrase);
		version = newVersion;
		statusCode = newCode;
		reasonPhrase = newReasonPhrase;
	}
}
