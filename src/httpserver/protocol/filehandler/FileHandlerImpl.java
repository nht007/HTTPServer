package httpserver.protocol.filehandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileHandlerImpl implements FileHandler {
	public String retrieveFile(String path) throws FileNotFoundException {
		if (path.equals("/")) return "";
		
		BufferedReader file = new BufferedReader(
				new FileReader("public" + path));
		
		String body = "";
		try {
			String readLine;
			while ((readLine = file.readLine()) != null) {
				body += readLine + "\n";
			}
			
			file.close();
		} catch (IOException e) {
			
		}
		
		return body;
	}
}
