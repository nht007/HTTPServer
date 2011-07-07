package httpserver.packet.header.initialline;

import static org.junit.Assert.*;

import org.junit.*;


public class StatusLineTest {
	private static StatusLine statusLine;
	
	@BeforeClass
	public static void constructStatusLine() {
		statusLine = new StatusLine("HTTP/1.0", 200, "OK");
	}
	
	@Test
	public void buildsText() {
		String text = statusLine.getText();
		
		assertEquals(text, "HTTP/1.0 200 OK");
	}
}
