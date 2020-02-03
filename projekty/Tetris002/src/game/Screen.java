package game;

import java.util.Random;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

public class Screen {
	public static final Logger LOGGER = Logger.getLogger(Screen.class);
	private final int hight;
	private long score;
	private final int with;
	private int tilesFallen;//number of tils that did fallen
	
	private boolean[][] field;//true = full
	private int aktHight;//acording to field
	private int aktWith;//acording to field
	private boolean[][][] activeField = new boolean[6][7][2];//last[0]locked last[1]falling
	

	public Screen(int hight, int with){
		
		if (hight<3) {throw new IndexOutOfBoundsException();}
		if (with<7) {throw new IndexOutOfBoundsException();}
			
		this.hight = hight;
		this.with = with;
	}
	/**
	 * only if possible
	 */
	private void fall()
	{
		for(int th = 1;th<6;th++)
		{
			for(int tw = 0;tw<7;tw++)
			{
				if(activeField[th][tw][0])
					activeField[th-1][tw][0] = true;
			}
		}
		if(aktHight>4)
		{
			for(int tw = 0;tw<7;tw++)
			{
				if(field[aktHight-4][tw])
					activeField[5][tw][0] = true;
			}
		}
		else
		{
			for(int tw = 0;tw<7;tw++)
			{
				activeField[5][tw][0] = true;
			}
		}
	}
	/**
	 * only if possible
	 */
	private void rotateRight()
	{
		boolean temp;
		
		temp = activeField[0][3][1];
		activeField[0][3][1]=activeField[2][1][1];
		activeField[2][1][1]=activeField[4][3][1];
		activeField[4][3][1]=activeField[2][5][1];
		activeField[2][5][1]=temp;
		
		temp = activeField[1][3][1];
		activeField[1][3][1]=activeField[2][2][1];
		activeField[2][2][1]=activeField[3][3][1];
		activeField[3][3][1]=activeField[2][4][1];
		activeField[2][4][1]=temp;
		
		temp = activeField[1][2][1];
		activeField[1][2][1]=activeField[3][2][1];
		activeField[3][2][1]=activeField[3][4][1];
		activeField[3][4][1]=activeField[1][4][1];
		activeField[1][4][1]=temp;
		
		temp = activeField[0][1][1];
		activeField[0][1][1]=activeField[4][1][1];
		activeField[4][1][1]=activeField[4][5][1];
		activeField[4][5][1]=activeField[0][5][1];
		activeField[0][5][1]=temp;
		
		temp = activeField[1][1][1];
		activeField[1][1][1]=activeField[4][2][1];
		activeField[4][2][1]=activeField[3][5][1];
		activeField[3][5][1]=activeField[0][4][1];
		activeField[0][4][1]=temp;
		
		temp = activeField[0][2][1];
		activeField[0][2][1]=activeField[3][1][1];
		activeField[3][1][1]=activeField[4][4][1];
		activeField[4][4][1]=activeField[1][5][1];
		activeField[1][5][1]=temp;
	}
	/**
	 * only if possible
	 */
	private void rotateLeft()
	{
		boolean temp;
		
		temp = activeField[0][3][1];
		activeField[0][3][1]=activeField[2][5][1];
		activeField[2][5][1]=activeField[4][3][1];
		activeField[4][3][1]=activeField[2][1][1];
		activeField[2][1][1]=temp;
		
		temp = activeField[1][3][1];
		activeField[1][3][1]=activeField[2][4][1];
		activeField[2][4][1]=activeField[3][3][1];
		activeField[3][3][1]=activeField[2][2][1];
		activeField[2][2][1]=temp;
		
		temp = activeField[1][2][1];
		activeField[1][2][1]=activeField[1][4][1];
		activeField[1][4][1]=activeField[3][4][1];
		activeField[3][4][1]=activeField[3][2][1];
		activeField[3][2][1]=temp;
		
		temp = activeField[0][1][1];
		activeField[0][1][1]=activeField[0][5][1];
		activeField[0][5][1]=activeField[4][5][1];
		activeField[4][5][1]=activeField[4][1][1];
		activeField[4][1][1]=temp;
		
		temp = activeField[1][1][1];
		activeField[1][1][1]=activeField[0][4][1];
		activeField[0][4][1]=activeField[3][5][1];
		activeField[3][5][1]=activeField[4][2][1];
		activeField[4][2][1]=temp;
		
		temp = activeField[0][2][1];
		activeField[0][2][1]=activeField[1][5][1];
		activeField[1][5][1]=activeField[4][4][1];
		activeField[4][4][1]=activeField[3][1][1];
		activeField[3][1][1]=temp;
	}
	/**
	 * only if possible
	 */
	private void moveRight()
	{
		for(int tw = 1;tw<6;tw++)
		{
			for(int th = 0;th<7;th++)
			{
				if(activeField[th][tw][0] == true)
					activeField[th][tw-1][0] = true;
			}
		}
		if(aktWith+4<with)
		{
			for(int th = 0;th<7;th++)
			{
				activeField[th][5][0] = field[th][aktWith+4];
			}
		}
		else
		{
			for(int th = 0;th<6;th++)
			{
				activeField[th][6][0] = true;
			}
		}
	}
	/**
	 * only if possible
	 */
	private void moveLeft()
	{
		for(int tw = 5;tw>0;tw--)
		{
			for(int th = 0;th<7;th++)
			{
				activeField[th][tw+1][0] = activeField[th][tw][0];
			}
		}
		if(aktWith>4)
		{
			for(int th = 0;th<7;th++)
			{
				if(field[th][aktWith-4])
					activeField[th][0][0] = true;
			}
		}
		else
		{
			for(int th = 0;th<6;th++)
			{
				activeField[th][0][0] = true;
			}
		}
	}
	
