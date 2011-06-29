package httpserver.packet.initialline;

public class InitialLine {
	protected String text;
	protected boolean valid = false;
	
	public InitialLine(String origText) {
		text = origText;
	}
	
	public String getText() {
		return text;
	}
	
	public boolean isValid() {
		return valid;
	}
}
