package httpserver.packet.initialline;

import static org.junit.Assert.*;

import org.junit.*;


public class InitialResponseLineTest {
	private static InitialResponseLine initialResponseLine;
	
	@BeforeClass
	public static void constructInitialResponseLine() {
		initialResponseLine = new InitialResponseLine("HTTP/1.0", 200, "OK");
	}
	
	@Test
	public void buildsText() {
		String text = initialResponseLine.getText();
		
		assertEquals(text, "HTTP/1.0 200 OK");
	}
}
