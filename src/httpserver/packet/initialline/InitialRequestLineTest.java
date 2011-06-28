package httpserver.packet.initialline;
import static org.junit.Assert.*;

import org.junit.*;

public class InitialRequestLineTest {
	private static InitialRequestLine initialRequestLine;
	@BeforeClass
	public static void constructInitialRequestLine() {
		initialRequestLine = new InitialRequestLine("GET / HTTP/1.0");
	}
	
	@Test
	public void parsessMethod() {
		assertEquals("GET", initialRequestLine.getMethod());
	}
	
	@Test
	public void parsesPath() {
		assertEquals("/", initialRequestLine.getPath());
	}
	
	@Test
	public void parsesHTTPVersion() {
		assertEquals("HTTP/1.0", initialRequestLine.getVersion());
	}
}
