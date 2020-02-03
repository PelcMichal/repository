package property;

public class Config {
	private static final String LOG_PROPERTY_FILE = "C:\\dev\\eclipse-projects\\Database\\config\\log.properties";
	private static final String PROPERTY_FILE = "C:\\dev\\eclipse-projects\\Database\\config\\database.properties";
	
	public static String getLogPropertyFile()
	{
		return LOG_PROPERTY_FILE;
	}

	public static String getPropertyFile() {
		return PROPERTY_FILE;
	}
	
	
}
