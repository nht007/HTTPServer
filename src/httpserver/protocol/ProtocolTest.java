package httpserver.protocol;

import static org.junit.Assert.*;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;

import httpserver.protocol.filehandler.FileHandler;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import org.junit.*;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class ProtocolTest {
	private static Protocol protocol;
	private static FileHandler fileHandler;
	private static Mockery context = new JUnit4Mockery();

	@BeforeClass
	public static void constructProtocol() throws FileNotFoundException {
		fileHandler = context.mock(FileHandler.class);
		context.checking(new Expectations() {{
			allowing(fileHandler).retrieveFile("/");
			will(returnValue(""));
		}});
		protocol = new Protocol(fileHandler);
	}

	@Test
	public void handlesSimpleGetRequest() {
		String request = 
			"GET / HTTP/1.0\n\r\n";

		BufferedReader input = new BufferedReader(
				new InputStreamReader(
						new ByteArrayInputStream(request.getBytes())));
		
		String response = protocol.processInput(input);
		assertEquals("HTTP/1.0 200 OK\n\r\n", response);
	}
	
	@Test
	public void handlesGetRequest() {
		String request = 
			"GET / HTTP/1.0\r\n" +
			"User-Agent: HTTPTool/1.0\n\r\n";

		BufferedReader input = new BufferedReader(
				new InputStreamReader(
						new ByteArrayInputStream(request.getBytes())));
		
		String response = protocol.processInput(input);
		assertEquals("HTTP/1.0 200 OK\n\r\n", response);
	}

	@Test
	public void handlesMissingBlankLine() {
		String request = 
			"POST / HTTP/1.0\n" +
			"User-Agent: HTTPTool/1.0\n" +
			"Content-Length: 26\n" +
			"<html>Hello World!\n</html>";
		
		BufferedReader input = new BufferedReader(
				new InputStreamReader(
						new ByteArrayInputStream(request.getBytes())));
		
		String response = protocol.processInput(input);
		assertEquals("HTTP/1.0 400 Bad Request\n\r\n", response);
	}

	@Test
	public void handlesMalformedInitialLine() {
		String request = 
			"POST/HTTP/1.0\n" +
			"User-Agent: HTTPTool/1.0\n" +
			"Content-Length: 26\n\r\n" +
			"<html>Hello World!\n</html>";
		
		BufferedReader input = new BufferedReader(
				new InputStreamReader(
						new ByteArrayInputStream(request.getBytes())));
		
		String response = protocol.processInput(input);
		assertEquals("HTTP/1.0 400 Bad Request\n\r\n", response);
	}
	
	@Test
	public void retrievesFile() throws FileNotFoundException {
		String request = 
			"GET /test HTTP/1.0\n\r\n";

		BufferedReader input = new BufferedReader(
				new InputStreamReader(
						new ByteArrayInputStream(request.getBytes())));
		
		context.checking(new Expectations() {{
			allowing(fileHandler).retrieveFile("/test");
			will(returnValue("test\ntest\n"));
		}});
		
		String response = protocol.processInput(input);
		assertEquals("HTTP/1.0 200 OK\n\r\n" +
				"test\ntest\n", response);
	}
	
	@Test
	public void returnsFileNotFound() throws FileNotFoundException {
		String request = 
			"GET /fdngkjdfg HTTP/1.0\n\r\n";

		BufferedReader input = new BufferedReader(
				new InputStreamReader(
						new ByteArrayInputStream(request.getBytes())));
		
		context.checking(new Expectations() {{
			allowing(fileHandler).retrieveFile("/fdngkjdfg");
			will(throwException(new FileNotFoundException()));
		}});
		
		String response = protocol.processInput(input);
		assertEquals("HTTP/1.0 404 Not Found\n\r\n", response);
	}
}
