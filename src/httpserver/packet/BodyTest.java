package httpserver.packet;

import static org.junit.Assert.*;

import org.junit.Test;

public class BodyTest {
	@Test
	public void checksValidity() {
		Body body = new Body("blah");
		
		assertTrue(body.isValid());
	}
}
