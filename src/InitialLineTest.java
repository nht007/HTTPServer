import static org.junit.Assert.*;

import org.junit.Test;

public class InitialLineTest {
	@Test
	public void containsText() {
		InitialLine initialLine = new InitialLine("GET / HTTP/1.0");
		
		assertEquals("GET / HTTP/1.0", initialLine.getText());
	}
}
