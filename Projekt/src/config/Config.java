package config;

public class Config {
	private static final String LOG_PROPERTY_FILE = ".\\config\\log.properties";
	private static final String PROPERTY_FILE = ".\\config\\config.properties";

	public static String getLogPropertyFile()
	{
		return LOG_PROPERTY_FILE;
	}
	public static String getPropertyFile() {
		return PROPERTY_FILE;
	}	
}
