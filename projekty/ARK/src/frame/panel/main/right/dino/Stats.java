package frame.panel.main.right.dino;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import frame.panel.main.right.WrongDataException;

public class Stats extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final Logger LOGGER = Logger.getLogger(Stats.class);
	
	private static final Stats HEALTH = new Stats("Health");public static Stats getHealth() {return HEALTH;}
	private static final Stats STAMINA = new Stats("Stamina");public static Stats getStamina() {return STAMINA;}
	private static final Stats OXYGEN = new Stats("Oxygen");public static Stats getOxygen() {return OXYGEN;}
	private static final Stats FOOD = new Stats("Food");public static Stats getFood() {return FOOD;}
	private static final Stats WEIGHT = new Stats("Weight");public static Stats getWeight() {return WEIGHT;}
	private static final Stats MELEEDAMAGE = new Stats("Melee Damage");public static Stats getMeleeDamage() {return MELEEDAMAGE;}
	
	private final JLabel name;
	private final JTextField num;
	private Long numF = null;
	private Long numM = null;
	private final JTextField mut;
	private Byte mutF = null;
	private Byte mutM = null;
	private final JButton gen;
	private static final String W ="Wrong data in part ";
	
	private Stats(String typ) {
		super();
		name = new JLabel(typ);
		add(name);
		num = new JTextField(10);
		mut = new JTextField(4);
		add(num);
		add(new JLabel("Mutaions  "));
		add(mut);
		gen = new JButton();
		gen.setBackground(Color.GRAY);
		gen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (gen.getBackground()==Color.GRAY)
				{
					gen.setBackground(Color.MAGENTA);
					if(numF!=null)
						num.setText(numF+"");
					if(mutF!=null)
						mut.setText(mutF+"");
				}
				else if(gen.getBackground()==Color.MAGENTA)
				{
					gen.setBackground(Color.BLUE);
					if(numM!=null)
						num.setText(numM+"");
					if(mutM!=null)
						mut.setText(mutM+"");
				}
				else{gen.setBackground(Color.GRAY);}
			}});
		gen.setPreferredSize(new Dimension(20,20));
		add(new JLabel("Inherited fron   "));
		add(gen);
		setPreferredSize(new Dimension(120,100));
		setBackground(Color.GREEN);
	}
	public void update(String alias,long stat,byte mutations,Boolean gender)
	{
		name.setText(alias);
		num.setText(stat+"");
		mut.setText(mutations+"");
		if(gender==null)
		{
			gen.setBackground(Color.GRAY);
		}
		else
		{
			if (gender) {gen.setBackground(Color.MAGENTA);}
			else{gen.setBackground(Color.BLUE);}
		}
	}
	public void updateF(Long a,Byte b)
	{
		numF = a;
		mutF = b;
	}
	public void updateM(Long a,Byte b)
	{
		numM = a;
		mutM = b;
	}
	public String getInsertPart() throws WrongDataException
	{
		if(mut.getText()==null||mut.getText().isEmpty())
			mut.setText("0");
		if(num.getText()==null||num.getText().isEmpty())
			num.setText("0");
		try {Long.valueOf(num.getText());Byte.valueOf(mut.getText());}
		catch(NumberFormatException e) {throw new WrongDataException(W+name.getText());}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(Long.valueOf(num.getText()));
		sb.append(",");
		sb.append(Byte.valueOf(mut.getText()));
		sb.append(",");
		if(gen.getBackground()==Color.GRAY)
		{sb.append("null");}
		else if(gen.getBackground()==Color.BLUE) 
		{sb.append("true");}
		else {sb.append("false");}
		
		return sb.toString();
	}
	public String getUpdatePart(String name) throws WrongDataException
	{
		if(mut.getText()==null||mut.getText().isEmpty())
			mut.setText("0");
		if(num.getText()==null||num.getText().isEmpty())
			num.setText("0");
		try {Long.valueOf(num.getText());Byte.valueOf(mut.getText());}
		catch(NumberFormatException e) {throw new WrongDataException(W+name);}
		
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append(" = ");
		sb.append(Long.valueOf(num.getText()));
		sb.append(",");
		sb.append(name);
		sb.append("_mutations = ");
		sb.append(Byte.valueOf(mut.getText()));
		sb.append(",");
		sb.append(name);
		sb.append("_from_Gender = ");
		
		if(gen.getBackground()==Color.GRAY)
		{sb.append("null");}
		else if(gen.getBackground()==Color.BLUE) 
		{sb.append("true");}
		else {sb.append("false");}
		
		return sb.toString();
	}
	public void update(String alias) {
		name.setText(alias);
	}
	
	
	
}
