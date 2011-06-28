package httpserver.packet;
import static org.junit.Assert.*;

import org.junit.Test;



public class PacketTest {
	
	@Test
	public void parsesRequest() {
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
	
	@Test
	public void validatesValidPacket() {
		String validRequest = 
			"GET / HTTP/1.0\r\n" +
			"User-Agent: HTTPTool/1.0\n\r\n" +
			"<html>Hello World!\r\n</html>";
		Packet validPacket = new Packet(validRequest);
		
		assertTrue(validPacket.isValid());
	}

	@Test
	public void validatesInvalidPacket() {
		String invalidRequest = 
			"GET / HTTP/1.0\r\n" +
			"User-Agent: HTTPTool/1.0\n" +
			"<html>Hello World!\r\n</html>";
		Packet invalidPacket = new Packet(invalidRequest);
		
		assertFalse(invalidPacket.isValid());
	}
	
	@Test
	public void handlesBadRequest() {
		String request = 
			"GET / HTTP/1.0\r\n" +
			"User-Agent: HTTPTool/1.0\n" +
			"<html>Hello World!\r\n</html>";
		Packet packet = new Packet(request);
		
		assertEquals(null, packet.getHeader());
		assertEquals(null, packet.getBody());
	}
	
}
