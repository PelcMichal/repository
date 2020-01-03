package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

import exception.PropertyFileException;

public class DatabaseFileUtils {
	public static final Logger LOGGER = Logger.getLogger(DatabaseFileUtils.class);
	
	private static DatabaseFileUtils INSTANCE;
	
	private final String DATABASE_PROPERTY_FILE = Config.getDatabasePropertyFile();
	private final Properties properties;
	
	private DatabaseFileUtils() {
		LOGGER.debug("bigin");
		
		properties = new Properties();
		
		try {
			FileInputStream fileInputStream = new FileInputStream(DATABASE_PROPERTY_FILE);
			properties.load(fileInputStream);
			fileInputStream.close();
		} catch (IOException ex)
		{
			LOGGER.error(ex);
		}
		//PropertyFileUtils
	}
	public static DatabaseFileUtils getInstance()
	{
		LOGGER.debug("bigin");
		if(INSTANCE ==null)
		{
			LOGGER.debug("New instance");
			INSTANCE = new DatabaseFileUtils();
		}
		else
		{
			LOGGER.debug("Old instance");
		}
		return INSTANCE;
	}
	
	public static void cancelInstance()
	{
		INSTANCE = null;
	}
	
	public String getProperty(String propertyName) throws PropertyFileException
	{
		LOGGER.debug("bigin");
		
		String propertyValue = properties.getProperty(propertyName);
		
		LOGGER.debug("propertyName: "+propertyName);
		LOGGER.debug("propertyValue: "+propertyValue);
		
		if(propertyValue == null)
		{
			LOGGER.error("Property not found");
			PropertyFileException propertyFileException = new PropertyFileException("Property not found");
			propertyFileException.setPropertyName(propertyName);
			propertyFileException.setPropertyValue(propertyValue);
			throw propertyFileException;
		}
		
		return propertyValue;
	}
	public Properties getProperties() {
		return properties;
	}
	
	
}
