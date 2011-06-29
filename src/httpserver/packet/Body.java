package httpserver.packet;

public class Body {
	private String text;
	private boolean valid = false;
	
	public Body(String origText) {
		text = origText;
		
		valid = true;
	}
	
	public String getText() {
		return text;
	}
	
	public boolean isValid() {
		return valid;
	}
}
