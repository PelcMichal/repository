package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

import exception.PropertyFileException;

public class DatabaseFileUtils {
	public static final Logger LOGGER = Logger.getLogger(DatabaseFileUtils.class);
	
	private static DatabaseFileUtils instance;
	
	private final String databasePropertyFile = Config.getDatabasePropertyFile();
	private final Properties properties;
	
	private DatabaseFileUtils() {
		LOGGER.debug("bigin");
		properties = new Properties();
		try {
			FileInputStream fileInputStream = new FileInputStream(databasePropertyFile);
			properties.load(fileInputStream);
			fileInputStream.close();
		} catch (IOException ex)
		{
			LOGGER.error(ex);
		}
	}
	/**
	 * Singlton
	 * @return
	 */
	public static DatabaseFileUtils getInstance()
	{
		if(instance ==null)
		{
			LOGGER.debug("New instance");
			instance = new DatabaseFileUtils();
		}
		else
		{
			LOGGER.debug("Old instance");
		}
		return instance;
	}
	/**
	 * deletes INSTANCE
	 */
	public static void cancelInstance()
	{
		instance = null;
	}
	/**
	 * gives property from a file based on name
	 * @param propertyName Name name of property
	 * @return property you named
	 * @throws PropertyFileException
	 */
	public String getProperty(String propertyName) throws PropertyFileException
	{
		
		String propertyValue = properties.getProperty(propertyName);
		
		LOGGER.debug("propertyName: "+propertyName);
		LOGGER.debug("propertyValue: "+propertyValue);
		
		if(propertyValue == null)
		{
			LOGGER.error("Property not found");
			throw new PropertyFileException("Property not found",propertyName,propertyValue);
		}
		return propertyValue;
	}
	/**
	 * 
	 * @return all Properties
	 */
	public Properties getProperties() {
		return properties;
	}
	
	
}
