package database.insert;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class Server {
	public static final Logger LOGGER = Logger.getLogger(Server.class);
	
	private Server() {}
	
	public static void insertServer(String name)
	{
		String s ="INSERT INTO server(name) VALUES('"+name+"')";
		LOGGER.info(s);
		try( Statement st = DriverManager.getConnection(init.Main.CONN).createStatement())
		{
			st.execute(s);
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}
	
}
