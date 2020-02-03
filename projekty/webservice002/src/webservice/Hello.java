package webservice;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.GET;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@Path("/hello")
public class Hello {
	Logger LOG = Logger.getLogger(Hello.class);
	
	private static Connection conn = null;
	private static Connection GetConn() throws SQLException
	{
		if(conn==null)
		{
			conn=DriverManager.getConnection("jdbc:postgresql://localhost/test","postgres","admin");
		}
		if(conn.isClosed())
		{
			conn=DriverManager.getConnection("jdbc:postgresql://localhost/test","postgres","admin");
		}
		return conn;
	}
	
	
	/*
	@GET()
	@Produces(MediaType.TEXT_XML)
	public String sayHelloXML()
	{
		String resource = "<? xml version='1.0' ?>"+
					"<hello> HI xml</hello>";
		return resource;
	}
	*/
	@GET()
	@Produces(MediaType.TEXT_XML)
	public String saySoucetXML(@QueryParam("cislo1")int cislo1,@QueryParam("cislo2")int cislo2)
	{
		StringBuilder sb = new StringBuilder("<? xml version='1.0' ?>");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			LOG.error(e);
		}/*
		try (Statement st =DriverManager.getConnection("jdbc:postgresql://localhost:5432/","postgres","admin").createStatement();) {
			st.execute("Create Database test");
		} catch (SQLException e) {
			sb.append("<ERROR>"+e+"</ERROR>");
		}*/
		try (Statement st =GetConn().createStatement();) {
			//st.execute("drop table conections;");
			st.execute("Create Table con(Id INT NOT NULL GENERATED ALWAYS AS IDENTITY,cislo1 int not null,cislo2 int not null,time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY (Id));");
		}catch (SQLException e) {
			LOG.error(e);
		}
		try (Statement st =GetConn().createStatement();) {
			st.execute("insert into con(cislo1,cislo2) values("+cislo1+","+cislo2+");");
		}catch (SQLException e) {
			LOG.error(e);
		}
		
		try (ResultSet rs =GetConn().createStatement().executeQuery("select cislo1,cislo2,cislo1+cislo2,time from con order by time;");) {
				while(rs.next())
				{
					sb.append("<cislo1>");
					sb.append(rs.getInt(1));
					sb.append("</cislo1>");
					sb.append("<cislo2>");
					sb.append(rs.getInt(2));
					sb.append("</cislo2>");
					sb.append("<soucet>");
					sb.append(rs.getInt(3));
					sb.append("</soucet>");
					sb.append("<time>");
					sb.append(rs.getTimestamp(4));
					sb.append("</time>");
				}
		} catch (SQLException e) {
			LOG.error(e);
		}
		
		
		return sb.toString();
	}
	/*
	@GET()
	@Produces(MediaType.TEXT_HTML)
	public String saySoucetHTML(@QueryParam("cislo1")int cislo1,@QueryParam("cislo2")int cislo2)
	{
		String resource ="<h1>"+(cislo1+cislo2)+"</h1>";
		return resource;
	}*/
	
	/*
	@GET()
	@Produces(MediaType.APPLICATION_JSON)
	public String sayHelloJSON()
	{
		String resource = null;
		return resource;
	}
	
	@GET()
	@Produces(MediaType.TEXT_HTML)
	public String sayHelloHTML()
	{
		String resource = "<h1> HI html</h1>";
		return resource;
	}*/
}
