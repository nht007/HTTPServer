package httpserver.protocol.filehandler;

import java.io.FileNotFoundException;

public interface FileHandler {
	public String retrieveFile(String path) throws FileNotFoundException;
}
