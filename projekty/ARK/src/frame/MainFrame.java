package frame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.apache.log4j.Logger;

public class MainFrame extends JFrame implements KeyListener{
	private static final long serialVersionUID = 1L;
	private static final MainFrame INSTANCE = new MainFrame("");public static MainFrame getMainFrame() {return INSTANCE;}
	public static final Logger LOGGER = Logger.getLogger(MainFrame.class);
	
    private JMenuBar mb; 
    private JMenu menu;
    private JMenuItem server; 
    private JMenuItem spicies; 
    
    
	
	private MainFrame(String arg0){
		super(arg0);
		mb = new JMenuBar();
		menu = new JMenu("Menu");
		server = new JMenuItem("Add or Edit Server");
		spicies = new JMenuItem("Add or Edit Spicies");
		menu.add(server);
		menu.add(spicies);
		mb.add(menu);
		setJMenuBar(mb);
		addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setSize(1280,720);
        setResizable(false);
        setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	LOGGER.debug("****END****");
		    	System.exit(0);
		    }
		});
		setTitle("ARK Survival Evolved Dinosaur database");
		add(frame.panel.Main.getMain());
		setVisible(true);
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