	public boolean tryRotateRight()
	{
		LOGGER.debug("Try rotate right");
		boolean[][][] activeFieldt = activeField;
		rotateRight();
		for(int th = 0;th<6;th++)
		{
			for(int tw = 0;tw<7;tw++)
			{
				if (activeField[th][tw][0] && activeField[th][tw][1])
				{
					activeField =activeFieldt;
					LOGGER.debug("Can't rotate");
					return false;
				}
			}
		}
		LOGGER.debug("Rotated");
		updateActiveScreen();
		return true;
	}
	public boolean tryRotateLeft()
	{
		LOGGER.debug("Try rotate left");
		boolean[][][] activeFieldt = activeField;
		rotateLeft();
		for(int th = 0;th<6;th++)
		{
			for(int tw = 0;tw<7;tw++)
			{
				if (activeField[th][tw][0] && activeField[th][tw][1])
				{
					activeField =activeFieldt;
					LOGGER.debug("Can't rotate");
					return false;
				}
			}
		}
		LOGGER.debug("Rotated");
		updateActiveScreen();
		return true;
	}
	public boolean tryMoveRight()
	{
		LOGGER.debug("Try move right");
		boolean[][][] activeFieldt = activeField;
		moveRight();
		for(int th = 0;th<6;th++)
		{
			for(int tw = 0;tw<7;tw++)
			{
				if (activeField[th][tw][0] && activeField[th][tw][1])
				{
					activeField =activeFieldt;
					LOGGER.debug("Can't move");
					return false;
				}
			}
		}
		aktWith++;
		updateActiveScreen();
		LOGGER.debug("Moved");
		return true;
	}
	public boolean tryMoveLeft()
	{
		LOGGER.debug("Try move left");
		boolean[][][] activeFieldt = activeField;
		moveLeft();
		for(int th = 0;th<6;th++)
		{
			for(int tw = 0;tw<7;tw++)
			{
				if (activeField[th][tw][0] && activeField[th][tw][1])
				{
					activeField =activeFieldt;
					LOGGER.debug("Can't move");
					return false;
				}
			}
		}
		aktWith--;
		updateActiveScreen();
		LOGGER.debug("Moved");
		return true;
	}
	public boolean tryFall()//fallen
	{
		LOGGER.debug("Started falling");
		boolean[][][] activeFieldt = activeField;
		fall();
		for(int th = 0;th<6;th++)
		{
			for(int tw = 0;tw<7;tw++)
			{
				if (activeField[th][tw][0] && activeField[th][tw][1])
				{
					activeField = activeFieldt;
					LOGGER.debug("Fallen and will be locked");
					fallen();
					updateScreen();
					return true;
				}
			}
		}
		aktHight--;
		LOGGER.debug("Fallen");
		updateActiveScreen();
		return false;
	}
	/**
	 * tile has fallen and is saved or you lose
	 */
	private void fallen()
	{
		for(int th = 0;th<6;th++)
		{
			for(int tw = 0;tw<7;tw++)
			{
				if (0<=aktHight+2-th&&0<=aktWith-3+tw&&aktWith-3+tw<with)
				{
					if(activeField[th][tw][1])
					{
						if (aktHight+2-th>hight)
						{
							lose();
							return;
						}
						field[aktHight+2-th][aktWith-3+tw]= true;
					}
				}
				
			}
		}
		tilesFallen++;
		score = score+4;
		LOGGER.debug("Fallen and locked");
		clearLines();
		newTile();
	}
	private void clearLines()
	{
		LOGGER.debug("Started clearing lines");
		boolean[] tempLinesToNotRemove = new boolean[hight];//true if not to remove
		int tMoveDown = 0;
		int temp = 0;
		if(aktHight+2>hight)
		{
			temp =hight;
		}
		else
		{
			temp =aktHight+2;
		}
		for(int th = aktHight-2;th<temp;th++)
		{
			for(int tw = 0;tw<with;tw++)
			{
				if(field[th][tw]==false)
				{
					tempLinesToNotRemove[th]=true;
					break;
				}
			}
		}
		for(int th = aktHight;th+tMoveDown<hight;th++)
		{
			if(tempLinesToNotRemove[th]==false)
			{
				tMoveDown++;
			}
			if (tMoveDown>0)
			{
				for(int tw = 0;tw<with;tw++)
				{
					field[th][tw]=field[th+tMoveDown][tw];
				}
			}
		}
		if(tMoveDown>0)
		{
			for(int th = hight-tMoveDown-1;th<hight;th++)
			{
				for(int tw = 0;tw<with;tw++)
				{
					field[th][tw] = false;
				}
			}
			score= score+ with*tMoveDown*tMoveDown;
		}
		LOGGER.debug("Cleared lines");
	}
	
