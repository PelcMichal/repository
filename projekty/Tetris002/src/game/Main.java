package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main {

	public static final Logger LOGGER = Logger.getLogger(Main.class);
	static GraphicsConfiguration g;
	public static JFrame frame;
	
	public static void main(String[] args) {
		PropertyConfigurator.configure(".\\config\\log.properties");
		LOGGER.debug("***START***");
		
		
		
		Screen s = new Screen(50,25);
		frame= new JFrame("TETRIS",g) {
			@Override
			public void paint(Graphics g)
			{
			super.paint(g);
			
			boolean[][] f = s.getField();
			
			for(int th = 0;th<s.getAktHight();th++)
			{
				for(int tw = 0;tw<s.getWith();tw++)
				{
					g.drawRect(10+(tw*20),50+(th*20),20,20);
						if(f[th][tw])
						{
							g.setColor(Color.BLUE);
							g.fillRect(10+(tw*20)+1,50+(th*20)+1,19,19);
							g.setColor(Color.BLACK);
						}
					
					}
				}
			}
		};
		
		frame.setSize((s.getWith()*20)+20, (s.getHight()*20)+60);
		frame.setLocation(0, 0);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {@Override public void windowClosing(WindowEvent e) {s.save();System.exit(0);}});
		s.newGame();
		for(int i = 0 ; i<500;i++)
			s.tryFall();
		
		frame.paint(frame.getGraphics());
				
			
		
		
		System.out.println(5/2);
		LOGGER.debug("****END****");
	}

}
