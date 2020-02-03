package frame.panel.main.center;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger;


public class Dino extends JTable {
	private static final long serialVersionUID = 1L;
	public static final Logger LOGGER = Logger.getLogger(Dino.class);
	private static final Dino INSTANCE = new Dino();public static Dino getDino() {return INSTANCE;}

	private ArrayList<Integer> id = new ArrayList<>(100);


	private DinoModel dtm = new DinoModel();
	public Dino() {
		super();
		String[] columnNames = {"Name", "Lives", "Gender","Level","Health","Stamina","Oxygen","Food","Weight","MeleeDamage"}; 
		setModel(dtm);
		dtm.setColumnIdentifiers(columnNames);
		this.setDefaultRenderer(getColumnClass(0),new DinoRenderer());
		setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS);
		getColumnModel().getColumn(0).setPreferredWidth(200);
		getColumnModel().getColumn(1).setPreferredWidth(10);
		getColumnModel().getColumn(2).setPreferredWidth(10);
		getColumnModel().getColumn(3).setPreferredWidth(30);

		getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {

				if(getSelectedRow()>-1)
				{
					frame.panel.main.right.Dino.update(id.get(getSelectedRow()));
				}
			}
		});

	}
	public void update(String sql) 
	{
		LOGGER.info(sql);
		String[] tempRow = new String[10];
		boolean t;
		dtm.setRowCount(0);
		id.clear();
		tempRow[1] = "t";
		tempRow[2] = "m";
		try (ResultSet trs = DriverManager.getConnection("jdbc:derby:testdb1;create=true").createStatement().executeQuery(sql);
				){
			while(trs.next())
			{
				id.add(trs.getInt(1));
				tempRow[0] = trs.getString(2);
				if(trs.getBoolean(3)) {tempRow[1] = "T";}else{tempRow[1] = "F";}

				t = trs.getBoolean(4); 
				if (trs.wasNull()) 
				{
					tempRow[2] = "NA";
				}
				else
				{
					if(t)
					{
						tempRow[2] = "M";
					}
					else
					{
						tempRow[2] = "F";
					}
				}
				tempRow[3] = trs.getInt(5)+"";
				tempRow[4] = trs.getInt(6)+"";
				tempRow[5] = trs.getInt(7)+"";
				tempRow[6] = trs.getInt(8)+"";
				tempRow[7] = trs.getInt(9)+"";
				tempRow[8] = trs.getInt(10)+"";
				tempRow[9] = trs.getInt(11)+"";

				dtm.addRow(tempRow);
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}
	public void eanble()
	{
		if (frame.panel.main.left.Spicies.getSpicies().selected() == 1)
		{
			int[] t = new int[6];
			try (ResultSet rs = DriverManager.getConnection(init.Main.CONN).createStatement().executeQuery("SELECT MAX(Health),MAX(Stamina),MAX(Oxygen),MAX(Food),MAX(Weight),MAX(MeleeDamage) FROM dino WHERE id_spicies = "+frame.panel.main.left.Spicies.getSpicies().getIds()[0]);)
			{
				rs.next();
				for(int i = 0;i<6;i++)
				{
					t[i] = rs.getInt(i+1);
				}
			} catch (SQLException e1) {
				LOGGER.error(e1);
			}
			frame.panel.main.center.Dino.getDino().eanble(t);
		}
		else
		{
			frame.panel.main.center.Dino.getDino().diseanble();
		}
	}
	public void eanble(int[] maxValues)
	{
		dtm.eanble(maxValues);
	}
	public void diseanble() {dtm.diseanble();}
}
