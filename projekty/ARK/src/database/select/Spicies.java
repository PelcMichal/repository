package database.select;

import org.apache.log4j.Logger;

public class Spicies {
	public static final Logger LOGGER = Logger.getLogger(Spicies.class);

	private Spicies() {}


	public static String selectLike(String like, Boolean tamable, Boolean rideable, Boolean breedable)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT id,Name FROM spicies where lower(name) LIKE '%");
		sb.append(like);
		sb.append("%'");

		if(tamable!=null)
		{
			if(tamable.equals(true))
			{
				sb.append(" and Tamable = true");
			}
			else
			{
				sb.append(" and Tamable = false");
			}
		}
		if(rideable!=null)
		{
			if(rideable.equals(true))
			{
				sb.append(" and Rideable = true");
			}
			else
			{
				sb.append(" and Rideable = false");
			}
		}
		if(breedable!=null)
		{
			if(breedable.equals(true))
			{
				sb.append(" and Breedable = true");
			}
			else
			{
				sb.append(" and Breedable = false");
			}
		}
		sb.append(" order by name");
		LOGGER.info(sb.toString());
		return sb.toString();
	}
	public static String selectNamesFromID(Integer[] ids)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT id,Name FROM spicies WHERE (");

		if(ids == null)
		{
			sb.append("id = cast(null as int)");
		}
		else
		{
			if(ids.length>0)
			{
				sb.append("( id = ");
				sb.append(ids[0]);
				for(int i = 1;i<ids.length;i++)
				{
					sb.append(" OR id = ");
					sb.append(ids[i]);
				}
				sb.append(")");
			}
			else
			{
				sb.append("id = cast(null as int)");
			}
		}
		sb.append(")");
		LOGGER.info(sb.toString());
		return sb.toString();
	}
	public static String selectAllForId(Integer id)
	{
		String s = "SELECT id,Name,Health_alias,Stamina_alias,Oxygen_alias,Food_alias,Weight_alias,MeleeDamage_alias FROM spicies WHERE id = "+id;
		LOGGER.info(s);
		return s;
	}



}
