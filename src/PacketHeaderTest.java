import static org.junit.Assert.*;

import org.junit.*;



public class PacketHeaderTest {
	static PacketHeader packetHeader;
	
	@BeforeClass
	public static void constructPacketHeader() {
		packetHeader = new PacketHeader(
				"GET / HTTP/1.0\r\n" +
				"User-Agent: HTTPTool/1.0");
	}
	
	@Test
	public void containsText() {
		assertEquals(
				"GET / HTTP/1.0\r\n" +
				"User-Agent: HTTPTool/1.0",
				packetHeader.getText());
	}
	
	@Test
	public void splitHeaderLines() {
		String[] lines = packetHeader.getLines();
		
		assertEquals("GET / HTTP/1.0", lines[0]);
		assertEquals("User-Agent: HTTPTool/1.0", lines[1]);
	}
	
	@Test
	public void containsInitialRequestLine() {
		assertEquals("GET / HTTP/1.0", packetHeader.getInitialLine().getText());
	}
}
