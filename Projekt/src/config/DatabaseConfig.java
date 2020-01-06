package config;

import org.apache.log4j.Logger;

import exception.PropertyFileException;

public class DatabaseConfig {
	public static final Logger LOGGER = Logger.getLogger(DatabaseConfig.class);
	
	public static String getUrl() {
		try{
			/*
			try{
				return "jdbc:postgresql:"+DatabaseFileUtils.getInstance().getProperty("URL_SYSTEM")+DatabaseFileUtils.getInstance().getProperty("DATABASE");
			}catch(PropertyFileException propertyFileException)
			{
				
			}
			*/
		return "jdbc:postgresql:"+DatabaseFileUtils.getInstance().getProperty("URL_SYSTEM");
	}catch(PropertyFileException propertyFileException)
	{
		LOGGER.error(propertyFileException.getMessage());
	}
		return null;
	}
	public static String getUserName() {
		try {
		return DatabaseFileUtils.getInstance().getProperty("USER_NAME");
	}catch(PropertyFileException propertyFileException)
	{
		LOGGER.error(propertyFileException.getMessage());
	}
		return null;
	}
	public static String getUserPassword() {
		try {
			return DatabaseFileUtils.getInstance().getProperty("USER_PASSWORD");
		}catch(PropertyFileException propertyFileException)
		{
			LOGGER.error(propertyFileException.getMessage());
		}
			return null;
	}
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
