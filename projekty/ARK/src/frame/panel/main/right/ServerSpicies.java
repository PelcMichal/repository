package frame.panel.main.right;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import frame.panel.main.right.dino.Stats;

public class ServerSpicies extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static final ServerSpicies INSTANCE = new ServerSpicies();public static ServerSpicies getServerSpicies() {return INSTANCE;}
	public static final Logger LOGGER = Logger.getLogger(ServerSpicies.class);
	
	private JTextField nameServer;
	private JTextField nameSpicies;
	private JTextField[] aliasSpicies = new JTextField[6];
	private final String[] DVALUES = {"Health","Stamina","Oxygen","Oxygen","Food","Weight","Melee Damage"};
	/*private static final Stats HEALTH = new Stats("Health");public static Stats getHealth() {return HEALTH;}
	private static final Stats STAMINA = new Stats("Stamina");public static Stats getStamina() {return STAMINA;}
	private static final Stats OXYGEN = new Stats("Oxygen");public static Stats getOxygen() {return OXYGEN;}
	private static final Stats FOOD = new Stats("Food");public static Stats getFood() {return FOOD;}
	private static final Stats WEIGHT = new Stats("Weight");public static Stats getWeight() {return WEIGHT;}
	private static final Stats MELEEDAMAGE = new Stats("Melee Damage");public static Stats getMeleeDamage() {return MELEEDAMAGE;}*/
	
	private ServerSpicies() 
	{
		super();
		
		
		
		
		add(new JLabel("Name of server"));
		nameServer = new JTextField(22);
		add(nameServer);
		
		JButton addSe = new JButton("Add");
		JButton editSe = new JButton("Edit");
		addSe.setPreferredSize(new Dimension(120,25));
		editSe.setPreferredSize(new Dimension(120,25));
		add(addSe);	
		add(editSe);
		
		nameSpicies = new JTextField(22);
		add(new JLabel("Name of spicies"));
		add(nameSpicies);
		for(int i = 0;i<6;i++)
		{
			JLabel t = new JLabel(DVALUES[i]+" alias");
			t.setPreferredSize(new Dimension(120,20));
			add(t);
			aliasSpicies[i] = new JTextField(10);
			add(aliasSpicies[i]);
		}
		
		JButton addSp = new JButton("Add");
		JButton editSp = new JButton("Edit");
		addSp.setPreferredSize(new Dimension(120,25));
		editSp.setPreferredSize(new Dimension(120,25));
		add(addSp);	
		add(editSp);
		
		setVisible(false);
		setPreferredSize(new Dimension(255,575));
		setBackground(Color.CYAN);
		
		
	}
	
}
