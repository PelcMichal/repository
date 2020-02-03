package database.select;

import org.apache.log4j.Logger;

public class Server {
	public static final Logger LOGGER = Logger.getLogger(Server.class);
	private Server(){}
	public static String all()
	{
		String s = "SELECT id,Name FROM server";
		LOGGER.info(s);
		return s;
	}
	public static String selectNamesFromID(Integer[] ids) {

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT id,Name FROM server WHERE (");
		
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
	public static String like(String like)
	{
		String s = "SELECT id,Name FROM server WHERE lower(name) LIKE '%"+like+"%'";
		LOGGER.info(s);
		return s;
	}
	
	
	
	
	
	
}
