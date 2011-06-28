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
		assertEquals(response, "HTTP/1.0 200 OK");
	}
}
