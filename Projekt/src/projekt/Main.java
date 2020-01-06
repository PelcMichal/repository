package projekt;

import java.sql.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import config.DatabaseFileUtils;
import exception.PropertyFileException;
import database.DatabaseFacade;

public class Main {
	
	public static final Logger LOGGER = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PropertyConfigurator.configure(config.Config.getLogPropertyFile());
		
		LOGGER.debug("*** BEGIN ***");
		
		
		try {
			Connection conn = DriverManager.getConnection(config.DatabaseConfig.getUrl(), config.DatabaseFileUtils.getInstance().getProperties());
			
			Statement stmt = conn.createStatement();
			//stmt.execute("CREATE DATABASE projekt");
			//stmt.execute("DROP DATABASE projekt");
			//stmt.execute("DROP table zmeny");
			DatabaseFacade.tryToCreate(conn);
			
			
			/*
			ResultSet rs = stmt.executeQuery("SELECT * FROM zmeny WHERE zpracovano = false");
			if (rs.next()) 
			{
				System.out.println(rs);
			}
			System.out.println(rs);
			System.out.println("f");*/
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e);
		}
		
		LOGGER.debug("***  END  ***");
	}

}
