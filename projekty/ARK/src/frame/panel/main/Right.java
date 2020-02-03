package frame.panel.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Right extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final Right INSTANCE = new Right();public static Right getRight() {return INSTANCE;}
	
	
	
	private Right()
	{
		super();
		JButton dino = new JButton("Dino");
		JButton server = new JButton("Server and Spicies");
		dino.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.panel.main.right.ServerSpicies.getServerSpicies().setVisible(false);
				frame.panel.main.right.Dino.getDino().setVisible(true);
			}});
		server.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.panel.main.right.Dino.getDino().setVisible(false);
				frame.panel.main.right.ServerSpicies.getServerSpicies().setVisible(true);
			}});
		dino.setPreferredSize(new Dimension(120,25));
		server.setPreferredSize(new Dimension(120,25));
		add(dino);
		add(server);
		
		
		
		add(frame.panel.main.right.Dino.getDino());
		add(frame.panel.main.right.ServerSpicies.getServerSpicies());
		//add();
		
		setPreferredSize(new Dimension(265,615));
		setBackground(Color.BLACK);
	}
}
