package commons;

public class LoggedInReader {
	
	static String loggedInReader = null;
	
	public static String getLoggedInReader() {
		if (loggedInReader != null) {
			return loggedInReader;
		}
		return null;
	}
	
	public static void setLoggedInUser(String readerId) {
		loggedInReader = readerId;
	}
	

}
