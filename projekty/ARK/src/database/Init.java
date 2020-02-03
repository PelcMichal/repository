package database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import database.insert.Spicies;

public class Init {
	public static final Logger LOGGER = Logger.getLogger(Init.class);
	
	private static final String CREATESERVER = "CREATE TABLE server(Id INT NOT NULL GENERATED ALWAYS AS IDENTITY,Name CHAR(50) NOT NuLL,PRIMARY KEY (Id))";
	private static final String CREATESPICIES ="CREATE TABLE spicies(Id INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
			+ "Name CHAR(30) NOT NuLL UNIQUE,"
			//+ "Name_lower CHAR(50) NOT NuLL DEFAULT lower(Name),"
			+ "Tamable BOOLEAN DEFAULT false,"
			+ "Rideable BOOLEAN DEFAULT false,"
			+ "Breedable BOOLEAN DEFAULT false,"
			
			+"Health_alias VARCHAR(30) NOT NULL DEFAULT 'Health',"
			+"Stamina_alias VARCHAR(30) NOT NULL DEFAULT 'Stamina',"
			+"Oxygen_alias VARCHAR(30) NOT NULL DEFAULT 'Oxygen',"
			+"Food_alias VARCHAR(30) NOT NULL DEFAULT 'Food',"
			+"Weight_alias VARCHAR(30) NOT NULL DEFAULT 'Weight',"
			+"MeleeDamage_alias VARCHAR(30) NOT NULL DEFAULT 'Melee Damage',"
			
			+ "PRIMARY KEY (Id))";
	private static final String CREATEDINO = "CREATE TABLE dino(Id INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
				+"id_server int references server(id) NOT NULL,"
				+"id_spicies int references spicies(id) NOT NULL,"
				+"id_female int references dino(id),"
				+"id_male int references dino(id),"
				
				+"Name VARCHAR(50) NOT NULL,"
				+"Gender BOOLEAN DEFAULT NULL,"
				+"Lvl INT NOT NULL,"
				+"is_alive BOOLEAN DEFAULT true,"
				
				+"Health BIGINT NOT NULL,"
				+"Health_mutations SMALLINT NOT NULL DEFAULT 0,"
				+"Health_from_Gender BOOLEAN DEFAULT null,"

				+"Stamina BIGINT NOT NULL,"
				+"Stamina_mutations SMALLINT NOT NULL DEFAULT 0,"
				+"Stamina_from_Gender BOOLEAN DEFAULT null,"

				+"Oxygen BIGINT NOT NULL,"
				+"Oxygen_mutations SMALLINT NOT NULL DEFAULT 0,"
				+"Oxygen_from_Gender BOOLEAN DEFAULT null,"
				
				+"Food BIGINT NOT NULL,"
				+"Food_mutations SMALLINT NOT NULL DEFAULT 0,"
				+"Food_from_Gender BOOLEAN DEFAULT null,"
				
				+"Weight BIGINT NOT NULL,"
				+"Weight_mutations SMALLINT NOT NULL DEFAULT 0,"
				+"Weight_from_Gender BOOLEAN DEFAULT null,"

				+"MeleeDamage BIGINT NOT NULL,"
				+"MeleeDamage_mutations SMALLINT NOT NULL DEFAULT 0,"
				+"MeleeDamage_from_Gender BOOLEAN DEFAULT null,"
				
				+"Color_mutations SMALLINT NOT NULL DEFAULT 0,"

				+"PRIMARY KEY (Id))";
	
	
	private Init() {}
	public static void drop()
	{
		try (Statement st = DriverManager.getConnection("jdbc:derby:testdb1;create=true").createStatement();)
		{
			st.execute("DROP TABLE dino");
			st.execute("DROP TABLE server");
			st.execute("DROP TABLE spicies");
		}catch (SQLException e) 
		{
			LOGGER.error(e);
		}
	}
 	public static void tryToCreate()
	{
		try (Statement st = DriverManager.getConnection("jdbc:derby:testdb1;create=true").createStatement();)
		{
			tryToCreateServer(st);
			tryToCreateSpicies(st);
			tryToCreateDino(st);
		} catch (SQLException e) 
		{
			LOGGER.error(e);
		}
	}
	private static void tryToCreateServer(Statement st)
	{
			try {
				st.execute(CREATESERVER);
			}catch(SQLException e)
			{
				LOGGER.error(e);
				LOGGER.debug("Table server alredy created");
			}
	}
	private static void tryToCreateSpicies(Statement st)
	{
		try {
			st.execute(CREATESPICIES);
			Spicies.insertSpicies();
		}catch(SQLException e)
		{
			LOGGER.error(e);
			LOGGER.debug("Table spicies alredy created");
		}
	}
	private static void tryToCreateDino(Statement st)
	{
		try {
			st.execute(CREATEDINO);
		}catch(SQLException e)
		{
			LOGGER.error(e);
			LOGGER.debug("Table dino alredy created");
		}
	}
}
