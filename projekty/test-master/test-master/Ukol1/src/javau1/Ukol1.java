package javau1;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Ukol1 {
	
	
	private static final Logger LOGGER = Logger.getLogger(Ukol1.class);
	
	public static void main(String[] args) {
		PropertyConfigurator.configure("C:\\dev\\git\\test\\Ukol1\\log.properties");
		LOGGER.debug("******BEGIN******");
		
		SpravceDatabaze spravce = SpravceDatabaze.getInstance();
		spravce.provedZmeny();
		JFrame j = new JFrame();
		j.setLocation(0,0);
		j.setSize(500,500);
		j.add(new JLabel("HAHA"));
		LOGGER.debug("******END******");
	}
}
