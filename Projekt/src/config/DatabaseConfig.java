package config;

import org.apache.log4j.Logger;

import exception.PropertyFileException;

public class DatabaseConfig {
	public static final Logger LOGGER = Logger.getLogger(DatabaseConfig.class);
	
	public static String getUrl() {
		try{
		return "jdbc:postgresql:/"+PropertyFileUtils.getInstance().getProperty("URL_SYSTEM");
	}catch(PropertyFileException propertyFileException)
	{
		LOGGER.error(propertyFileException.getMessage());
	}
		return null;
	}
	public static String getUserName() {
		try {
		return PropertyFileUtils.getInstance().getProperty("USER_NAME");
	}catch(PropertyFileException propertyFileException)
	{
		LOGGER.error(propertyFileException.getMessage());
	}
		return null;
	}
	public static String getUserPassword() {
		try {
			return PropertyFileUtils.getInstance().getProperty("USER_PASSWORD");
		}catch(PropertyFileException propertyFileException)
		{
			LOGGER.error(propertyFileException.getMessage());
		}
			return null;
	}
	public static String getDatabase() {
		try {
			return PropertyFileUtils.getInstance().getProperty("DATABASE");
		}catch(PropertyFileException propertyFileException)
		{
			LOGGER.error(propertyFileException.getMessage());
		}
			return null;
	}
	
}