	public void save()
	{
		System.out.println("save");
	}
	private void lose() 
	{
		//TO DOO
	}
	private void newTile()
	{
		LOGGER.debug("Started creating new tile");
		aktHight = hight+1;
		aktWith = with/2;
		activeField = new boolean[6][7][2];
		for (int tw = 0;tw<7;tw++)
		{
			activeField[4][tw][0]=field[hight-1][tw+(with/2)-3];
		}
		for (int tw = 0;tw<7;tw++)
		{
			activeField[5][tw][0]=field[hight-2][tw+(with/2)-3];
		}
		//Random rn = ;
		switch(new Random().nextInt(6)) {
		  case 0://I
			  activeField[0][3][1] = true;
			  activeField[1][3][1] = true;
			  activeField[2][3][1] = true;
			  activeField[3][3][1] = true;
			  	break;
		  case 1://L
			  activeField[0][3][1] = true;
			  activeField[1][3][1] = true;
			  activeField[2][3][1] = true;
			  activeField[2][4][1] = true;
		   		break;
		  case 2://J
			  activeField[0][3][1] = true;
			  activeField[1][3][1] = true;
			  activeField[2][3][1] = true;
			  activeField[2][2][1] = true;
			    break;
		  case 3://Z
			  activeField[2][2][1] = true;
			  activeField[2][3][1] = true;
			  activeField[3][3][1] = true;
			  activeField[3][4][1] = true;
			    break;
		  case 4://S
			  activeField[2][4][1] = true;
			  activeField[2][3][1] = true;
			  activeField[3][3][1] = true;
			  activeField[3][2][1] = true;
			    break;
		  case 5://#
			  activeField[2][3][1] = true;
			  activeField[2][4][1] = true;
			  activeField[3][3][1] = true;
			  activeField[3][4][1] = true;
			    break;
		default:
			LOGGER.error("Random is not working properly");
		}
		tryFall();
		LOGGER.debug("Created new tile");
	}
	public void newGame()
	{
		LOGGER.debug("Creating new game");
		field = new boolean[hight][with];
		tilesFallen = 0;
		updateScreen();
		newTile();
		updateActiveScreen();
		LOGGER.debug("Created new game");
	}
	private void updateActiveScreen()
	{
		LOGGER.debug("Updating active screen");
		//TO DOO
	}
	private void updateScreen()
	{
		LOGGER.debug("Updating whole screen");
		Main.frame.paint(Main.frame.getGraphics());
	}
	
	public int getHight() {
		return hight;
	}
	public long getScore() {
		return score;
	}
	public int getWith() {
		return with;
	}
	public int getTilesFallen() {
		return tilesFallen;
	}
	public boolean[][] getField() {
		return field;
	}
	public int getAktHight() {
		return aktHight;
	}
	public int getAktWith() {
		return aktWith;
	}
	public boolean[][][] getActiveField() {
		return activeField;
	}
	
	
}
