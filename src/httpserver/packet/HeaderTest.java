package httpserver.packet;
import static org.junit.Assert.*;

import org.junit.*;



public class HeaderTest {
	static Header packetHeader;
	
	@BeforeClass
	public static void constructPacketHeader() {
		packetHeader = new Header(
				"GET / HTTP/1.0\r\n" +
				"User-Agent: HTTPTool/1.0");
	}
	
	@Test
	public void parsesHeaderLines() {
		String[] lines = packetHeader.getLines();
		
		assertEquals("GET / HTTP/1.0", lines[0]);
		assertEquals("User-Agent: HTTPTool/1.0", lines[1]);
	}
	
	@Test
	public void parsesInitialRequestLine() {
		assertEquals("GET / HTTP/1.0", packetHeader.getInitialLine().getText());
	}
}
