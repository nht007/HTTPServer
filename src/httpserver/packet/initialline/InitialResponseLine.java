package httpserver.packet.initialline;

public class InitialResponseLine {
	private String version;
	private int statusCode;
	private String reasonPhrase;
	
	public void setVersion(String newVersion) {
		version = newVersion;
	}
	
	public void setStatusCode(int newCode) {
		statusCode = newCode;
	}
	
	public void setReasonPhrase(String newReasonPhrase) {
		reasonPhrase = newReasonPhrase;
	}
	
	public String print() {
		return version + " " + statusCode + " " + reasonPhrase;
	}
	
}
