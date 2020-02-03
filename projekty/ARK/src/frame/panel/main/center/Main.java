package frame.panel.main.center;

import java.awt.Color;

import javax.swing.JPanel;

import org.apache.log4j.Logger;


public class Main extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final Logger LOGGER = Logger.getLogger(Main.class);
	private static final Main INSTANCE = new Main();public static Main getMain() {return INSTANCE;}
	
	

	public Main() {
		super();
		add(Dino.getDino());
		setBackground(Color.CYAN);
	}
	
	
	
	
}
