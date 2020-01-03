package projekt;

import java.sql.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main {
	
	public static final Logger LOGGER = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PropertyConfigurator.configure(config.Config.getLogPropertyFile());
		
		LOGGER.debug("*** BEGIN ***");
		
		
		try {
			Connection conn = DriverManager.getConnection(config.DatabaseConfig.getUrl(), config.DatabaseFileUtils.getInstance().getProperties());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e);
		}
		
		LOGGER.debug("***  END  ***");
	}

}
