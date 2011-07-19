package httpserver.packet.header.initialline;

public class InitialLineFactory {
	public static RequestLine createInitialLine(String line) {
		return new RequestLine(line);
	}
	
	public static StatusLine createInitialLine(String version, int code, String reasonPhrase) {
		return new StatusLine(version, code, reasonPhrase);
	}
}
