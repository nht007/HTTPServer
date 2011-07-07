package httpserver.packet.header;
import static org.junit.Assert.*;

import org.junit.*;



public class HeaderTest {
	static Header header;
	
	@BeforeClass
	public static void constructPacketHeader() {
		header = new Header(
				"GET / HTTP/1.0\r\n" +
				"User-Agent: HTTPTool/1.0\n" +
				"Content-Length: 3248");
	}
	
	@Test
	public void parsesHeaderLines() {
		String[] lines = header.getLines();
		
		assertEquals("GET / HTTP/1.0", lines[0]);
		assertEquals("User-Agent: HTTPTool/1.0", lines[1]);
		assertEquals("Content-Length: 3248", lines[2]);
	}
	
	@Test
	public void parsesInitialRequestLine() {
		assertEquals("GET / HTTP/1.0", header.getInitialLine().getText());
	}
	
	@Test
	public void getsMessageLength() {
		assertEquals(3248, header.getContentLength());
	}
}
