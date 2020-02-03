package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class THighSoreFrame extends JPanel{
	private static final long serialVersionUID = 1L;
	private JButton button;
	private JTextField t; 
	public JLabel score;
	private JLabel a;
	
	public THighSoreFrame() {
		super();
		setVisible(false);
		setLocation(0, 0);
		t = new JTextField(20); 
		button = new JButton("   Save high score   ");
		score = new JLabel();
		a = new JLabel("Set your name");
		
		
		add(a);
		add(t);
		add(button);add(score);
		button.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Main.s.saveHighScore(t.getText());
			Main.tHighSoreFrame.setVisible(false);
			Main.tScoreBoardFrame.setVisible(true);
			Main.tScoreBoardFrame.repaint();
		}
		
	});
		
	}
	
	
	
}
