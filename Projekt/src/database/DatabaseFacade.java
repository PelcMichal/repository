package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import config.DatabaseFileUtils;
import exception.PropertyFileException;

public class DatabaseFacade {
	public static final Logger LOGGER = Logger.getLogger(DatabaseFacade.class);
	public static void tryToCreate(Connection conn)
	{
		/*
		CREATE TABLE kmen(id serial PRIMARY KEY,jmeno VARCHAR (50) NOT NULL,mnozstvi integer NOT NULL)
		CREATE TABLE zmeny(id serial PRIMARY KEY,id_kmen serial NOT NULL,jmeno VARCHAR (50),mnozstvi integer NOT NULL,odeslana TIMESTAMP NOT NULL)
		CREATE TABLE historie(id serial PRIMARY KEY,id_kmen serial NOT NULL,jmeno VARCHAR (50) NOT NULL,mnozstvi integer NOT NULL,bylo_provedeno BOOLEAN NOT NULL,zpracovano TIMESTAMP NOT NULL)
		 */
		try {
			Statement stmt = conn.createStatement();
			/*
		try {
					stmt.execute("CREATE DATABASE "+DatabaseFileUtils.getInstance().getProperty("DATABASE"));
					stmt.execute("\\c "+DatabaseFileUtils.getInstance().getProperty("DATABASE"));
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.debug("Database alredy exist");;
		} catch (PropertyFileException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e);
		} */
		
		try {
			stmt.execute("CREATE TABLE kmen(id serial PRIMARY KEY,jmeno VARCHAR (50) NOT NULL,mnozstvi integer NOT NULL)");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.debug("Table kmen alredy exists");;
		}
		try {
			stmt.execute("CREATE TABLE zmeny(id serial PRIMARY KEY,id_kmen serial NOT NULL,jmeno VARCHAR (50),mnozstvi integer NOT NULL,odeslana TIMESTAMP NOT NULL)");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.debug("Table zmeny alredy exists");;
		}
		try {
			stmt.execute("CREATE TABLE historie(id serial PRIMARY KEY,id_kmen serial NOT NULL,jmeno VARCHAR (50) NOT NULL,mnozstvi integer NOT NULL,bylo_provedeno BOOLEAN NOT NULL,zpracovano TIMESTAMP NOT NULL)");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.debug("Table historie alredy exists");;
		}
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			LOGGER.error("Cannot create Statment	"+e1);
		}
	}
}
