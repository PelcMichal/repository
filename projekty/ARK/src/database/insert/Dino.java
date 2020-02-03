package database.insert;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class Dino {
public static final Logger LOGGER = Logger.getLogger(Dino.class);
	
	private Dino() {}
	 /**
     * @deprecated
     * only for testing data
     */
	@Deprecated
	public static void insertDino(String name, Boolean gender,boolean isAlive ,int lvl, int idServer, int idSpicies, int health, int stamina, int oxygen, int food, int weight, int meleeDamage)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO dino(name,gender,is_alive,Lvl,id_server,id_spicies,	Health,Stamina,Oxygen,Food,Weight,MeleeDamage) VALUES('");
		sb.append(name);
		sb.append("',");
		if(gender == null)
		{
			sb.append("null");
		}
		else
		{
			if(gender){sb.append("true");}
			else{sb.append("false");}
		}
		sb.append(",");
		if(isAlive){sb.append("true");}
		else{sb.append("false");}
		sb.append(",");
		sb.append(lvl);
		sb.append(",");
		sb.append(idServer);
		sb.append(",");
		sb.append(idSpicies);
		sb.append(",");
		sb.append(health);
		sb.append(",");
		sb.append(stamina);
		sb.append(",");
		sb.append(oxygen);
		sb.append(",");
		sb.append(food);
		sb.append(",");
		sb.append(weight);
		sb.append(",");
		sb.append(meleeDamage);
		sb.append(")");
		LOGGER.info(sb.toString());
		try( Statement st = DriverManager.getConnection(init.Main.CONN).createStatement())
		{
			st.execute(sb.toString());
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}
	public static void insertDino(String general, String health, String stamina, String oxygen, String food, String weight, String meleeDamage)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO dino(name,Lvl,gender,is_alive,id_spicies,id_server,id_female,id_male,Color_mutations,	Health,Health_mutations,Health_from_Gender,Stamina,Stamina_mutations,Stamina_from_Gender,Oxygen,Oxygen_mutations,Oxygen_from_Gender,Food,Food_mutations,Food_from_Gender,Weight,Weight_mutations,Weight_from_Gender,MeleeDamage,MeleeDamage_mutations,MeleeDamage_from_Gender) VALUES(");
		sb.append(general);
		sb.append(",");
		sb.append(health);
		sb.append(",");
		sb.append(stamina);
		sb.append(",");
		sb.append(oxygen);
		sb.append(",");
		sb.append(food);
		sb.append(",");
		sb.append(weight);
		sb.append(",");
		sb.append(meleeDamage);
		sb.append(")");
		LOGGER.info(sb.toString());
		try( Statement st = DriverManager.getConnection(init.Main.CONN).createStatement())
		{
			st.execute(sb.toString());
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}
}
