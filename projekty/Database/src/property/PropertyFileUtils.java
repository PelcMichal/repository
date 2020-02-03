package property;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

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
	public static PropertyFileUtils getInstance()
	{
		LOGGER.debug("bigin");
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
}
