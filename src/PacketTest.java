import static org.junit.Assert.*;

import org.junit.Test;



public class PacketTest {
	
	@Test
	public void splitRequest() {
		String request = 
				"GET / HTTP/1.0\r\n" +
				"User-Agent: HTTPTool/1.0\n\r\n" +
				"<html>Hello World!\r\n</html>";
		Packet packet = new Packet(request);
		
		assertEquals( 
				"GET / HTTP/1.0\r\n" +
				"User-Agent: HTTPTool/1.0",
				packet.getHeader().getText());
		assertEquals( 
				"<html>Hello World!\r\n</html>",
				packet.getBody().getText());
	}
}
