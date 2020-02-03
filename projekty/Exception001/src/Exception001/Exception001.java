package Exception001;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Exception001 {

	private static final Logger LOGGER = Logger.getLogger(Exception001.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PropertyConfigurator.configure(Config.getLogPropertyFile());
		
		LOGGER.debug("*** BIGIN ***");
		PropertyFileUtils propertyFileUtils = PropertyFileUtils.getInstance();
		
		String propertyStringValue;
		try
		{
			propertyStringValue = propertyFileUtils.getProperty("K1");
			System.out.println(propertyStringValue);
			
			propertyStringValue = propertyFileUtils.getProperty("K2");
			System.out.println(propertyStringValue);
			
			PropertyFileUtils.cancelInstance();
			propertyStringValue = PropertyFileUtils.getInstance().getProperty("K3");
			System.out.println(propertyStringValue);
		}catch(PropertyFileException propertyFileException)
		{
			LOGGER.error(propertyFileException.getMessage());
		}
		LOGGER.debug("*** END ***");
	}

}
