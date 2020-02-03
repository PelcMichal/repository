package database;

import org.apache.log4j.Logger;

import property.PropertyFileException;
import property.PropertyFileUtils;

public class DatabaseConfig {
	public static final Logger LOGGER = Logger.getLogger(Database.class);
	
	//private static final String DRIVER_CLASS = "com.ibm.as400.access.AS400JDBCDriver";
	//private static final String URL_PROTOCOL = "jdbc:as400://";
	//private static final String URL_SYSTEM = "193.179.195.134";
	//private static final String URL = URL_PROTOCOL +URL_SYSTEM;
	//private static final String USER_NAME = "user3";
	//private static final String USER_PASSWORD = "user3";
	//private static final String LOG_PROPERTY_FILE ="C:\\dev\\eclipse-projects\\Database\\config\\log.properties";
	
	
	public static String getDriverClass() {
		try {
			return PropertyFileUtils.getInstance().getProperty("DRIVER_CLASS");
		}catch(PropertyFileException propertyFileException)
		{
			LOGGER.error(propertyFileException.getMessage());
		}
			return null;
	}
	
	public static String getUrl() {
		try{
		return PropertyFileUtils.getInstance().getProperty("URL_PROTOCOL")+PropertyFileUtils.getInstance().getProperty("URL_SYSTEM");
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
	
	public static String getLogPropertyFile() {
		try {
			return PropertyFileUtils.getInstance().getProperty("LOG_PROPERTY_FILE");
		}catch(PropertyFileException propertyFileException)
		{
			LOGGER.error(propertyFileException.getMessage());
		}
			return null;
	}
	
	public static String getTable() {
		try {
			return PropertyFileUtils.getInstance().getProperty("Table");
		}catch(PropertyFileException propertyFileException)
		{
			LOGGER.error(propertyFileException.getMessage());
		}
			return null;
	}
	
}
