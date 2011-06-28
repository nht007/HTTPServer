package httpserver.packet.initialline;

import static org.junit.Assert.*;

import org.junit.*;


public class InitialResponseLineTest {
	private static InitialResponseLine initialResponseLine;
	
	@BeforeClass
	public static void constructInitialResponseLine() {
		initialResponseLine = new InitialResponseLine();
	}
	
	@Test
	public void printsResponseLine() {
		initialResponseLine.setVersion("HTTP/1.0");
		initialResponseLine.setStatusCode(200);
		initialResponseLine.setReasonPhrase("OK");
		String output = initialResponseLine.print();
		
		assertEquals(output, "HTTP/1.0 200 OK");
	}
}
