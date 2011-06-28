import static org.junit.Assert.*;

import org.junit.Test;



public class PacketBodyTest {
	@Test
	public void containsText() {
		PacketBody packetBody = new PacketBody("body");
		
		assertEquals("body", packetBody.getText());
	}
}
