package frame.panel.main;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

public class Down extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final Logger LOGGER = Logger.getLogger(Down.class);
	private static final Down INSTANCE = new Down();public static Down getDown() {return INSTANCE;}
	private JPanel wiki = new JPanel();
	private JPanel dododex = new JPanel();
	
	private String wS = "https://ark.gamepedia.com";
	private String dS = "https://www.dododex.com"; 
	private JLabel  wL = new JLabel(wS);
	private JLabel  dL = new JLabel(dS);
	
	private Down()
	{
		super();
		wL.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	try {
					Desktop.getDesktop().browse(new URI(wS));
				} catch (IOException | URISyntaxException e1) {
					LOGGER.error(e1);
				} 
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        wL.setText("<html><a href=''>" + wS + "</a></html>");
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		    	wL.setText(wS);
		    }
		});
		dL.addMouseListener(new MouseAdapter() {
			 
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	try {
					Desktop.getDesktop().browse(new URI(dS));
				} catch (IOException | URISyntaxException e1) {
					LOGGER.error(e1);
				} 
		    }
		 
		    @Override
		    public void mouseEntered(MouseEvent e) {
		    	dL.setText("<html><a href=''>" + dS + "</a></html>");
		    }
		 
		    @Override
		    public void mouseExited(MouseEvent e) {
		    	dL.setText(dS);
		    }
		});
		
		
		wiki.setPreferredSize(new Dimension(625,30));
		wiki.setBackground(Color.CYAN);
		wiki.add(wL);
		add(wiki);
		
		dododex.setPreferredSize(new Dimension(625,30));
		dododex.setBackground(Color.CYAN);
		dododex.add(dL);
		add(dododex);
		
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(1265,40));
	}
	public boolean setSpicies(String dinoName) {
		if(dinoName==null)
		{
			wS = "https://ark.gamepedia.com";
			dS = "https://www.dododex.com";
		}else
		{
			String a = dinoName.trim();
			int i;
			while(a.contains(" "))
			{
				i = a.indexOf(' ');
				a = a.substring(0, i)+"_"+a.substring(i+1);
			}
			wS = "https://ark.gamepedia.com/"+a;
			if(dinoName.startsWith("Aberrant ")||dinoName.startsWith("Abberant ")){a = dinoName.trim().substring(9).toLowerCase();}
			else {a = dinoName.trim().toLowerCase();}
				
			
			while(a.contains(" "))
			{
				i = a.indexOf(' ');
				a = a.substring(0, i)+a.substring(i+1);
			}
			dS = "https://www.dododex.com/stat-calculator/"+a;
		}
		wL.setText(wS);
		dL.setText(dS);
		return true;
	}
}
