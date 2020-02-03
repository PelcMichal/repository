package frame.panel.main;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.log4j.Logger;

public class Center extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final Logger LOGGER = Logger.getLogger(Center.class);
	private static final Center INSTANCE = new Center();public static Center getCenter() {return INSTANCE;}
	
	private JButton[] buttons = new JButton[9];
	private JTextField like;
	private StringBuilder sql = new StringBuilder(" ");

	public Center() {
		super();
		JScrollPane main=new JScrollPane(frame.panel.main.center.Main.getMain());
		main.setPreferredSize(new Dimension(740,580));
		main.getVerticalScrollBar().setUnitIncrement(16);
		main.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
		main.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
		like = new JTextField(17);
		like.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				updateCenter();
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				updateCenter();
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				updateCenter();
			}
			
		});
		
		add(like);
		buttonsInit();
		for(int i = 0;i<buttons.length;i++)
		{
			buttons[i].setMargin(new Insets(0, 0, 0, 0));
			add(buttons[i]);
		}
		JPanel t = new JPanel();
		t.setPreferredSize(new Dimension(10,20));
		add(t);
		
		
		add(main);
		setPreferredSize(new Dimension(750,615));
		setBackground(Color.BLACK);
	}
	public void updateCenter(String param, Boolean direction)
	{
			int t = sql.indexOf(param);

			if (t>-1)
			{
				if (direction == null)
				{
					if(t==1)
					{
						if(sql.length()>param.length()+6&&sql.charAt(t+param.length()+6)==',')
						{sql.replace(t, t+param.length()+5, "");}
						else
						{sql.replace(t, t+param.length()+6, "");}
					}
					else
					{sql.replace(t-1, t+param.length()+5, "");}
					
				}
				else
				{
					if(direction)
					{
						sql.replace(t, t+param.length()+5, param+"  ASC");
					}
					else
					{
						sql.replace(t, t+param.length()+5, param+" DESC");
					}
				}
			}
			else
			{
				if (direction != null)
				{
					if(direction)
					{
						if(sql.length()!=1)
							sql.append(",");
						sql.append(param+"  ASC");
					}
					else
					{
						if(sql.length()!=1)
							sql.append(",");
						sql.append(param+" DESC");
					}
				}
			}
			updateCenter();
	}
	public void updateCenter()
	{
		if(sql.length()==1)
		{
			frame.panel.main.center.Dino.getDino().update(database.select.Dino.selectWhere(frame.panel.main.left.Spicies.getSpicies().getIds(), frame.panel.main.left.Server.getServer().getIds(), like.getText())+sql.toString()+"name");
		}
		else
		{
			frame.panel.main.center.Dino.getDino().update(database.select.Dino.selectWhere(frame.panel.main.left.Spicies.getSpicies().getIds(), frame.panel.main.left.Server.getServer().getIds(), like.getText())+sql.toString()+",name");
		}
	}
	
	private void buttonsInit()
	{
		final String[] PARAM = {"is_alive","Gender","Lvl","Health","Stamina","Oxygen","Food","Weight","MeleeDamage"};
		buttons[0] = new JButton();
		buttons[1] = new JButton();
		buttons[0].setPreferredSize(new Dimension(15,15));
		buttons[1].setPreferredSize(new Dimension(15,15));
		buttons[0].setBackground(Color.GRAY);
		buttons[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (buttons[0].getBackground()==Color.GRAY)
				{buttons[0].setBackground(Color.GREEN);updateCenter(PARAM[0],false);}	
				else if (buttons[0].getBackground()==Color.GREEN)
				{buttons[0].setBackground(Color.RED);updateCenter(PARAM[0],true);}
				else{buttons[0].setBackground(Color.GRAY);updateCenter(PARAM[0],null);}
			}});
		buttons[1].setBackground(Color.GRAY);
		buttons[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (buttons[1].getBackground()==Color.GRAY)
				{buttons[1].setBackground(Color.MAGENTA);updateCenter(PARAM[1],true);}
				else if(buttons[1].getBackground()==Color.MAGENTA)
				{buttons[1].setBackground(Color.BLUE);updateCenter(PARAM[1],false);}
				else{buttons[1].setBackground(Color.GRAY);updateCenter(PARAM[1],null);}
			}});
		for(int i = 2;i<PARAM.length;i++)
		{
			final int x =i;
			buttons[x] = new JButton("");
			buttons[x].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (buttons[x].getText().isEmpty())
				{buttons[x].setText("\u25b2");updateCenter(PARAM[x],true);}
				else if(buttons[x].getText().equals("\u25b2"))
				{buttons[x].setText("\u25bc");updateCenter(PARAM[x],false);}
				else {buttons[x].setText("");updateCenter(PARAM[x],null);}
			}});
			buttons[x].setPreferredSize(new Dimension(71,20));
		}
		buttons[2].setPreferredSize(new Dimension(25,20));
	}
	
	
	
}