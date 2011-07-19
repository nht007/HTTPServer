package httpserver.packet.header.initialline;

import static org.junit.Assert.*;

import org.junit.Test;

public class InitialLineFactoryTest {
	@Test
	public void createsRequestLine() {
		InitialLine initialLine = InitialLineFactory.createInitialLine("GET / HTTP/1.0");
		assertTrue(initialLine instanceof RequestLine);
	}
	
	@Test
	public void createsStatusLine() {
		InitialLine initialLine = InitialLineFactory.createInitialLine("HTTP/1.0", 200, "OK");
		assertTrue(initialLine instanceof StatusLine);
	}
}
