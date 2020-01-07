package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

import exception.PropertyFileException;

public class PropertyFileUtils {
	public static final Logger LOGGER = Logger.getLogger(PropertyFileUtils.class);
	
	private static PropertyFileUtils INSTANCE;
	
	private final String PROPERTY_FILE = Config.getPropertyFile();
	private final Properties properties;
	private PropertyFileUtils() {
		LOGGER.debug("bigin");
		properties = new Properties();
		try {
			FileInputStream fileInputStream = new FileInputStream(PROPERTY_FILE);
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
	public static PropertyFileUtils getInstance()
	{
		if(INSTANCE ==null)
		{
			LOGGER.debug("New instance");
			INSTANCE = new PropertyFileUtils();
		}
		else
		{
			LOGGER.debug("Old instance");
		}
		return INSTANCE;
	}
	/**
	 * deletes INSTANCE
	 */
	public static void cancelInstance()
	{
		INSTANCE = null;
	}
	/**
	 * gives property from a file based on name
	 * @param property Name name of property
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
			PropertyFileException propertyFileException = new PropertyFileException("Property not found");
			propertyFileException.setPropertyName(propertyName);
			propertyFileException.setPropertyValue(propertyValue);
			throw propertyFileException;
		}
		return propertyValue;
	}
}
