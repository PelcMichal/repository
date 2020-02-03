package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.apache.log4j.Logger;

public class Screen {
	private static final short[][][] ROTATEBLE = getRotetable();//[set][number][h=0 w=1]
	private static short[][][] getRotetable()
	{
		short[][][] t = new short[3][4][2];
		t[0][0][0] = 2;t[0][0][1] = 1;
		t[0][1][0] = 0;t[0][1][1] = 3;
		t[0][2][0] = 2;t[0][2][1] = 5;
		t[0][3][0] = 4;t[0][3][1] = 3;
		
		t[1][0][0] = 2;t[1][0][1] = 2;
		t[1][1][0] = 1;t[1][1][1] = 3;
		t[1][2][0] = 2;t[1][2][1] = 4;
		t[1][3][0] = 3;t[1][3][1] = 3;
		
		t[2][0][0] = 1;t[2][0][1] = 2;
		t[2][1][0] = 1;t[2][1][1] = 4;
		t[2][2][0] = 3;t[2][2][1] = 4;
		t[2][3][0] = 3;t[2][3][1] = 2;
		
		return t;
	}
	public static final Logger LOGGER = Logger.getLogger(Screen.class);
	private int hight;
	private long score;
	private int width;
	private int tilesFallen;//number of tils that did fallen
	
	private boolean[][] field;//true = full
	private int aktHight;//acording to field
	private int aktWith;//acording to field
	private boolean[][][] activeField = new boolean[6][7][2];//last[0]locked last[1]falling
	
