package config;

public class Config {
	private static final String LOG_PROPERTY_FILE = ".\\config\\log.properties";
	private static final String DATABASE_PROPERTY_FILE = ".\\config\\database.properties";

	private Config() {}
	
	public static String getLogPropertyFile()
	{
		return LOG_PROPERTY_FILE;
	}
	public static String getDatabasePropertyFile() {
		return DATABASE_PROPERTY_FILE;
	}	
	
}
