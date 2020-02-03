
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import tetris.Screen;

public class Tetris  {
	
	public static final Logger LOGGER = Logger.getLogger(Tetris.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("A");
		PropertyConfigurator.configure(config.Config.getLogPropertyFile());
		LOGGER.debug("START");
		
		Screen s = new Screen();
		s.NewTile();
		for (;true;)
		{
			if (s.Fall())
			{
				s.NewTile();
				if (s.GameOver())
				{
					System.out.println(s);
					System.out.println("GAME OVER");
					break;
				}
			}
			System.out.println(s);
		}
		
		
	}

}
