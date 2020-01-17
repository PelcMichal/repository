package projekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import database.DatabaseFacade;

public class Main {
	
	public static final Logger LOGGER = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		
		PropertyConfigurator.configure(config.Config.getLogPropertyFile());
		
		LOGGER.debug("*** BEGIN ***");
		try (Connection conn = DriverManager.getConnection(config.DatabaseConfig.getUrl(), config.DatabaseFileUtils.getInstance().getProperties());){
			DatabaseFacade.tryToCreate(conn.createStatement());
			DatabaseFacade.provedZmeny(conn);
			System.out.println("Blbost");			
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		
		LOGGER.debug("***  END  ***");
	}

}
