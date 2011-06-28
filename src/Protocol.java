public class Protocol {
    public String processInput(String inputString) {
    	String[] header = inputString.split(" ");
    	
    	for (String s : header) {
    		System.err.println(s);
    	}
    	return "HTTP/1.1 200 OK";
    }
}
