package httpserver.protocol.filehandler;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.*;

public class FileHandlerImplTest {
	private static FileHandler fileHandler;
	@BeforeClass
	public static void constructFileHandler() {
		fileHandler = new FileHandlerImpl();
	}
	
	@Test
	public void retrievesAFileFromAPath() {
		String path = "/test";
		String file = null;
		try {
			file = fileHandler.retrieveFile(path);
		} catch (FileNotFoundException e) {
			
		}
		assertEquals("test\ntest\n", file);
	}
	 
	@Test
	public void retrievesAFileThatDoesntExist() {
		String path = "/nkgd";
		FileNotFoundException exception = null;
		try {
			fileHandler.retrieveFile(path);
		} catch (FileNotFoundException e) {
			exception = e;
		}
		
		assertNotNull(exception);
	}
	
	@Test
	public void retrieveRoot() {
		String path = "/";
		String file = null;
		try {
			file = fileHandler.retrieveFile(path);
		} catch (FileNotFoundException e) {
			
		}
		assertEquals("", file);
	}
	
	@Test
	public void retrievesHTMLFromAPath() {
		String path = "/blog";
		String file = null;
		try {
			file = fileHandler.retrieveFile(path);
		} catch (FileNotFoundException e) {
			
		}
		assertTrue(file.contains("<!DOCTYPE html>"));
	}
}
