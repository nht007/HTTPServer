package httpserver.protocol;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import org.junit.*;

public class ProtocolTest {
	private static Protocol protocol;

	@BeforeClass
	public static void constructProtocol() {
		protocol = new Protocol();
	}

	@Test
	public void handlesSimpleGetRequest() {
		String request = 
			"GET / HTTP/1.0\n\r\n";

		BufferedReader input = new BufferedReader(
				new InputStreamReader(
						new ByteArrayInputStream(request.getBytes())));
		
		String response = protocol.processInput(input);
		assertEquals("HTTP/1.0 200 OK", response);
	}
	
	@Test
	public void handlesGetRequest() {
		String request = 
			"GET / HTTP/1.0\r\n" +
			"User-Agent: HTTPTool/1.0\n\r\n";

		BufferedReader input = new BufferedReader(
				new InputStreamReader(
						new ByteArrayInputStream(request.getBytes())));
		
		String response = protocol.processInput(input);
		assertEquals("HTTP/1.0 200 OK", response);
	}

	@Test
	public void handlesMissingBlankLine() {
		String request = 
			"POST / HTTP/1.0\n" +
			"User-Agent: HTTPTool/1.0\n" +
			"Content-Length: 26\n" +
			"<html>Hello World!\n</html>";
		
		BufferedReader input = new BufferedReader(
				new InputStreamReader(
						new ByteArrayInputStream(request.getBytes())));
		
		String response = protocol.processInput(input);
		assertEquals("HTTP/1.0 400 Bad Request", response);
	}

	@Test
	public void handlesMalformedInitialLine() {
		String request = 
			"POST/HTTP/1.0\n" +
			"User-Agent: HTTPTool/1.0\n" +
			"Content-Length: 26\n\r\n" +
			"<html>Hello World!\n</html>";
		
		BufferedReader input = new BufferedReader(
				new InputStreamReader(
						new ByteArrayInputStream(request.getBytes())));
		
		String response = protocol.processInput(input);
		assertEquals("HTTP/1.0 400 Bad Request", response);
	}
}
