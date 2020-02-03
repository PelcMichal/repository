package Exception001;

public class Config {
	private static final String LOG_PROPERTY_FILE = "C:\\dev\\eclipse-projects\\Exception001\\properties\\log.properties";
	private static final String PROPERTY_FILE = "C:\\dev\\eclipse-projects\\Exception001\\properties\\exception001.properties";
	
	public static String getLogPropertyFile()
	{
		return LOG_PROPERTY_FILE;
	}

	public static String getPropertyFile() {
		return PROPERTY_FILE;
	}
	
	
}
