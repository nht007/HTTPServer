package httpserver.packet.header.initialline;

public abstract class InitialLine {
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
	
	public String getMethod() {
		return null;
	}
	
	public String getPath() {
		return null;
	}

	public String getVersion() {
		return null;
	}
}
