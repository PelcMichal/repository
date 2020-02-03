package game;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main {
	public static final Logger LOGGER = Logger.getLogger(Main.class);
	public static Screen s;
	public static Timer timer;
	public static TFrame frame;
	public static final TActiveFrame tActiveFrame= new TActiveFrame();
	public static final TWholeFrame tWholeFrame= new TWholeFrame();
	public static final THighSoreFrame tHighSoreFrame = new THighSoreFrame();
	public static final TScoreBoardFrame tScoreBoardFrame = new TScoreBoardFrame();
	
	public static void main(String[] args) {
		PropertyConfigurator.configure(".\\config\\log.properties");
		LOGGER.debug("***START***");
		s = new Screen(Screen.load());
		timer = new Timer(1000, new ActionListener() {@Override public void actionPerformed(ActionEvent evt) {Main.s.tryFall();timer.setDelay((int)(100000/(100+s.getTilesFallen())));}});
		frame = new TFrame();
		s.updateScreen();
		frame.add(tScoreBoardFrame);
		frame.add(tHighSoreFrame);
		frame.add(tWholeFrame);
		
		tWholeFrame.add(tActiveFrame);
			
		
		
		LOGGER.debug("****END****");
	}
}
