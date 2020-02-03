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

public class Spicies extends JPanel{
	private static final long serialVersionUID = 1L;
	public static final Logger LOGGER = Logger.getLogger(Spicies.class);
	private static final Spicies INSTANCE = new Spicies();public static Spicies getSpicies() {return INSTANCE;}
	private DefaultListModel<String> dl = new DefaultListModel<>();
	private JList<String> jList = new JList<>();
	private ArrayList<Integer> id = new ArrayList<>(300);

	private Spicies() {
		super();
		jList.setFixedCellWidth(200);
		updateList("",null,null,null);
		jList.setModel(dl);
		add(jList);
		setBackground(Color.CYAN);
		jList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				frame.panel.main.Down.getDown().setSpicies(jList.getSelectedValue());
				frame.panel.main.right.dino.General.getGeneral().updateServerSpicies();
				frame.panel.main.Center.getCenter().updateCenter();
				frame.panel.main.center.Dino.getDino().eanble();
				if(jList.getSelectedIndices().length==1)
					frame.panel.main.right.Dino.getDino().setStatNames(id.get(jList.getSelectedIndex()));
			}});
	}
	
	public void updateList(String like, Boolean tamable, Boolean rideable, Boolean breedable)
	{
		id.clear();
		dl.clear();
		try (ResultSet trs = DriverManager.getConnection(init.Main.CONN).createStatement().executeQuery(database.select.Spicies.selectLike(like.toLowerCase(),tamable,rideable,breedable));
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
	public int selected()
	{
		return jList.getSelectedIndices().length;
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