	/**
	 * Creates screen
	 * @param hight min 3
	 * @param with min 7
	 */
	public Screen(int hight, int with){
		
		this.hight = 0;
		this.width = 0;
		if (hight<3) {throw new IndexOutOfBoundsException();}
		if (with<7) {throw new IndexOutOfBoundsException();}
			
		this.hight = hight;
		this.width = with;
		field = new boolean[hight][with];
	}
	public Screen(String save)
	{
	//	20|10|0|0|-1|0|00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010101010101010
		
		StringBuilder sb = new StringBuilder(save);
		int position =0;
		int temp;
		temp = sb.indexOf("|");
		hight=Integer.parseInt(sb.substring(position, temp));
		position = temp+1;
		
		temp = sb.indexOf("|",position);
		width=Integer.parseInt(sb.substring(position, temp));
		position = temp+1;
		
		temp = sb.indexOf("|",position);
		score=Integer.parseInt(sb.substring(position, temp));
		position = temp+1;
		
		temp = sb.indexOf("|",position);
		tilesFallen=Integer.parseInt(sb.substring(position, temp));
		position = temp+1;
		
		temp = sb.indexOf("|",position);
		aktHight=Integer.parseInt(sb.substring(position, temp));
		position = temp+1;
		
		temp = sb.indexOf("|",position);
		aktWith=Integer.parseInt(sb.substring(position, temp));
		position = temp+1;
		
		field = new boolean[hight][width];
		
		for(int h = 0;h<hight;h++)
		{
			for(int w = 0;w<width;w++)
			{
				field[h][w] = sb.charAt(position+(h*width)+w) == 49;
			}
		}
		position = position +hight*width;
		for(int h = 0;h<6;h++)
		{
			for(int w = 0;w<7;w++)
			{
				activeField[h][w][1] = sb.charAt(position+(h*7)+w) == 49||sb.charAt(position+(h*7)+w) == 51;
				activeField[h][w][0] = sb.charAt(position+(h*7)+w) == 50||sb.charAt(position+(h*7)+w) == 51;
			}
		}
	}
	
	
	public void newGame()
	{
		field = new boolean[hight][width];
		newTile();
		tilesFallen =0;
		score = 0;
	}
	/*st.execute("drop table high_score" );
			st.execute("create table high_score(Id INT NOT NULL GENERATED ALWAYS AS IDENTITY,Name VARCHAR(20) NOT NuLL,Time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,Score BIGINT NOT NULL,Hight INTEGER not null,width INTEGER not null,PRIMARY KEY (Id))");*/
	public void saveHighScore(String name) 
	{
		try (Statement st = DriverManager.getConnection("jdbc:derby:testdb1;create=true").createStatement();){
			st.execute(highScore(name));
		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}
	public String highScore(String name)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO high_score(name,Score,Hight,width) VALUES('");
		sb.append(name);
		sb.append("',");
		sb.append(score);
		sb.append(",");
		sb.append(hight);
		sb.append(",");
		sb.append(width);
		sb.append(")");
		return sb.toString();
	}
	
	public void save() 
	{
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\save\\save.txt"));){
			bufferedWriter.write(toString());
		} catch (IOException e) {
			LOGGER.error(e);
		}
	}
	public static String load()
	{
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(".\\save\\save.txt"));) {
			String s = bufferedReader.readLine();
			return s;
		} catch (FileNotFoundException e) {
			LOGGER.error(e);
		}catch (IOException e) {
			LOGGER.error(e);
		}
		return null;
	}
	
	
	

	@Override

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(hight);
		sb.append("|");
		sb.append(width);
		sb.append("|");
		sb.append(score);
		sb.append("|");
		sb.append(tilesFallen);
		sb.append("|");
		sb.append(aktHight);
		sb.append("|");
		sb.append(aktWith);
		sb.append("|");
		for(int h = 0;h<hight;h++)
		{
			for(int w = 0;w<width;w++)
			{
				if(field[h][w])
				{
					sb.append(1);
				}
				else
				{
					sb.append(0);
				}
			}
		}
		for(int h = 0;h<6;h++)
		{
			for(int w = 0;w<7;w++)
			{
				if(activeField[h][w][0]&&activeField[h][w][1])
				{
					sb.append(3);
				}
				else if(activeField[h][w][0]&&!activeField[h][w][1])
				{
					sb.append(2);
				}
				else if(!activeField[h][w][0]&&activeField[h][w][1])
				{
					sb.append(1);
				}
				else 
				{
					sb.append(0);
				}
			}
		}
		return  sb.toString();
	}
	
	
	
	
	public void updateScreen() {
		updateActiveScreen();
		Main.frame.repaint();
	}
	
	public void updateActiveScreen() {
		Main.tActiveFrame.repaint();
	}
	
	private void lose() 
	{
		Main.tHighSoreFrame.setSize((getWith()*20)+50, (getHight()*20)+60);
		
		Main.timer.stop();
		Main.tHighSoreFrame.score.setText("Your score is : "+score);
		
		Main.tWholeFrame.setVisible(false);
		Main.tScoreBoardFrame.setVisible(false);
		Main.tHighSoreFrame.setVisible(false);
		Main.tHighSoreFrame.setVisible(true);
		//newGame();
		//updateScreen();
		//Main.tWholeFrame.setVisible(false);
	}
	private boolean isLosing() 
	{
		if (aktHight+4<hight)
			return false;
		for(int th = 0;th<6&&hight<aktHight-th+3;th++)
		{
			for(int tw = 0;tw<7;tw++)
			{
				if (activeField[th][tw][1])
					return true;
			}
		}
		return false;
	}
	
	public boolean tryFall()
	{
		if(canFall())
		{
			fall();
			updateActiveScreen();
			return true;
		}
		else
		{
			if(isLosing())
			{
				lose();
			}
			else
			{
			lock();
			score=score+4;
			if(clearLines()>0)
			{
				updateScreen();
			}
			
			newTile();
			updateActiveScreen();
			}
			
		return false;
		}
		
	}
	private boolean canFall()
	{
		for(int th = 0;th<5;th++)
		{
			for(int tw = 1;tw<6;tw++)
			{
				if(activeField[th][tw][1]&&activeField[th+1][tw][0])
				{
					return false;
				}
			}
		}
		return true;
	}
	private void fall()
	{
		for(int th = 1;th<6;th++)
		{
			for(int tw = 0;tw<7;tw++)
			{
				activeField[th-1][tw][0]=activeField[th][tw][0];
			}
		}
		for(int tw = 0;tw<7;tw++)
		{
			if (aktHight<4)
			{
				activeField[5][tw][0] = true;
			}
			else
			{
				if(aktWith-2+tw>0&&aktWith-3+tw<width)
				{
					activeField[5][tw][0] = field[aktHight-4][aktWith-3+tw];
				}
				else
				{
					activeField[5][tw][0] = true;
				}
			}
		}
		aktHight--;
	}
	private void lock()
	{
		for(int th = 0;th<5;th++)
		{
			for(int tw = 1;tw<6;tw++)
			{
				if (activeField[th][tw][1])
					field[aktHight+2-th][aktWith-3+tw] = true;
			}
		}
	}
	private int clearLines() 
	{
		LOGGER.debug("Started clearing lines");
		int tMin = 0;if(aktHight-2<0){tMin =0;}else{tMin =aktHight-2;}
		int tMax = 0;if(aktHight+3>hight){tMax =hight;}else{tMax =aktHight+3;}
		int toClear =  howMuchLinesToClear(tMin,tMax);
		if (toClear>0&&tMax<hight)
			fallUpperLines(tMax,toClear);
		score= score+(toClear*toClear*width);
		LOGGER.debug("Cleared lines");
		return toClear;
	}
	private int howMuchLinesToClear(int min , int max)
	{
		int temp = 0;
		for(;min<max;min++)
		{
			boolean t2 = true;
			for(int t1 = 0;t1<width;t1++)
			{
				if (!field[min][t1])
				{
					t2=false;
					break;
				}
			}
			if (t2)
			{
				clearActiveLines(min ,max);
				max--;
				min--;
				temp++;
			}
		}
		
		return temp;
	}
	private void clearActiveLines(int min , int max)
	{
		for(;min<max;min++)
		{
			for(int t = 0;t<width;t++)
			{
				field[min][t] = field[min+1][t];
			}
		}
		
		for(int t = 0;t<width;t++)
		{
			field[max-1][t] = false;
		}
	}
	private void fallUpperLines(int startAt,int toClear)
	{
		for(;startAt<hight;startAt++)
		{
			for(int t = 0;t<width;t++)
			{
				field[startAt-toClear][t] = field[startAt][t];
			}
		}
		for(;toClear>0;toClear--)
		{
			for(int t = 0;t<width;t++)
			{
				field[hight-toClear][t] = false;
			}
		}
	}
	
	public boolean tryMoveRight()
	{
		if(canMoveRight())
		{
			moveRight();
			updateActiveScreen();
			return true;
		}
		return false;
	}
	private boolean canMoveRight()
	{
		for(int tw = 1;tw<6;tw++)
		{
			for(int th = 0;th<5;th++)
			{
				if(activeField[th][tw][1]&&activeField[th][tw+1][0])
				{
					return false;
				}
			}
		}
		return true;
	}
	private void moveRight()
	{
		for(int tw = 1;tw<7;tw++)
		{
			for(int th = 0;th<6;th++)
			{
				activeField[th][tw-1][0]=activeField[th][tw][0];
				
			}
		}
		for(int th = 0;th<6;th++)
		{
			if (aktWith+5>width)
			{
				activeField[th][6][0] = true;
			}
			else
			{
				if(aktHight-th+2<hight)
				{
					if(aktHight-th+3>0)
					{
						activeField[th][6][0] = field[aktHight-th+2][aktWith+4];
					}
					else
					{
						activeField[th][0][0] = true;
					}
				}
				else
				{
					if (aktWith+4>width)
						activeField[th][6][0] = true;
				}
			}
		}
		aktWith++;
	}
	
	public boolean tryMoveLeft()
	{
		if(canMoveLeft())
		{
			moveLeft();
			updateActiveScreen();
			return true;
		}
		return false;
	}
	private boolean canMoveLeft()
	{
		for(int tw = 5;tw>0;tw--)
		{
			for(int th = 0;th<5;th++)
			{
				if(activeField[th][tw][1]&&activeField[th][tw-1][0])
				{
					return false;
				}
			}
		}
		return true;
	}
	private void moveLeft()
	{
		for(int tw = 5;tw>=0;tw--)
		{
			for(int th = 0;th<6;th++)
			{
				activeField[th][tw+1][0]=activeField[th][tw][0];
				
			}
		}
		for(int th = 0;th<6;th++)
		{
			if (aktWith-4<0)
			{
				activeField[th][0][0] = true;
			}
			else
			{
				if(aktHight-th+2<hight)
				{
					if(aktHight-th+3>0)
					{
						activeField[th][0][0] = field[aktHight-th+2][aktWith-4];
					}
					else
					{
						activeField[th][0][0] = true;
					}
				}
				else
				{
					if (aktWith+4<0)
						activeField[th][0][0] = true;
				}
			}
		}
		aktWith--;
	}
	
	
	public boolean tryRotateRight()
	{
		LOGGER.debug("Try rotate right");
		if(canRotateRight())
		{
			rotateRight();
			updateActiveScreen();
			LOGGER.debug("Rotated");
		}
		else
		{
			LOGGER.debug("Can't rotate");
		}
		return true;
	}
	private boolean rotatedRightValue(int h,int w)
	{
		switch(w +(h*10)){
		  case 21:return activeField[0][3][0];
		  case 03:return activeField[2][5][0];
		  case 25:return activeField[4][3][0];
		  case 43:return activeField[2][1][0];
		  
		  case 22:return activeField[1][3][0];
		  case 13:return activeField[2][4][0];
		  case 24:return activeField[3][3][0];
		  case 33:return activeField[2][2][0];
		  
		  case 12:return activeField[1][4][0];
		  case 14:return activeField[3][4][0];
		  case 34:return activeField[3][2][0];
		  case 32:return activeField[1][2][0];
		  
		  default:LOGGER.error("Rotated right value wrong parametrs");throw new IndexOutOfBoundsException();
		}
	}
	private boolean canRotateRight()
	{
		for(int s = 0;s<3;s++)
		{
			for(int n = 0;n<4;n++)
			{
				if(activeField[ROTATEBLE[s][n][0]][ROTATEBLE[s][n][1]][1]&&rotatedRightValue(ROTATEBLE[s][n][0],ROTATEBLE[s][n][1]))
					return false;
			}
		}
		return true;
	}
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
		
		temp = activeField[3][2][1];
		activeField[3][2][1]=activeField[3][4][1];
		activeField[3][4][1]=activeField[1][4][1];
		activeField[1][4][1]=activeField[1][2][1];
		activeField[1][2][1]=temp;
	}
	
	public boolean tryRotateLeft()
	{
		LOGGER.debug("Try rotate right");
		if(canRotateLeft())
		{
			rotateLeft();
			LOGGER.debug("Rotated");
			updateActiveScreen();
		}
		else
		{
			LOGGER.debug("Can't rotate");
		}
		return true;
	}
	private boolean rotatedLeftValue(int h,int w)
	{
		switch(w +(h*10)){
		  case 21:return activeField[4][3][0];
		  case 03:return activeField[2][1][0];
		  case 25:return activeField[0][3][0];
		  case 43:return activeField[2][5][0];
		  
		  case 22:return activeField[3][3][0];
		  case 13:return activeField[2][2][0];
		  case 24:return activeField[1][3][0];
		  case 33:return activeField[2][4][0];
		  
		  case 12:return activeField[3][2][0];
		  case 14:return activeField[1][2][0];
		  case 34:return activeField[1][4][0];
		  case 32:return activeField[3][4][0];
		  
		  default:LOGGER.error("Rotated right value wrong parametrs");throw new IndexOutOfBoundsException();
		}
	}
	private boolean canRotateLeft()
	{
		for(int s = 0;s<3;s++)
		{
			for(int n = 0;n<4;n++)
			{
				if(activeField[ROTATEBLE[s][n][0]][ROTATEBLE[s][n][1]][1]&&rotatedLeftValue(ROTATEBLE[s][n][0],ROTATEBLE[s][n][1]))
					return false;
			}
		}
		return true;
	}
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
		
		temp = activeField[3][2][1];
		activeField[3][2][1]=activeField[1][2][1];
		activeField[1][2][1]=activeField[1][4][1];
		activeField[1][4][1]=activeField[3][4][1];
		activeField[3][4][1]=temp;
		
	}
	
	private void newTile()
	{
		LOGGER.debug("Started creating new tile");
		aktHight = hight+1;
		aktWith = width/2;
		activeField = new boolean[6][7][2];
		for (int tw = 0;tw<7;tw++)
		{
			activeField[4][tw][0]=field[hight-1][tw+(width/2)-3];
		}
		for (int tw = 0;tw<7;tw++)
		{
			activeField[5][tw][0]=field[hight-2][tw+(width/2)-3];
		}
		switch(new Random().nextInt(7)) {
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
		  case 6://|-
			  activeField[2][3][1] = true;
			  activeField[1][3][1] = true;
			  activeField[3][3][1] = true;
			  activeField[2][4][1] = true;
			    break;
		default:
			LOGGER.error("Random is not working properly");
		}
		LOGGER.debug("Created new tile");
	}
	
	
	public int getHight() {
		return hight;
	}
	public String getScore() {
		return Long.toString(score);
	}
	public int getWith() {
		return width;
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
