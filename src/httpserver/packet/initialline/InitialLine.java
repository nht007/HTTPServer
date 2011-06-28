package httpserver.packet.initialline;

public class InitialLine {
	protected String text;
	
	public InitialLine(String origText) {
		text = origText;
	}
	
	public String getText() {
		return text;
	}
}
