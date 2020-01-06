package projekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import database.DatabaseFacade;

public class Main {
	
	public static final Logger LOGGER = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PropertyConfigurator.configure(config.Config.getLogPropertyFile());
		
		LOGGER.debug("*** BEGIN ***");
		
		/*
		CREATE TABLE kmen(id serial unique,jmeno VARCHAR (50) NOT NULL,mnozstvi integer NOT NULL)
		CREATE TABLE zmeny(id serial PRIMARY KEY,id_kmen serial NOT NULL,jmeno VARCHAR (50),zmena_mnozstvi integer NOT NULL,odeslana TIMESTAMPTZ DEFAULT Now(),zpracovano BOOLEAN DEFAULT false)
		CREATE TABLE historie(id serial PRIMARY KEY,id_kmen serial NOT NULL,id_zmeny serial NOT NULL,jmeno VARCHAR (50) NOT NULL,zmena_mnozstvi integer NOT NULL,bylo_provedeno BOOLEAN NOT NULL,zpracovano TIMESTAMPTZ DEFAULT Now())
		 */
		try {
			Connection conn = DriverManager.getConnection(config.DatabaseConfig.getUrl(), config.DatabaseFileUtils.getInstance().getProperties());
			
			Statement stmt = conn.createStatement();
			Statement stmtt = conn.createStatement();
			Statement stmttt = conn.createStatement();
			//stmt.execute("CREATE DATABASE projekt");
			//stmt.execute("DROP DATABASE projekt");
			stmt.execute("DROP table kmen");
			stmt.execute("DROP table zmeny");
			stmt.execute("DROP table historie");
			DatabaseFacade.tryToCreate(conn);
			
			//stmt.execute("INSERT INTO kmen(id, jmeno, mnozstvi) VALUES (1, 'a', 1)");
			//stmt.execute("INSERT INTO zmeny(id_kmen, jmeno, zmena_mnozstvi) VALUES (1, 'a', 1)");
			
			stmt.execute(DatabaseFacade.createZmena(1, "lopata", 10));
			stmt.execute(DatabaseFacade.createZmena(1, "lopata", -5));
			stmt.execute(DatabaseFacade.createZmena(1, "lopata", -10));
			stmt.execute(DatabaseFacade.createZmena(1, "lopata", 5));
			stmt.execute(DatabaseFacade.createZmena(2, "krumpac", 5));
			/*
			ResultSet rs = stmt.executeQuery("SELECT VERSION()");

	            if (rs.next()) {
	                System.out.println(rs.getString(1));
	            }*/
			DatabaseFacade.vipis(conn);
			ResultSet z = stmt.executeQuery("SELECT id_kmen,jmeno,zmena_mnozstvi,id FROM zmeny WHERE zpracovano = false");
			
			while (z.next()) 
			{
				
				ResultSet k = stmtt.executeQuery("SELECT id,jmeno,mnozstvi FROM kmen WHERE id = "+z.getInt(1));
				if (k.next())
				{
					if(k.getInt(3)+z.getInt(3)>=0)
					{
						stmttt.execute(DatabaseFacade.updateKmen(z.getInt(1), z.getInt(3), k.getInt(3)));
						stmttt.execute(DatabaseFacade.createHistory(z.getInt(1), z.getInt(3), true, k.getString(2)));
						stmttt.execute(DatabaseFacade.zmenaZpracovana(z.getInt(4)));
						LOGGER.debug("Added to "+ k.getString(2));
					}
					else
					{
						stmttt.execute(DatabaseFacade.createHistory(z.getInt(1), z.getInt(3), false, k.getString(2)));
						stmttt.execute(DatabaseFacade.zmenaZpracovana(z.getInt(4)));
						LOGGER.debug("Not enough "+ k.getString(2));
					}
				}
				else
				{
					stmttt.execute(DatabaseFacade.createKmen(z.getInt(1), z.getString(2), z.getInt(3)));
					stmttt.execute(DatabaseFacade.createHistory(z.getInt(1), z.getInt(3), true, z.getString(2)));
					stmttt.execute(DatabaseFacade.zmenaZpracovana(z.getInt(4)));
					LOGGER.debug("Created "+z.getString(2));
				}
				
				
				//System.out.println(z.getInt(1)+z.getString(2)+z.getInt(3));
				LOGGER.debug("Chenge finished");
			}
			DatabaseFacade.vipis(conn);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e);
		}
		
		LOGGER.debug("***  END  ***");
	}

}
