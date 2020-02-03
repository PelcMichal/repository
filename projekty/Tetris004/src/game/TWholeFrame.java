package game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TWholeFrame extends JPanel{
	private static final long serialVersionUID = 1L;

	public TWholeFrame() {
		super();
		setVisible(false);
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		boolean[][] f = Main.s.getField();
		for(int th = 0;th<Main.s.getHight();th++)
		{
			for(int tw = 0;tw<Main.s.getWith();tw++)
			{
				g.drawRect((tw*20)+20,(Main.s.getHight()*20)-(th*20)+20,20,20);
				if(f[th][tw])
				{
					g.setColor(Color.BLUE);
					g.fillRect((tw*20)+1+20,(Main.s.getHight()*20)-(th*20)+1+20,19,19);
					g.setColor(Color.BLACK);
				}
			}
		}
		g.drawString(Main.s.getScore(), 20, 30);
	}
}
