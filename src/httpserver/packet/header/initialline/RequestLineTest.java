package httpserver.packet.header.initialline;
import static org.junit.Assert.*;

import org.junit.*;

public class RequestLineTest {
	private static RequestLine requestLine;
	@BeforeClass
	public static void constructRequestLine() {
		requestLine = new RequestLine("GET / HTTP/1.0");
	}
	
	@Test
	public void parsesMethod() {
		assertEquals("GET", requestLine.getMethod());
	}
	
	@Test
	public void parsesPath() {
		assertEquals("/", requestLine.getPath());
	}
	
	@Test
	public void parsesHTTPVersion() {
		assertEquals("HTTP/1.0", requestLine.getVersion());
	}

	@Test
	public void validatesValidLine() {
		RequestLine validLine = new RequestLine("GET / HTTP/1.0");
		assertTrue(validLine.isValid());
	}
	
	@Test
	public void validatesInvalidLine() {
		RequestLine invalidLine = new RequestLine("GET/HTTP/1.0");
		assertFalse(invalidLine.isValid());
	}
	
	@Test
	public void validatesInvalidMethod() {
		RequestLine invalidLine = new RequestLine("BLAH / HTTP/1.0");
		assertFalse(invalidLine.isValid());
	}
}
