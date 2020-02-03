package database.select;

import org.apache.log4j.Logger;

public class Dino {
	public static final Logger LOGGER = Logger.getLogger(Dino.class);
	private Dino() {}
	public static String selectOne(int id)
	{
		//				   1   2      3      4      5    6          7                8              9          10                 11             12          13            14               15        16          17              18        19            20                 21             22                          23               24             25          26       27       28
		String s ="SELECT id,Name,is_alive,Gender,Lvl,Health,Health_mutations,Health_from_Gender,Stamina,Stamina_mutations,Stamina_from_Gender,Oxygen,Oxygen_mutations,Oxygen_from_Gender,Food,Food_mutations,Food_from_Gender,Weight,Weight_mutations,Weight_from_Gender,MeleeDamage,MeleeDamage_mutations,MeleeDamage_from_Gender,Color_mutations,id_server,id_spicies,id_female,id_male FROM dino WHERE id = "+id;
		LOGGER.info(s);
		return s;
	}
	public static String selectAncester(Integer id_spicies,Integer[] id_server, Boolean isFemale)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT id,Name FROM dino WHERE (");
		
		if(id_spicies == null)
		{
			sb.append("id_spicies = cast(null as int)");
		}
		else
		{
			sb.append("id_spicies = ");
			sb.append(id_spicies);
		}
		sb.append(" AND ");
		if(id_server == null)
		{
			sb.append("id_server = cast(null as int)");
		}
		else
		{
			if(id_server.length>0)
			{
				sb.append("( id_server = ");
				sb.append(id_server[0]);
				for(int i = 1;i<id_server.length;i++)
				{
					sb.append(" OR id_server = ");
					sb.append(id_server[i]);
				}
				sb.append(")");
			}
		}
		if (isFemale !=null)
		{
			if(isFemale) {sb.append(" AND Gender = false");}else{sb.append(" AND Gender = true");}
		}
		sb.append(") ORDER BY name");
		LOGGER.info(sb.toString());
		return sb.toString();
	}
	public static String selectWhere(Integer[] id_spicies,Integer[] id_server,String like)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT id,Name,is_alive,gender,Lvl,Health,Stamina,Oxygen,Food,Weight,MeleeDamage FROM dino WHERE (");
		
		
		if(id_spicies == null)
		{
			sb.append("id_spicies = cast(null as int)");
		}
		else
		{
			if(id_spicies.length>0)
			{
				sb.append("( id_spicies = ");
				sb.append(id_spicies[0]);
				for(int i = 1;i<id_spicies.length;i++)
				{
					sb.append(" OR id_spicies = ");
					sb.append(id_spicies[i]);
				}
				sb.append(")");
			}
		}
		sb.append(" AND ");
		if(id_server == null)
		{
			sb.append("id_server = cast(null as int)");
		}
		else
		{
			if(id_server.length>0)
			{
				sb.append("( id_server = ");
				sb.append(id_server[0]);
				for(int i = 1;i<id_server.length;i++)
				{
					sb.append(" OR id_server = ");
					sb.append(id_server[i]);
				}
				sb.append(")");
			}
		}
		if(!like.isEmpty())
		{
			sb.append(" AND lower(name) Like '%");
			sb.append(like.toLowerCase());
			sb.append("%'");
		}
		sb.append(") ORDER BY");
		return sb.toString();
	}
}
