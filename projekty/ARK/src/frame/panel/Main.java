package frame.panel;

import java.awt.Color;

import javax.swing.JPanel;

public class Main extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final Main INSTANCE = new Main();public static Main getMain() {return INSTANCE;}

	private Main() {
		super();
		add(frame.panel.main.Left.getLeft());
		add(frame.panel.main.Center.getCenter());
		add(frame.panel.main.Right.getRight());
		add(frame.panel.main.Down.getDown());
		setBackground(Color.CYAN);
	}

}
