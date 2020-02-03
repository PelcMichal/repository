package init;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main {

	public static final Logger LOGGER = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		PropertyConfigurator.configure(".\\config\\log.properties");
		LOGGER.debug("***BIGIN***");
		boolean temp = false;
		try {
			DriverManager.getConnection(CONN);
			temp = true;
		} catch (SQLException e) {
			LOGGER.debug("Probebly allredy running");
			LOGGER.info(e);
		}
		if (temp) 
		{
	/*		database.Init.drop();
			database.Init.tryToCreate();
			database.insert.Server.insertServer("nevim");
			database.insert.Server.insertServer("blabla");
			
			database.insert.Dino.insertDino("A",null,true,100,1,128,100,100,100,100,100,100);
			database.insert.Dino.insertDino("D",null,false,100,2,128,100,100,100,100,100,100);
			database.insert.Dino.insertDino("M",true,true,100,1,128,100,100,100,100,100,100);
			*/
			
			vypis();
			/*
			for(int i = 60102; i<100000;i++)
			{
				database.insert.Dino.insertDino("test"+i,false,false,100,1,128,1000,1000,1000,1000,1000,1000);
				if (i%100 ==0)
					System.out.println(i);
			}*//*
			try( Statement st = DriverManager.getConnection(init.Main.CONN).createStatement())
			{
				System.out.println("A");
				st.execute("DELETE FROM dino WHERE name LIKE 'test%'");System.out.println("A");
			} catch (SQLException e) {
				LOGGER.error(e);
			}*/
			
			//TODO add server spicies
			frame.MainFrame.getMainFrame();
		}
	}
	public static final String  CONN= "jdbc:derby:testdb1;create=true";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void vypis()
	{
		try (
				ResultSet rsSp = DriverManager.getConnection(CONN).createStatement().executeQuery("SELECT id,Name,Tamable,Rideable,Breedable,Health_alias,Stamina_alias,Oxygen_alias,Food_alias,Weight_alias,MeleeDamage_alias FROM spicies WHERE name LIKE '%%' order by name");
				ResultSet rsSe = DriverManager.getConnection(CONN).createStatement().executeQuery("SELECT id,Name FROM server");
				ResultSet rsdI = DriverManager.getConnection(CONN).createStatement().executeQuery("SELECT id,Name,is_alive,Gender,Lvl,Health,Stamina,Oxygen,Food,Weight,MeleeDamage,id_server,id_spicies FROM dino order by name ");
				){
			
			while(rsSp.next())
			{
				StringBuilder sb = new StringBuilder();
				//System.out.println(rsSp.getInt(1)+"\t"+rsSp.getString(2)+"\t"+rsSp.getBoolean(3)+"\t"+rsSp.getBoolean(4)+"\t"+rsSp.getBoolean(5));
				sb.append(rsSp.getBoolean(3));
				sb.append(",");
				sb.append(rsSp.getBoolean(4));
				sb.append(",");
				sb.append(rsSp.getBoolean(5));
				sb.append(",'");
				sb.append(rsSp.getString(2).trim());
				for(int i = 6;i<12;i++)
				{
					sb.append("','");
					sb.append(rsSp.getString(i));
				}
				sb.append("'");
				System.out.println(sb.toString());
			}
			
			while(rsSe.next())
			{
				System.out.println(rsSe.getInt(1)+"\t"+rsSe.getString(2));
			}
			System.out.println("\n");
			StringBuilder sb;
			boolean t;
			while(rsdI.next())
			{
				sb = new StringBuilder();
				sb.append(rsdI.getInt(1)+"\t");
				sb.append(rsdI.getString(2)+"\t");
				if(rsdI.getBoolean(3)) {sb.append("T\t");}else{sb.append("F\t");}
				t = rsdI.getBoolean(4); 
				if (rsdI.wasNull()) 
				{
					sb.append("N\t");
				}
				else
				{
					if(t)
					{
						sb.append("M\t");
					}
					else
					{
						sb.append("F\t");
					}
				}
				sb.append(rsdI.getInt(5)+"\t");
				sb.append(rsdI.getInt(6)+"\t");
				sb.append(rsdI.getInt(7)+"\t");
				sb.append(rsdI.getInt(8)+"\t");
				sb.append(rsdI.getInt(9)+"\t");
				sb.append(rsdI.getInt(10)+"\t");
				sb.append(rsdI.getInt(11)+"\t");
				sb.append(rsdI.getInt(12)+"\t");
				sb.append(rsdI.getInt(13));
				System.out.println(sb);
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}
}
