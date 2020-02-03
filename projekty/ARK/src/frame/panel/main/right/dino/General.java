package frame.panel.main.right.dino;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import frame.panel.main.right.WrongDataException;

public class General extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final Logger LOGGER = Logger.getLogger(General.class);
	private static final General INSTANCE = new General();public static General getGeneral() {return INSTANCE;}
	
	private JTextField name;
	private JComboBox<String> female;
	private ArrayList<Integer> idFemale = new ArrayList<>(50);
	private JComboBox<String> male;
	private ArrayList<Integer> idMale = new ArrayList<>(50);
	private JComboBox<String> spicies;
	private Integer[] idSpicies = new Integer[0];
	private JComboBox<String> server;
	private Integer[] idServer = new Integer[0];
	private JTextField lvl;
	private JButton gender;
	private JButton alive;
	private JTextField mut;
	private static final String W = "Wrong data in part General";
	private static final String INTNULL ="cast(null as int)";
	private static final String T="true";
	private static final String F="false";
	private static final String N="null";
	
	public General() {
		super();
		
		name = new JTextField(18);
		
		String[] a  = {"Select spicies"};//
		
		spicies = new JComboBox<>(a);
		spicies.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent arg0) {
	        	if (spicies.getSelectedIndex()!=-1)
	    		{
	        		updateAncestors(idSpicies[spicies.getSelectedIndex()], idServer);
	        		frame.panel.main.right.Dino.getDino().setStatNames(idSpicies[spicies.getSelectedIndex()]);
	    		}
	        }
	    });
		a[0] = "Select server";
		server = new JComboBox<>(a);
		spicies.setPreferredSize(new Dimension(235,20));
		server.setPreferredSize(new Dimension(235,20));
		a[0] = "Wild";
		female = new JComboBox<>(a);
		female.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent arg0) {
	        	if(!idFemale.isEmpty()&&female.getSelectedIndex()!=-1)
	        		frame.panel.main.right.Dino.updateF(idFemale.get(female.getSelectedIndex()));
	        }
	    });
		female.setPreferredSize(new Dimension(235,20));
		
		male = new JComboBox<>(a);
		male.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent arg0) {
	        	if(!idMale.isEmpty()&&male.getSelectedIndex()!=-1)
	        		frame.panel.main.right.Dino.updateM(idMale.get(male.getSelectedIndex()));
	        }
	    });
		male.setPreferredSize(new Dimension(235,20));
		
		lvl = new JTextField(4);
		
		gender = new JButton();
		gender.setBackground(Color.GRAY);
		gender.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (gender.getBackground()==Color.GRAY)
				{gender.setBackground(Color.MAGENTA);}
				else if(gender.getBackground()==Color.MAGENTA)
				{gender.setBackground(Color.BLUE);}
				else{gender.setBackground(Color.GRAY);}
			}});
		gender.setPreferredSize(new Dimension(20,20));
		
		alive = new JButton();
		alive.setBackground(Color.GREEN);
		alive.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (alive.getBackground()==Color.GREEN)
				{alive.setBackground(Color.RED);}
				else{alive.setBackground(Color.GREEN);}
			}});
		alive.setPreferredSize(new Dimension(20,20));
		
		setPreferredSize(new Dimension(245,220));
		setBackground(Color.GREEN);
		mut = new JTextField(4);
		add(new JLabel("Name"));
		add(name);
		add(new JLabel("Lvl"));
		add(lvl);
		add(new JLabel("Gender"));
		add(gender);
		add(new JLabel("Lives"));
		add(alive);
		add(server);
		add(spicies);
		
		add(new JLabel("Name of female"));
		add(female);
		add(new JLabel("Name of male"));
		add(male);
		add(new JLabel("Color mutaions"));
		add(mut);
	}
	public void update(String name,int lvl,Boolean gender,boolean lives,Integer idOfSpicies,Integer idOfServer,Integer idOfFemale,Integer idOfMale,int colorMutations)
	{
		this.name.setText(name);
		this.lvl.setText(lvl+"");
		if(gender == null)
		{
			this.gender.setBackground(Color.GRAY);
		}
		else
		{
			if(gender)
			{
				this.gender.setBackground(Color.BLUE);
			}
			else
			{
				this.gender.setBackground(Color.MAGENTA);
			}
		}
		if(lives)
		{
			alive.setBackground(Color.GREEN);
		}
		else
		{
			alive.setBackground(Color.RED);
		}
		updateServerSpicies();
		
		for(int i =0 ;i<idSpicies.length;i++)
		{
			if (idSpicies[i].equals(idOfSpicies))
			{
				spicies.setSelectedIndex(i);
			}
		}
		for(int i =0 ;i<idServer.length;i++)
		{
			if (idServer[i].equals(idOfServer))
			{
				server.setSelectedIndex(i);
			}
		}
		if (spicies.getSelectedIndex()>-1)
			updateAncestors(idSpicies[spicies.getSelectedIndex()], idServer);
		male.setSelectedIndex(idMale.indexOf(idOfMale));
		female.setSelectedIndex(idFemale.indexOf(idOfFemale));
		mut.setText(colorMutations+"");
	}
	public void updateAncestors(int id_spicies, Integer[] id_server)
	{
		if(spicies.getSelectedItem() != "Select spicies"&&server.getSelectedItem()!="Select server")
		{
			idFemale.clear();
			idFemale.add(null);
			female.removeAllItems();
			female.addItem("Wild");
			try (ResultSet trs = DriverManager.getConnection(init.Main.CONN).createStatement().executeQuery(database.select.Dino.selectAncester(id_spicies, id_server, true));
				){
				while(trs.next())
				{
					idFemale.add(trs.getInt(1));
					female.addItem(trs.getString(2));
				}
			} catch (SQLException e) {
			LOGGER.error(e);
			}
			idMale.clear();
			idMale.add(null);
			male.removeAllItems();
			male.addItem("Wild");
			try (ResultSet trs = DriverManager.getConnection(init.Main.CONN).createStatement().executeQuery(database.select.Dino.selectAncester(id_spicies, id_server, false));
				){
				while(trs.next())
				{
					idMale.add(trs.getInt(1));
					male.addItem(trs.getString(2));
				}
			} catch (SQLException e) {
				LOGGER.error(e);
			}
		}
	}
	public void updateServerSpicies()
	{
		
		
		try (ResultSet trs = DriverManager.getConnection(init.Main.CONN).createStatement().executeQuery(database.select.Server.selectNamesFromID(frame.panel.main.left.Server.getServer().getIds()));
				){
					if(trs.next())
					{
						this.idServer = frame.panel.main.left.Server.getServer().getIds();
						server.removeAllItems();
						server.addItem(trs.getString(2));
						while(trs.next())
						{
							server.addItem(trs.getString(2));
						}
					}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		
		
		try (ResultSet trs = DriverManager.getConnection(init.Main.CONN).createStatement().executeQuery(database.select.Spicies.selectNamesFromID(frame.panel.main.left.Spicies.getSpicies().getIds()));
				){
					if(trs.next())
					{
						this.idSpicies = frame.panel.main.left.Spicies.getSpicies().getIds();
						spicies.removeAllItems();
						spicies.addItem(trs.getString(2));
						while(trs.next())
						{
							spicies.addItem(trs.getString(2));
						}
				}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}
	public String getInsertPart() throws WrongDataException
	{
		if (name.getText() ==null||name.getText().isEmpty()||
			lvl.getText()==null||lvl.getText().isEmpty()||
			spicies.getSelectedIndex()<-1||server.getSelectedIndex()<-1)
			throw new WrongDataException(W);
		if(mut.getText()==null||mut.getText().isEmpty())
			mut.setText("0");
		try {Integer.valueOf(lvl.getText());Byte.valueOf(mut.getText());}
		catch(NumberFormatException e) {throw new WrongDataException(W);}
		
		StringBuilder sb = new StringBuilder();
		sb.append("'");
		sb.append(name.getText());
		sb.append("',");
		sb.append(Integer.valueOf(lvl.getText()));
		sb.append(",");
		if(gender.getBackground()==Color.GRAY)
		{sb.append(N);}
		else if(gender.getBackground()==Color.BLUE) 
		{sb.append(T);}
		else {sb.append(F);}
		sb.append(",");
		if(alive.getBackground()==Color.GREEN)
		{sb.append(T);}
		else{sb.append(F);}
		sb.append(",");
		sb.append(idSpicies[spicies.getSelectedIndex()]);
		sb.append(",");
		sb.append(idServer[server.getSelectedIndex()]);
		sb.append(",");
		if(female.getSelectedIndex() == -1||idFemale.get(female.getSelectedIndex()) == null)
		{sb.append(INTNULL);}
		else {sb.append(idFemale.get(female.getSelectedIndex()));}
		sb.append(",");
		if(male.getSelectedIndex() == -1||idMale.get(male.getSelectedIndex()) == null)
		{sb.append(INTNULL);}
		else {sb.append(idMale.get(male.getSelectedIndex()));}
		sb.append(",");
		sb.append(Byte.valueOf(mut.getText()));
		return sb.toString();
	}
	public String getUpdatePart() throws WrongDataException
	{
		if (name.getText() ==null||name.getText().isEmpty()||
				lvl.getText()==null||lvl.getText().isEmpty())
			throw new WrongDataException(W);
		if(mut.getText()==null||mut.getText().isEmpty())
			mut.setText("0");
		try {Integer.valueOf(lvl.getText());Byte.valueOf(mut.getText());}
		catch(NumberFormatException e) {throw new WrongDataException(W);}
		
		StringBuilder sb = new StringBuilder();
		sb.append(" Name = '");
		sb.append(name.getText());
		sb.append("' ,lvl  = ");
		sb.append(Integer.valueOf(lvl.getText()));
		sb.append(" ,Gender = ");
		if(gender.getBackground()==Color.GRAY)
		{sb.append(N);}
		else if(gender.getBackground()==Color.BLUE) 
		{sb.append(T);}
		else {sb.append(F);}
		sb.append(" ,is_alive = ");
		if(alive.getBackground()==Color.GREEN)
		{sb.append(T);}
		else{sb.append(F);}
		sb.append(" ,id_spicies = ");
		sb.append(idSpicies[spicies.getSelectedIndex()]);
		sb.append(" ,id_server = ");
		sb.append(idServer[server.getSelectedIndex()]);
		
		sb.append(" ,id_female = ");
		if(female.getSelectedIndex() == -1||idFemale.get(female.getSelectedIndex()) == null)
		{sb.append(INTNULL);}
		else {sb.append(idFemale.get(female.getSelectedIndex()));}
		sb.append(" ,id_male = ");
		if(male.getSelectedIndex() == -1||idMale.get(male.getSelectedIndex()) == null)
		{sb.append(INTNULL);}
		else {sb.append(idMale.get(male.getSelectedIndex()));}
		
		sb.append(" ,Color_mutations = ");
		sb.append(Byte.valueOf(mut.getText()));
		return sb.toString();
	}
	
	
}
