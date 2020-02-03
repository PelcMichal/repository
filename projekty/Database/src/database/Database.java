package database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import property.Config;


public class Database {
	
	public static final Logger LOGGER = Logger.getLogger(Database.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PropertyConfigurator.configure(Config.getLogPropertyFile());

		LOGGER.debug("*** BEGIN ***");
		
		try {
			Class.forName(DatabaseConfig.getDriverClass());
		}catch(ClassNotFoundException exception)
		{
			System.out.println("Failed to load driver");
			System.out.println(exception);
			return;
		}
		
		try {
			
			Connection connection = DriverManager.getConnection(DatabaseConfig.getUrl(),DatabaseConfig.getUserName(),
					DatabaseConfig.getUserPassword());
			Statement statment = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			String updateString[] = new String[3];
			updateString[0] = "update user3.user3t set cena = cena+1 where id = 1";
			updateString[1] = "update user3.user3t set cena = cena+2 where id > 2";
			updateString[2] = "update user3.user3t set cena = cena+3 where id < 3";
			for(int i = 0;i<updateString.length;i++)
			{
				statment.addBatch(updateString[i]);
			}
			int[] affectiedRows = statment.executeBatch();
			for(int i = 0;i<affectiedRows.length;i++)
			{
				System.out.println(updateString[i] + " # affectedRowes: " +affectiedRows[i]);
			}
			
			
			String queryString = "SELECT * FROM USER3.USER3T";
			
			
			
			ResultSet resultSet = statment.executeQuery(queryString);
			while (resultSet.next())
			{
				StringBuilder sb = new StringBuilder();
				sb.append("id = ");
				sb.append(resultSet.getInt("id"));
				sb.append("\t\tNazev = ");
				sb.append(resultSet.getString("nazev").trim());
				sb.append("\t\tCena = ");
				sb.append(resultSet.getBigDecimal("cena"));
				System.out.println(sb);
			}
			
			
			/*
			String queryString = "SELECT * FROM USER3.USER3T where  id > ?";
			System.out.println("prepere "+ queryString);
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			
			int paramId;
			ResultSet resultSet;
			
			paramId = 3;
			System.out.println("id > "+paramId);
			preparedStatement.setInt(1, paramId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				StringBuilder sb = new StringBuilder();
				sb.append("id = ");
				sb.append(resultSet.getInt("id"));
				sb.append("\t\tNazev = ");
				sb.append(resultSet.getString("nazev").trim());
				sb.append("\t\tCena = ");
				sb.append(resultSet.getBigDecimal("cena"));
				System.out.println(sb);
			}
			
			
			
			paramId = 2;
			System.out.println("id > "+paramId);
			preparedStatement.setInt(1, paramId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				StringBuilder sb = new StringBuilder();
				sb.append("id = ");
				sb.append(resultSet.getInt("id"));
				sb.append("\t\tNazev = ");
				sb.append(resultSet.getString("nazev").trim());
				sb.append("\t\tCena = ");
				sb.append(resultSet.getBigDecimal("cena"));
				System.out.println(sb);
			}
			*/
			/*
			LOGGER.debug("Qurry");
			String queryString = "SELECT * FROM USER3.USER3T";
			
			
			
			ResultSet resoultSet = statment.executeQuery(queryString);
			
			while (resoultSet.next())
			{
				StringBuilder sb = new StringBuilder();
				sb.append("id = ");
				sb.append(resoultSet.getInt("id"));
				sb.append("\t\tNazev = ");
				sb.append(resoultSet.getString("nazev"));
				sb.append("\t\tCena = ");
				sb.append(resoultSet.getBigDecimal("cena"));
				System.out.println(sb);
			}
			
			resoultSet.next();
			
			
			resoultSet.relative(1);resoultSet.previous();
			resoultSet.absolute(1);
			resoultSet.deleteRow();
			
			resoultSet.absolute(1);
			resoultSet.updateInt("id", 1);
			resoultSet.updateString("nazev","jkghsjh");
			long updateUnscaledVal = 123456789L;
			int updateScale = 2;
			resoultSet.updateBigDecimal("cena", BigDecimal.valueOf(updateUnscaledVal,updateScale));
			resoultSet.rowUpdated();
			
			resoultSet.moveToInsertRow();
			resoultSet.updateInt("id", 1);
			resoultSet.updateString("nazev","jkghsjh");
			long deleteUnscaledVal = 123456789L;
			int deleteScale = 2;
			resoultSet.updateBigDecimal("cena", BigDecimal.valueOf(deleteUnscaledVal,deleteScale));
			resoultSet.insertRow();
			
			resoultSet = statment.executeQuery(queryString);
			while (resoultSet.next())
			{
				StringBuilder sb = new StringBuilder();
				sb.append("id = ");
				sb.append(resoultSet.getInt("id"));
				sb.append("\t\tNazev = ");
				sb.append(resoultSet.getString("nazev"));
				sb.append("\t\tCena = ");
				sb.append(resoultSet.getBigDecimal("cena"));
				System.out.println(sb);
			}*/
			
			
			/*
			ResultSet resoultSet = statment.executeQuery("select distinct * from user3.user3");
			while (resoultSet.next())
			{
				StringBuilder sb = new StringBuilder();
				sb.append("id = ");
				sb.append(resoultSet.getInt("id"));
				sb.append("\t\tnbr = ");
				sb.append(resoultSet.getBigDecimal("nbr"));
				sb.append("\t\tdta = ");
				sb.append(resoultSet.getString("dta"));
				System.out.println(sb);
			}*/
			/*
			//statment.execute("create table "+DatabaseConfig.getTable()+" (id integer, nbr decimal (15,2), dta char (50) ccsid 1208)");
			statment.execute("insert into "+DatabaseConfig.getTable()+" (id, nbr, dta) values (1, 500, 'A')");
			statment.execute("insert into "+DatabaseConfig.getTable()+" (id, nbr, dta) values (2, 400, 'B')");
			statment.execute("insert into "+DatabaseConfig.getTable()+" (id, nbr, dta) values (3, 300, 'C')");
			statment.execute("insert into "+DatabaseConfig.getTable()+" (id, nbr, dta) values (4, 200, 'D')");
			statment.execute("insert into "+DatabaseConfig.getTable()+" (id, nbr, dta) values (5, 100, 'E')");
			*/
			connection.close();
		}catch(SQLException sqlException)
		{
			int errCode = sqlException.getErrorCode();
			String SQLState = sqlException.getSQLState();
			System.out.println("ErrorCode: " + Integer.toString(errCode) +"SQLState: "+ SQLState);
		}catch(Exception exception) {
			System.out.println(exception);
		}
		
		LOGGER.debug("*** END ***");
	}

}
