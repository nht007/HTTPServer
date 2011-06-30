package httpserver.packet;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import org.junit.Test;



public class PacketTest {
	
	@Test
	public void parsesSimpleGETRequest() {
		String request = 
				"GET / HTTP/1.0\n\r\n";
		
		BufferedReader input = new BufferedReader(
				new InputStreamReader(
						new ByteArrayInputStream(request.getBytes())));
		
		Packet packet = new Packet(input);
		
		assertEquals( 
				"GET / HTTP/1.0\n\n",
				packet.getHeader().getText());
	}
	
	@Test
	public void parsesRequest() {
		String request = 
				"GET / HTTP/1.0\r\n" +
				"User-Agent: HTTPTool/1.0\n\r\n" +
				"<html>Hello World!\r\n</html>";
		
		BufferedReader input = new BufferedReader(
				new InputStreamReader(
						new ByteArrayInputStream(request.getBytes())));
		
		Packet packet = new Packet(input);
		
		assertEquals( 
				"GET / HTTP/1.0\n" +
				"User-Agent: HTTPTool/1.0\n\n",
				packet.getHeader().getText());
		assertEquals( 
				"<html>Hello World!\n</html>\n",
				packet.getBody().getText());
	}
	
	@Test
	public void validatesValidPacket() {
		String validRequest = 
			"GET / HTTP/1.0\r\n" +
			"User-Agent: HTTPTool/1.0\n\r\n" +
			"<html>Hello World!\r\n</html>";

		BufferedReader input = new BufferedReader(
				new InputStreamReader(
						new ByteArrayInputStream(validRequest.getBytes())));
		
		Packet validPacket = new Packet(input);

		assertTrue(validPacket.isValid());
	}

	@Test
	public void validatesInvalidPacket() {
		String invalidRequest = 
			"GET / HTTP/1.0\r\n" +
			"User-Agent: HTTPTool/1.0\n" +
			"<html>Hello World!\r\n</html>";
		
		BufferedReader input = new BufferedReader(
				new InputStreamReader(
						new ByteArrayInputStream(invalidRequest.getBytes())));
		
		Packet invalidPacket = new Packet(input);

		assertFalse(invalidPacket.isValid());
	}

	@Test
	public void handlesBadRequest() {
		String request = 
			"GET / HTTP/1.0\r\n" +
			"User-Agent: HTTPTool/1.0\n" +
			"<html>Hello World!\r\n</html>";

		BufferedReader input = new BufferedReader(
				new InputStreamReader(
						new ByteArrayInputStream(request.getBytes())));
		
		Packet packet = new Packet(input);

		assertEquals(null, packet.getHeader());
		assertEquals(null, packet.getBody());
	}
	
}
