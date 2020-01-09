package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DatabaseFacade {
	public static final Logger LOGGER = Logger.getLogger(DatabaseFacade.class);
	private DatabaseFacade() {}
	
	/**
	 * tries to create necesary tables for working with database
	 * @param conn
	 * CREATE TABLE kmen(id serial unique,jmeno VARCHAR (50) NOT NULL,mnozstvi integer NOT NULL)
	 * CREATE TABLE zmeny(id serial PRIMARY KEY,id_kmen serial NOT NULL,jmeno VARCHAR (50),zmena_mnozstvi integer NOT NULL,odeslana TIMESTAMPTZ DEFAULT Now(),zpracovano BOOLEAN DEFAULT false)
	 * CREATE TABLE historie(id serial PRIMARY KEY,id_kmen serial NOT NULL,id_zmeny serial NOT NULL,jmeno VARCHAR (50) NOT NULL,zmena_mnozstvi integer NOT NULL,bylo_provedeno BOOLEAN NOT NULL,zpracovano TIMESTAMPTZ DEFAULT Now())
	 */
	public static void tryToCreate(Statement stmt)
	{	
		try {
			stmt.execute("CREATE TABLE kmen(id serial unique,jmeno VARCHAR (50) NOT NULL,mnozstvi integer NOT NULL)");
		}
		catch (SQLException e) {
			LOGGER.debug("Table kmen alredy exists");
		}
		try {
			stmt.execute("CREATE TABLE zmeny(id serial PRIMARY KEY,id_kmen serial NOT NULL,jmeno VARCHAR (50),zmena_mnozstvi integer NOT NULL,odeslana TIMESTAMPTZ DEFAULT Now(),zpracovano BOOLEAN DEFAULT false)");
		}
		catch (SQLException e) {
			LOGGER.debug("Table zmeny alredy exists");
		}
		try {
			stmt.execute("CREATE TABLE historie(id serial PRIMARY KEY,id_kmen serial NOT NULL,jmeno VARCHAR (50) NOT NULL,zmena_mnozstvi integer NOT NULL,bylo_provedeno BOOLEAN NOT NULL,zpracovano TIMESTAMPTZ DEFAULT Now())");
		}
		catch (SQLException e) {
			LOGGER.debug("Table historie alredy exists");
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
		return "UPDATE zmeny SET zpracovano = true WHERE id = "+id;
	}
	/**
	 * creates string to create line in table historie
	 * @param idKmen
	 * @param zmenaMnozstvi
	 * @param byloProvedeno
	 * @param jmeno
	 * @return INSERT INTO historie(id_kmen, zmena_mnozstvi, bylo_provedeno,jmeno) VALUES (id_kmen, zmena_mnozstvi, bylo_provedeno, 'jmeno')
	 */
	public static String createHistory(int idKmen,int zmenaMnozstvi, boolean byloProvedeno,String jmeno)
	{
		StringBuilder st = new StringBuilder();
		st.append("INSERT INTO historie(id_kmen, zmena_mnozstvi, bylo_provedeno,jmeno) VALUES (");
		st.append(idKmen);
		st.append(", ");
		st.append(zmenaMnozstvi);
		st.append(", ");
		st.append(byloProvedeno);
		st.append(", '");
		st.append(jmeno);
		st.append("')");
		return st.toString();
	}
	/**
	 * creates string to update line in table zmeny
	 * @param id
	 * @param zmenaMnozstvi
	 * @param mnozstni
	 * @return UPDATE kmen SET mnozstvi = mnozstni+zmena_mnozstvi WHERE id = id
	 */
	public static String updateKmen(int id,int zmenaMnozstvi,int mnozstni)
	{
		StringBuilder st = new StringBuilder();
		st.append("UPDATE kmen SET mnozstvi = ");
		st.append(mnozstni+zmenaMnozstvi);
		st.append(" WHERE id = ");
		st.append(id);
		return st.toString();
	}
	/**
	 * creates string to create line in table zmeny
	 * @param idKmen
	 * @param jmeno
	 * @param zmenaMnozstvi
	 * @return INSERT INTO zmeny(id_kmen, jmeno, zmena_mnozstvi) VALUES (id_kmen, 'jmeno', zmena_mnozstvi)
	 */
	public static String createZmena(int idKmen,String jmeno,int zmenaMnozstvi)
	{
		StringBuilder st = new StringBuilder();
		st.append("INSERT INTO zmeny(id_kmen, jmeno, zmena_mnozstvi) VALUES (");
		st.append(idKmen);
		st.append(", '");
		st.append(jmeno);
		st.append("', ");
		st.append(zmenaMnozstvi);
		st.append(")");
		return st.toString();
		
	}
	/**
	 * write table kmen, zmeny and historie to console
	 * @param conn
	 * @return 
	 * @throws SQLException 
	 */
	public static String vipis(Connection conn) throws SQLException 
	{
		StringBuilder st = new StringBuilder();
		try(
				ResultSet k = conn.createStatement().executeQuery("SELECT id,jmeno,mnozstvi FROM kmen ORDER BY id");
				ResultSet z = conn.createStatement().executeQuery("SELECT id,id_kmen,jmeno,zmena_mnozstvi,odeslana,zpracovano FROM zmeny ORDER BY odeslana");
				ResultSet h = conn.createStatement().executeQuery("SELECT id,id_kmen,jmeno,zmena_mnozstvi,zpracovano,bylo_provedeno FROM historie ORDER BY zpracovano");
			) {
			st.append("Kmen\nid\tjmeno\t\tmnozstvi\n");
			while (k.next()) 
			{
				st.append(k.getInt(1));
				st.append("\t");
				st.append(k.getString(2));
				st.append("\t\t");
				st.append(k.getInt(3));
				st.append("\n");
			}
			st.append("\nZmeny\nid\tid_kmen\tjmeno\t\tzmena_mnozstvi\todeslana\t\t\tzpracovano\n");
			while (z.next()) 
			{
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
				st.append("\n");
			}
			st.append("\nHistorie\nid\tid_kmen\tjmeno\t\tzmena_mnozstvi\tzpracovano\t\t\tbylo_provedeno\n");
			while (h.next()) 
			{
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
				st.append("\n");
			}
			st.append("\n\n");
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return st.toString();
	}
	/**
	 * 
	 * @param conn
	 */
	public static void provedZmeny(Connection conn)
	{
		try (
				ResultSet z = conn.createStatement().executeQuery("SELECT id_kmen,jmeno,zmena_mnozstvi,id FROM zmeny WHERE zpracovano = false");
				ResultSet k = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY ).executeQuery("SELECT id,jmeno,mnozstvi FROM kmen ORDER BY id");
				Statement stmt = conn.createStatement();
				){
			while (z.next()) 
			{
				boolean b = true;
				while (k.next())
				{
					if(k.getInt(1)==z.getInt(1))
					{
						if(k.getInt(3)+z.getInt(3)>=0)
						{
							stmt.execute(DatabaseFacade.updateKmen(z.getInt(1), z.getInt(3), k.getInt(3)));
							k.refreshRow();
							stmt.execute(DatabaseFacade.createHistory(z.getInt(1), z.getInt(3), true, k.getString(2)));
							stmt.execute(DatabaseFacade.zmenaZpracovana(z.getInt(4)));
							LOGGER.debug("Added to "+ k.getString(2));
						}
						else
						{
							stmt.execute(DatabaseFacade.createHistory(z.getInt(1), z.getInt(3), false, k.getString(2)));
							stmt.execute(DatabaseFacade.zmenaZpracovana(z.getInt(4)));
							LOGGER.debug("Not enough "+ k.getString(2));
						}
						b=false;
						break;
					}
				}
				if(b)
				{
					stmt.execute(DatabaseFacade.createKmen(z.getInt(1), z.getString(2), z.getInt(3)));
					k.refreshRow();
					stmt.execute(DatabaseFacade.createHistory(z.getInt(1), z.getInt(3), true, z.getString(2)));
					stmt.execute(DatabaseFacade.zmenaZpracovana(z.getInt(4)));
					LOGGER.debug("Created "+z.getString(2));
				}
				k.beforeFirst();
				LOGGER.debug("Chenge finished");
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}
}
