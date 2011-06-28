package httpserver.protocol;

import static org.junit.Assert.*;

import org.junit.*;

public class ProtocolTest {
	private static Protocol protocol;
	
	@BeforeClass
	public static void constructProtocol() {
		protocol = new Protocol();
	}
	
	@Test
	public void handlesGetRequest() {
		String request = 
			"GET / HTTP/1.0\r\n" +
			"User-Agent: HTTPTool/1.0\n\r\n" +
			"<html>Hello World!\r\n</html>";
		String response = protocol.processInput(request);
		assertEquals("HTTP/1.0 200 OK", response);
	}
	
	@Test
	public void handlesMissingBlankLine() {
		String request = 
			"GET / HTTP/1.0\r\n" +
			"User-Agent: HTTPTool/1.0\n" +
			"<html>Hello World!\r\n</html>";
		String response = protocol.processInput(request);
		assertEquals("HTTP/1.0 400 Bad Request", response);
	}
	
	@Test
	public void handlesMalformedInitialLine() {
		String request = 
			"GET/HTTP/1.0\r\n" +
			"User-Agent: HTTPTool/1.0\n\r\n" +
			"<html>Hello World!\r\n</html>";
		String response = protocol.processInput(request);
		assertEquals("HTTP/1.0 400 Bad Request", response);
	}
}
