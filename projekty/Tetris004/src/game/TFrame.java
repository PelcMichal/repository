package game;

import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TFrame extends JFrame implements KeyListener{
	private static final long serialVersionUID = 1L;
	static GraphicsConfiguration g;
	
	private JMenuBar menuBars; 
	private JMenu menu; 
    private JMenuItem newGame; 
    private JMenuItem resume;
    private JMenuItem highScoreBoard;
    private JMenuItem save;
    private JMenuItem pause;
    private JMenuItem help;
    private JMenuItem exit;
	
	public TFrame(){
		super("TETRIS",g);
		
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setSize((Main.s.getWith()*20)+50, (Main.s.getHight()*20)+110);
		setLocation(0, 0);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		addWindowListener(new WindowAdapter() {@Override public void windowClosing(WindowEvent e) {Main.s.save();System.exit(0);}});
		
		menuBars = new JMenuBar(); 
		menu = new JMenu("Menu"); 
		newGame = new JMenuItem("New Game        (F2)");
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.timer.setDelay((int)(100000/(100+Main.s.getTilesFallen())));
				Main.timer.restart();
				Main.timer.start();
				Main.s.newGame();
				Main.s.updateScreen();
				Main.tHighSoreFrame.setVisible(false);
				Main.tScoreBoardFrame.setVisible(false);
				Main.tHighSoreFrame.setVisible(false);
				Main.tWholeFrame.setVisible(true);
				
				
				}
		}); 
		resume = new JMenuItem("Resume");
		resume.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.tHighSoreFrame.setVisible(false);
				Main.tHighSoreFrame.setVisible(false);
				Main.tScoreBoardFrame.setVisible(false);
				Main.tWholeFrame.setVisible(true);
				Main.timer.start();
			}
		});
		highScoreBoard = new JMenuItem("High Score");
		highScoreBoard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.tHighSoreFrame.setVisible(false);
				Main.tWholeFrame.setVisible(false);
				Main.tHighSoreFrame.setVisible(false);
				Main.tScoreBoardFrame.setVisible(true);
				Main.timer.stop();
				Main.tScoreBoardFrame.paint(Main.tScoreBoardFrame.getGraphics());
			}
		});
        save = new JMenuItem("Save                   (S)");
        save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.s.save();
			}
		}); 
        pause = new JMenuItem("Pause       	         (P)");
        pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.timer.stop();
			}
		}); 
        help = new JMenuItem("Help                    (H)"); 
        help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//to doo
			}
		}); 
        exit = new JMenuItem("Exit"); 
        exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.s.save();System.exit(0);
			}
		}); 
        menu.add(newGame); 
        menu.add(resume);
        menu.add(highScoreBoard);
        menu.add(save); 
        menu.add(pause); 
        menu.add(help); 
        menu.add(exit); 
        menuBars.add(menu); 
        setJMenuBar(menuBars); 
        setVisible(true);
    }
	
	
	
	
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode()== KeyEvent.VK_NUMPAD6)
		{
			Main.s.tryMoveRight();
		}
        else if(e.getKeyCode()== KeyEvent.VK_NUMPAD4)
        {
        	Main.s.tryMoveLeft();
        }
        else if(e.getKeyCode()== KeyEvent.VK_NUMPAD5)
        {
        	Main.s.tryFall();
        	Main.timer.setDelay((int)(100000/(100+Main.s.getTilesFallen())));
        	Main.timer.restart();
        }
        else if(e.getKeyCode()== KeyEvent.VK_NUMPAD7)
        {
        	Main.s.tryRotateLeft();
        }
        else if(e.getKeyCode()== KeyEvent.VK_NUMPAD9)
        {
        	Main.s.tryRotateRight();
        }
        else if(e.getKeyCode()== KeyEvent.VK_P)
        {
        	pause.doClick();
        }else if(e.getKeyCode()== KeyEvent.VK_S)
        {
        	save.doClick();
        }else if(e.getKeyCode()== KeyEvent.VK_F2)
        {
        	newGame.doClick();
        }else if(e.getKeyCode()== KeyEvent.VK_H)
        {
        	help.doClick();
        }
	}
	
	@Override
	public void keyReleased(KeyEvent e) {/*A*/}

	@Override
	public void keyTyped(KeyEvent e) {/*A*/}
}
