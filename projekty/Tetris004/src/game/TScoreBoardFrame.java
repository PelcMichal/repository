package game;

import java.awt.Graphics;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

public class TScoreBoardFrame extends JPanel{
	private static final long serialVersionUID = 1L;
	public static final Logger LOGGER = Logger.getLogger(TScoreBoardFrame.class);
	
	public TScoreBoardFrame()
	{
		super();
		setVisible(true);
	}

	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		setSize(300, 500);
		
		g.drawString("Place", 0, 20);g.drawString("Name", 40, 20);g.drawString("Score", 100, 20);g.drawString("Time", 160, 20);
		setLocation(0, 0);
		
		
		try (
				Connection c = DriverManager.getConnection("jdbc:derby:testdb1;create=true");
				ResultSet rs = c.createStatement().executeQuery("SELECT  name,score,time FROM high_score WHERE hight = " + Main.s.getHight() + " AND width = " + Main.s.getWith() + " ORDER BY score DESC");
			)
		{
			int place = 1;
			while(rs.next())
			{
				g.drawString(place+"", 0, 20+(place*20));
				g.drawString(rs.getString(1), 40, 20+(place*20));
				g.drawString(rs.getLong(2)+"", 100, 20+(place*20));
				g.drawString(rs.getTimestamp(3).toString(), 160, 20+(place*20));
				place++;
			}
			
			
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		
		
		
		
	}
}
