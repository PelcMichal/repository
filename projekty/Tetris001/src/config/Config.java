package config;

import org.apache.log4j.Logger;

import config.PropertyFileException;
import config.PropertyFileUtils;

@SuppressWarnings("unused")
public class Config {
	private static final String LOG_PROPERTY_FILE = ".\\config\\log.properties";
	private static final String PROPERTY_FILE = ".\\config\\config.properties";
	public static final Logger LOGGER = Logger.getLogger(PropertyFileUtils.class);
	public static String getLogPropertyFile()
	{
		return LOG_PROPERTY_FILE;
	}
	
	
	
	public static String getPropertyFile() {
		return PROPERTY_FILE;
	}
	/*
	  try {
			return PropertyFileUtils.getInstance().getProperty("DRIVER_CLASS");
		}catch(PropertyFileException propertyFileException)
		{
			LOGGER.error(propertyFileException.getMessage());
		}
			return null;
	 */
	
}
