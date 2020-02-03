package tetris;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.apache.log4j.Logger;

public class Screen {
	
	public static final Logger LOGGER = Logger.getLogger(Screen.class);
	
	private static final int HIGHT = 20;
	private static final int WITH = 10;
	private int relHight;
	private int relWith;
	int tiles;

	
	enum Rotation {
		Up,
		Down,
		Left,
		Right
		}
	private Rotation rotation;
	enum Block {
		Empty,
		Locked,
		Falling
		}
	
	private Block[][] screen = new Block[HIGHT+3][WITH];

	public Screen() {
		CleenScreen();
		LOGGER.debug("Create screen");
	}
	public void CleenScreen()
	{
		for (int i = 0; i < WITH;i++) {
			for (int x = 0; x < HIGHT+3;x++) {
				screen[x][i] = Block.Empty;
			}
		}
		updateScreen();
		LOGGER.debug("Screen cleared");
	}
	public void NewTile()
	{
		rotation = Rotation.Up;
		relHight = HIGHT+1;
		relWith = WITH/2;
		int a = (int)(Math.random()*6);
		switch (a)
		{
		case 0:  screen[HIGHT][WITH/2] = Block.Falling;screen[HIGHT+1][WITH/2] = Block.Falling;screen[HIGHT+2][WITH/2] = Block.Falling;screen[HIGHT+3][WITH/2] = Block.Falling;
		case 1:  screen[HIGHT][WITH/2] = Block.Falling;screen[HIGHT][(WITH/2)+1] = Block.Falling;screen[HIGHT+1][(WITH/2)+1] = Block.Falling;screen[HIGHT+2][(WITH/2)+1] = Block.Falling;
		case 2:  screen[HIGHT][WITH/2] = Block.Falling;screen[HIGHT][(WITH/2)+1] = Block.Falling;screen[HIGHT+1][WITH/2] = Block.Falling;screen[HIGHT+2][WITH/2] = Block.Falling;
		case 3:  screen[HIGHT][WITH/2] = Block.Falling;screen[HIGHT][(WITH/2)+1] = Block.Falling;screen[HIGHT+1][WITH/2] = Block.Falling;screen[HIGHT+1][(WITH/2)+1] = Block.Falling;
		case 4:  screen[HIGHT][WITH/2] = Block.Falling;screen[HIGHT+1][(WITH/2)+1] = Block.Falling;screen[HIGHT+1][WITH/2] = Block.Falling;screen[HIGHT+2][(WITH/2)+1] = Block.Falling;
		case 5:  screen[HIGHT][(WITH/2)+1] = Block.Falling;screen[HIGHT+1][(WITH/2)+1] = Block.Falling;screen[HIGHT+1][WITH/2] = Block.Falling;screen[HIGHT+2][WITH/2] = Block.Falling;
		}
		updateScreen();
		LOGGER.debug("Tile created");
	}
	public boolean Fall()
	{
		LOGGER.debug("Started Falling");
		boolean fallen = false;
		for (int a = 0; a < WITH;a++) {
			if (screen[0][a]==Block.Falling) {
				fallen = true;
				break;
			}
			for (int b = 1; b < HIGHT+2;b++) {
					if (screen[b][a]==Block.Falling)
					{
						if (IsAvilible(a,b) ==false)
						{
							fallen = true;
						}
					}
			}
		}
		LOGGER.debug("fallen" + fallen);
		if (fallen) {
			for (int a = 0; a < WITH;a++) {
				for (int b = 0; b < HIGHT+3;b++) {
					if(screen[b][a] == Block.Falling) {
						screen[b][a] = Block.Locked;
					}
					
				}
			}
			for (int b = 0; b < HIGHT;b++) {
				boolean delete = true;
				for (int a = 0; a < WITH;a++) {
					if ( screen[b][a] == Block.Empty)
					{
						delete = false;
						break;
					}
				}
				if(delete)
				{
					for (int a = 0; a < WITH;a++) {
						screen[b][a] = Block.Empty;
					}
					for (int c = b+1; c < HIGHT;c++)
					{
						for (int a = 0; a < WITH;a++) {
							if ( screen[c][a] == Block.Locked)
							{
								screen[c][a] = Block.Empty;
								screen[c-1][a] = Block.Locked;
							}
						}
					}
				}
			}
			relHight--;
			LOGGER.debug("fallen and locked");
			
		}
		updateScreen();
		return fallen;
	}
	public boolean RotateRight()
	{
		
		
		updateScreen();
		return false;
	}
	private boolean IsAvilible(int With,int Hight)
	{
		if (screen[Hight][With] ==Block.Empty||screen[Hight][With] ==Block.Falling)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	private void updateScreen()
	{
		
	}
	/*private void Running() 
	{
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("sszz");
		String formattedDate = myDateObj.format(myFormatObj);
		if (Integer.valueOf(formattedDate)%(200-tiles)==0)
		{
			
		}
	}*/
	public boolean GameOver()
	{
		for(int i = 2;i>-1;i--)
		{
			for(int x = 0; x<WITH;x++)
			{
				if(screen[HIGHT+i][x]==Block.Locked)
					return true;
			}
		}
		return false;
	}
	
	
	public boolean Left()
	{
		LOGGER.debug("Move Left");
		if (LeftAvilible())
		{
			for (int a = 0; a < WITH;a++) {
				for (int b = 1; b < HIGHT+4;b++) {
					if (screen[b][a] ==Block.Falling)
					{
						screen[b][a] = Block.Empty;
						screen[b][a-1] = Block.Falling;
					}
				}
			}
			updateScreen();
			return true;
		}
		return false;
	}
	public boolean Right()
	{
		LOGGER.debug("Move Right");
		if (RightAvilible())
		{
			for (int a = WITH; a > 0;a--) {
				for (int b = 1; b < HIGHT+4;b++) {
					if (screen[b][a] ==Block.Falling)
					{
						screen[b][a] = Block.Empty;
						screen[b][a-1] = Block.Falling;
					}
				}
			}
			updateScreen();
			return true;
		}
		return false;
	}
	private boolean LeftAvilible()
	{
		LOGGER.debug("Can move Left?");
		for (int a = 0; a < WITH;a++) {
			for (int b = 1; b < HIGHT+4;b++) {
				if (screen[b][a] ==Block.Falling)
				{
					if (a == 0)
					{
						LOGGER.debug("It can\'t");
						return false;
					}
					if (screen[b][a-1] ==Block.Locked)
					{
						LOGGER.debug("It can\'t");
						return false;
						
					}
					
				}
			}
		}
		LOGGER.debug("It can");
		return true;
	}
	private boolean RightAvilible()
	{
		LOGGER.debug("Can move Right?");
		for (int a = 0; a < WITH;a++) {
			for (int b = 1; b < HIGHT+4;b++) {
				if (screen[b][a] ==Block.Falling)
				{
					if (a == WITH)
					{
						LOGGER.debug("It can\'t");
						return false;
					}
					if (screen[b][a+1] ==Block.Locked)
					{
						LOGGER.debug("It can\'t");
						return false;
						
					}
					
				}
			}
		}
		LOGGER.debug("It can");
		return true;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Screen \nHIGHT =  \t");
		s.append(HIGHT);
		s.append("\tWITH =  \t");
		s.append(WITH);
		s.append("\nrelHight =\t" );
		s.append(relHight);
		s.append("\trelWith =\t");
		s.append(relWith);
		s.append("\trotation = ");
		s.append(rotation);
		s.append("\n");
		for(int i = 2;i>-1;i--)
		{
			for(int x = 0; x<WITH;x++)
			{
				s.append("|");
				if (screen[HIGHT+i][x]==Block.Empty)
				{
					s.append("E");
				}else if(screen[HIGHT+i][x]==Block.Locked)
				{
					s.append("L");
				}else if(screen[HIGHT+i][x]==Block.Falling)
				{
					s.append("F");
				}
			}
			s.append("\n");
		}
		s.append("\n");
		for(int i = HIGHT-1;i>=0;i--)
		{
			for(int x = 0; x<WITH;x++)
			{
				s.append("|");
				if (screen[i][x]==Block.Empty)
				{
					s.append("E");
				}else if(screen[i][x]==Block.Locked)
				{
					s.append("#");
				}else if(screen[i][x]==Block.Falling)
				{
					s.append("=");
				}
			}
			s.append("\n");
		}
		
		return s.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + relHight;
		result = prime * result + relWith;
		result = prime * result + ((rotation == null) ? 0 : rotation.hashCode());
		result = prime * result + Arrays.deepHashCode(screen);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Screen other = (Screen) obj;
		if (relHight != other.relHight)
			return false;
		if (relWith != other.relWith)
			return false;
		if (rotation != other.rotation)
			return false;
		if (!Arrays.deepEquals(screen, other.screen))
			return false;
		return true;
	}
	
	
	
}
