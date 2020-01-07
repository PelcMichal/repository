package projekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import database.DatabaseFacade;

public class CreateData {
	
	public static final Logger LOGGER = Logger.getLogger(CreateData.class);
	
	public static void main(String[] args) {
		PropertyConfigurator.configure(config.Config.getLogPropertyFile());
			
		try(
				Connection conn = DriverManager.getConnection(config.DatabaseConfig.getUrl(), config.DatabaseFileUtils.getInstance().getProperties());
				Statement stmt = conn.createStatement();	
				) {
			DatabaseFacade.tryToCreate(conn.createStatement());
			
			stmt.execute(DatabaseFacade.createZmena(0, "lopata", 0));
			stmt.execute(DatabaseFacade.createZmena(1, "krumpac", 0));
			stmt.execute(DatabaseFacade.createZmena(2, "vozicek", 0));
			Random r = new Random();
			for (int i = 0;i<100;i++)
			{
				
				stmt.execute(DatabaseFacade.createZmena(r.nextInt(3), "", r.nextInt(200)-100));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}

}
