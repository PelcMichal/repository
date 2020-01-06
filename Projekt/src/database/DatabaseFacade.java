package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DatabaseFacade {
	public static final Logger LOGGER = Logger.getLogger(DatabaseFacade.class);
	/*
		CREATE TABLE kmen(id serial unique,jmeno VARCHAR (50) NOT NULL,mnozstvi integer NOT NULL)
		CREATE TABLE zmeny(id serial PRIMARY KEY,id_kmen serial NOT NULL,jmeno VARCHAR (50),zmena_mnozstvi integer NOT NULL,odeslana TIMESTAMPTZ DEFAULT Now(),zpracovano BOOLEAN DEFAULT false)
		CREATE TABLE historie(id serial PRIMARY KEY,id_kmen serial NOT NULL,id_zmeny serial NOT NULL,jmeno VARCHAR (50) NOT NULL,zmena_mnozstvi integer NOT NULL,bylo_provedeno BOOLEAN NOT NULL,zpracovano TIMESTAMPTZ DEFAULT Now())
		 */
	/**
	 * tries to create necesary tables for working with database
	 * @param conn
	 */
	public static void tryToCreate(Connection conn)
	{
		
		try {
			Statement stmt = conn.createStatement();	
		try {
			stmt.execute("CREATE TABLE kmen(id serial unique,jmeno VARCHAR (50) NOT NULL,mnozstvi integer NOT NULL)");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.debug("Table kmen alredy exists");;
		}
		try {
			stmt.execute("CREATE TABLE zmeny(id serial PRIMARY KEY,id_kmen serial NOT NULL,jmeno VARCHAR (50),zmena_mnozstvi integer NOT NULL,odeslana TIMESTAMPTZ DEFAULT Now(),zpracovano BOOLEAN DEFAULT false)");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.debug("Table zmeny alredy exists");;
		}
		try {
			stmt.execute("CREATE TABLE historie(id serial PRIMARY KEY,id_kmen serial NOT NULL,jmeno VARCHAR (50) NOT NULL,zmena_mnozstvi integer NOT NULL,bylo_provedeno BOOLEAN NOT NULL,zpracovano TIMESTAMPTZ DEFAULT Now())");
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
	/**
	 * creates string to create line in table kmen
	 * @param id
	 * @param jmeno
	 * @param mnozstvi
	 * @return INSERT INTO kmen(id, jmeno, mnozstvi) VALUES (id, 'jmeno', mnozstvi)
	 */
	public static String createKmen(int id, String jmeno, int mnozstvi)
	{
		StringBuilder st = new StringBuilder();
		st.append("INSERT INTO kmen(id, jmeno, mnozstvi) VALUES (");
		st.append(id);
		st.append(", '");
		st.append(jmeno);
		st.append("', ");
		st.append(mnozstvi);
		st.append(")");
		return st.toString();
	}
	/**
	 * creates string to update line in table zmeny
	 * @param id
	 * @return UPDATE zmeny SET zpracovano = true WHERE id = id
	 */
	public static String zmenaZpracovana(int id)
	{
		//UPDATE zmena SET zpracovano = 'true' WHERE id = id;
		return "UPDATE zmeny SET zpracovano = true WHERE id = "+id;
	}
	/**
	 * creates string to create line in table historie
	 * @param id_kmen
	 * @param zmena_mnozstvi
	 * @param bylo_provedeno
	 * @param jmeno
	 * @return INSERT INTO historie(id_kmen, zmena_mnozstvi, bylo_provedeno,jmeno) VALUES (id_kmen, zmena_mnozstvi, bylo_provedeno, 'jmeno')
	 */
	public static String createHistory(int id_kmen,int zmena_mnozstvi, boolean bylo_provedeno,String jmeno)
	{
		//INSERT INTO historie(id_kmen, id_zmeny, zmena_mnozstvi, bylo_provedeno,jmeno) VALUES (")
		StringBuilder st = new StringBuilder();
		st.append("INSERT INTO historie(id_kmen, zmena_mnozstvi, bylo_provedeno,jmeno) VALUES (");
		st.append(id_kmen);
		st.append(", ");
		st.append(zmena_mnozstvi);
		st.append(", ");
		st.append(bylo_provedeno);
		st.append(", '");
		st.append(jmeno);
		st.append("')");
		return st.toString();
	}
	/**
	 * creates string to update line in table zmeny
	 * @param id
	 * @param zmena_mnozstvi
	 * @param mnozstni
	 * @return UPDATE kmen SET mnozstvi = mnozstni+zmena_mnozstvi WHERE id = id
	 */
	public static String updateKmen(int id,int zmena_mnozstvi,int mnozstni)
	{
		StringBuilder st = new StringBuilder();
		st.append("UPDATE kmen SET mnozstvi = ");
		st.append(mnozstni+zmena_mnozstvi);
		st.append(" WHERE id = ");
		st.append(id);
		return st.toString();
	}
	/**
	 * creates string to create line in table zmeny
	 * @param id_kmen
	 * @param jmeno
	 * @param zmena_mnozstvi
	 * @return INSERT INTO zmeny(id_kmen, jmeno, zmena_mnozstvi) VALUES (id_kmen, 'jmeno', zmena_mnozstvi)
	 */
	public static String createZmena(int id_kmen,String jmeno,int zmena_mnozstvi)
	{
		//stmt.execute("INSERT INTO zmeny(id_kmen, jmeno, zmena_mnozstvi) VALUES (1, 'a', 1)");
		StringBuilder st = new StringBuilder();
		st.append("INSERT INTO zmeny(id_kmen, jmeno, zmena_mnozstvi) VALUES (");
		st.append(id_kmen);
		st.append(", '");
		st.append(jmeno);
		st.append("', ");
		st.append(zmena_mnozstvi);
		st.append(")");
		return st.toString();
		
	}
	/**
	 * write tble kmen, zmeny and historie to console
	 * @param conn
	 */
	public static void vipis(Connection conn) 
	{
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			ResultSet k = stmt.executeQuery("SELECT id,jmeno,mnozstvi FROM kmen ORDER BY id");
			System.out.println("Kmen\nid\tjmeno\t\tmnozstvi");
			while (k.next()) 
			{
				
				StringBuilder st = new StringBuilder();
				st.append(k.getInt(1));
				st.append("\t");
				st.append(k.getString(2));
				st.append("\t\t");
				st.append(k.getInt(3));
				System.out.println(st.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ResultSet z = stmt.executeQuery("SELECT id,id_kmen,jmeno,zmena_mnozstvi,odeslana,zpracovano FROM zmeny ORDER BY odeslana");
			System.out.println("Zmeny\nid\tid_kmen\tjmeno\t\tzmena_mnozstvi\todeslana\t\t\tzpracovano");
			while (z.next()) 
			{
				StringBuilder st = new StringBuilder();
				st.append(z.getInt(1));
				st.append("\t");
				st.append(z.getInt(2));
				st.append("\t");
				st.append(z.getString(3));
				st.append("\t\t");
				st.append(z.getInt(4));
				st.append("\t\t");
				st.append(z.getTimestamp(5));
				st.append("\t");
				st.append(z.getBoolean(6));
				System.out.println(st);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ResultSet h = stmt.executeQuery("SELECT id,id_kmen,jmeno,zmena_mnozstvi,zpracovano,bylo_provedeno FROM historie ORDER BY zpracovano");
			System.out.println("Historie\nid\tid_kmen\tjmeno\t\tzmena_mnozstvi\tzpracovano\t\t\tbylo_provedeno");
			while (h.next()) 
			{
				StringBuilder st = new StringBuilder();
				st.append(h.getInt(1));
				st.append("\t");
				st.append(h.getInt(2));
				st.append("\t");
				st.append(h.getString(3));
				st.append("\t\t");
				st.append(h.getInt(4));
				st.append("\t\t");
				st.append(h.getTimestamp(5));
				st.append("\t");
				st.append(h.getBoolean(6));
				System.out.println(st);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
