package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main extends JFrame implements KeyListener{
	public static Screen s = new Screen(20,10);
	private static Timer timer = new Timer(1000, new ActionListener() {@Override public void actionPerformed(ActionEvent evt) {Main.s.tryFall();timer.setDelay((int)(100000/(100+s.getTilesFallen())));}});
	public void keyPressed(KeyEvent e) {

				if(e.getKeyCode()== KeyEvent.VK_D)
				{
		            s.tryMoveRight();
				}
		        else if(e.getKeyCode()== KeyEvent.VK_A)
		        {
		            s.tryMoveLeft();
		        }
		        else if(e.getKeyCode()== KeyEvent.VK_S)
		        {
		            s.tryFall();
		            timer.restart();
		        }
		        else if(e.getKeyCode()== KeyEvent.VK_Q)
		        {
		            s.tryRotateLeft();
		        }
		        else if(e.getKeyCode()== KeyEvent.VK_E)
		        {
		            s.tryRotateRight();
		        }
		        else if(e.getKeyCode()== KeyEvent.VK_P)
		        {
		            timer.stop();
		        }
			}
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		boolean[][] f = s.getField();
		boolean[][][] a = s.getActiveField();
		for(int th = 0;th<s.getHight();th++)
		{
			for(int tw = 0;tw<s.getWith();tw++)
			{
				g.drawRect(10+(tw*20),50+(s.getHight()*20)-(th*20),20,20);
				if(f[th][tw])
				{
					g.setColor(Color.BLUE);
					g.fillRect(10+(tw*20)+1,50+(s.getHight()*20)-(th*20)+1,19,19);
					g.setColor(Color.BLACK);
				}
				else if(th==s.getAktHight()&&tw==s.getAktWith())
				{
					g.setColor(Color.RED);
					g.fillRect(10+(tw*20)+1,50+(s.getHight()*20)-(th*20)+1,19,19);
					g.setColor(Color.BLACK);
				}
				else if(s.getAktHight()-3<th&&th<s.getAktHight()+3&&s.getAktWith()-3<tw&&tw<s.getAktWith()+3&&a[-th+s.getAktHight()+2][tw-(s.getAktWith()-3)][1])
				{
					g.setColor(Color.GREEN);
					g.fillRect(10+(tw*20)+1,50+(s.getHight()*20)-(th*20)+1,19,19);
					g.setColor(Color.BLACK);
				}
			}
		}
		/*
		for(int th = 0;th<6;th++)
		{
			for(int tw = 0;tw<7;tw++)
			{
				g.drawRect(10+(tw*20)+300,50+(th*20),20,20);
				if(th == 2 && tw ==3)
				{
					g.setColor(Color.RED);
					g.fillRect(10+(tw*20)+1+300,50+(th*20)+1,19,19);
					g.setColor(Color.BLACK);
					continue;
				}
				if(a[th][tw][1])
				{
					g.setColor(Color.GREEN);
					g.fillRect(10+(tw*20)+1+300,50+(th*20)+1,19,19);
					g.setColor(Color.BLACK);
					continue;
				}
				if(a[th][tw][0])
				{
					g.setColor(Color.BLUE);
					g.fillRect(10+(tw*20)+1+300,50+(th*20)+1,19,19);
					g.setColor(Color.BLACK);
				}
			}
		}*/
		g.drawString(Main.s.getScore(), 10, 40);
	}
	public Main(){
		super("TETRIS",g);
		s.newGame();
		
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setSize((s.getWith()*20)+20, (s.getHight()*20)+80);
		setLocation(0, 0);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		addWindowListener(new WindowAdapter() {@Override public void windowClosing(WindowEvent e) {s.save();System.exit(0);}});
		repaint();
    }
	
	public static final Logger LOGGER = Logger.getLogger(Main.class);
	static GraphicsConfiguration g;
	public static Main frame;
	
	public static void main(String[] args) {
		PropertyConfigurator.configure(".\\config\\log.properties");
		LOGGER.debug("***START***");
		timer.start();
		
		
		
		frame = new Main();
		
		
		
		frame.paint(frame.getGraphics());
		
		
			
		
		
		LOGGER.debug("****END****");
	}

	@Override
	public void keyReleased(KeyEvent e) {/*A*/}

	@Override
	public void keyTyped(KeyEvent e) {/*A*/}

}
