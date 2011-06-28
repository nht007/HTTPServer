import static org.junit.Assert.*;

import org.junit.*;

public class InitialRequestLineTest {
	private static InitialRequestLine initialRequestLine;
	@BeforeClass
	public static void constructInitialRequestLine() {
		initialRequestLine = new InitialRequestLine("GET / HTTP/1.0");
	}
	
	@Test
	public void containsMethod() {
		assertEquals("GET", initialRequestLine.getMethod());
	}
	
	@Test
	public void containsPath() {
		assertEquals("/", initialRequestLine.getPath());
	}
	
	@Test
	public void containsHTTPVersion() {
		assertEquals("HTTP/1.0", initialRequestLine.getVersion());
	}
}
