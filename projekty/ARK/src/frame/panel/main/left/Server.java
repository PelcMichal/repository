package frame.panel.main.left;

import java.awt.Color;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger;

public class Server extends JPanel{
	private static final long serialVersionUID = 1L;
	public static final Logger LOGGER = Logger.getLogger(Server.class);
	private static final Server INSTANCE = new Server();public static Server getServer() {return INSTANCE;}
	private DefaultListModel<String> dl = new DefaultListModel<>();
	private JList<String> jList = new JList<>();
	private ArrayList<Integer> id = new ArrayList<>(300);
	
	private Server() {
		super();
		jList.setFixedCellWidth(200);
		updateList();
		jList.setModel(dl);
		add(jList);
		setBackground(Color.CYAN);
		jList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				frame.panel.main.Center.getCenter().updateCenter();
				frame.panel.main.right.dino.General.getGeneral().updateServerSpicies();
			}});
	}
	public void updateList()
	{
		dl.clear();
		id.clear();
		try (ResultSet trs = DriverManager.getConnection(init.Main.CONN).createStatement().executeQuery(database.select.Server.all());
				){
			while(trs.next())
			{
				id.add(trs.getInt(1));
				dl.addElement(trs.getString(2));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}
	public void updateList(String like)
	{
		dl.clear();
		id.clear();
		try (ResultSet trs = DriverManager.getConnection(init.Main.CONN).createStatement().executeQuery(database.select.Server.like(like.toLowerCase()));
				){
			while(trs.next())
			{
				id.add(trs.getInt(1));
				dl.addElement(trs.getString(2));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}
	public Integer[] getIds() 
	{
		Integer[] temp = new Integer[jList.getSelectedIndices().length];
		for(int i=0;i<jList.getSelectedIndices().length;i++)
		{
			temp[i] = id.get(jList.getSelectedIndices()[i]);
		}
		return temp;
	}
}
