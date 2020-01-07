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
		
		/*
		CREATE TABLE kmen(id serial unique,jmeno VARCHAR (50) NOT NULL,mnozstvi integer NOT NULL)
		CREATE TABLE zmeny(id serial PRIMARY KEY,id_kmen serial NOT NULL,jmeno VARCHAR (50),zmena_mnozstvi integer NOT NULL,odeslana TIMESTAMPTZ DEFAULT Now(),zpracovano BOOLEAN DEFAULT false)
		CREATE TABLE historie(id serial PRIMARY KEY,id_kmen serial NOT NULL,id_zmeny serial NOT NULL,jmeno VARCHAR (50) NOT NULL,zmena_mnozstvi integer NOT NULL,bylo_provedeno BOOLEAN NOT NULL,zpracovano TIMESTAMPTZ DEFAULT Now())
		 */
		try (Connection conn = DriverManager.getConnection(config.DatabaseConfig.getUrl(), config.DatabaseFileUtils.getInstance().getProperties());){
			DatabaseFacade.tryToCreate(conn.createStatement());
			LOGGER.info(DatabaseFacade.vipis(conn));
			DatabaseFacade.provedZmeny(conn);
			LOGGER.info(DatabaseFacade.vipis(conn));
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		
		LOGGER.debug("***  END  ***");
	}

}
