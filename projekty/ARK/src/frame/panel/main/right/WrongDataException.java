package frame.panel.main.right;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WrongDataException extends java.lang.Exception {
	private static final long serialVersionUID = 1L;
	
	private final String value;
	
	public WrongDataException(String message,String Value) {
		super(message+Value);
		this.value = Value;
	}
	public WrongDataException(String message) {
		super(message);
		this.value ="";
	}
	public WrongDataException() {
		super("Property not found");
		this.value ="";
	}
	public String getValue() {
		return value;
	}
	public static void errorFrame(String error)
	{
		JFrame newFrame = new JFrame("Error");
		JPanel newPanel = new JPanel();
		JPanel bPanel = new JPanel();
		JButton b = new JButton("OK");
		JLabel l = new JLabel(error);
		l.setFont(new Font(l.getFont().getName(), Font.PLAIN, 30));
		newPanel.add(l);
		b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				newFrame.dispatchEvent(new WindowEvent(newFrame, WindowEvent.WINDOW_CLOSING));
			}});
		bPanel.setPreferredSize(new Dimension((error.length()*17)-20,40));
		bPanel.add(b);
		newPanel.add(bPanel);
		newFrame.add(newPanel);
		newFrame.setSize((error.length()*17)-10,124);
		newFrame.setResizable(false);
		newFrame.setLocationRelativeTo(null);
		newFrame.setVisible(true);
	}
}
