package config;

import org.apache.log4j.Logger;

import exception.PropertyFileException;

public class DatabaseConfig {
	private DatabaseConfig() {}
	public static final Logger LOGGER = Logger.getLogger(DatabaseConfig.class);
	/**
	 * url for jdbc
	 * @return jdbc:postgresql: + property(URL_SYSTEM)
	 */
	public static String getUrl() {
		try{
		return "jdbc:postgresql:"+DatabaseFileUtils.getInstance().getProperty("URL_SYSTEM");
	}catch(PropertyFileException propertyFileException)
	{
		LOGGER.error(propertyFileException.getMessage());
	}
		return null;
	}
	/**
	 * 
	 * @return property USER_NAME
	 */
	public static String getUserName() {
		try {
		return DatabaseFileUtils.getInstance().getProperty("USER_NAME");
	}catch(PropertyFileException propertyFileException)
	{
		LOGGER.error(propertyFileException.getMessage());
	}
		return null;
	}
	/**
	 * 
	 * @return property USER_PASSWORD
	 */
	public static String getUserPassword() {
		try {
			return DatabaseFileUtils.getInstance().getProperty("USER_PASSWORD");
		}catch(PropertyFileException propertyFileException)
		{
			LOGGER.error(propertyFileException.getMessage());
		}
			return null;
	}
	/**
	 * 
	 * @return property DATABASE
	 */
	public static String getDatabase() {
		try {
			return DatabaseFileUtils.getInstance().getProperty("DATABASE");
		}catch(PropertyFileException propertyFileException)
		{
			LOGGER.error(propertyFileException.getMessage());
		}
			return null;
	}
}
