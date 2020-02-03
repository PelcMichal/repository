package game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TActiveFrame extends JPanel{
	private static final long serialVersionUID = 1L;

	
	
	public TActiveFrame() {
		super();
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		setSize(141, 121);
		setLocation((Main.s.getAktWith()*20)-60+20,((Main.s.getHight()-Main.s.getAktHight())*20)-20);
		boolean[][][] a = Main.s.getActiveField();
		
		int th;if(Main.s.getAktHight()-Main.s.getHight()+4>0) {th = Main.s.getAktHight()-Main.s.getHight()+3;}else{th =0;}
		int thm;if(Main.s.getAktHight()-3<0) {thm = Main.s.getAktHight()+3;} else {thm = 6;}
		int tw;if(Main.s.getAktWith()<3) {tw = -Main.s.getAktWith()+3;} else {tw = 0;}
		int twm;if(Main.s.getAktWith()+4>Main.s.getWith()) {twm = -Main.s.getAktWith()+Main.s.getWith()+3;}else {twm = 7;}
			
		for(;th<thm;th++)
		{
			for(int ttw = tw;ttw<twm;ttw++)
			{
				g.drawRect(ttw*20,th*20,20,20);
				if(a[th][ttw][0])
				{
					g.setColor(Color.BLUE);
					g.fillRect((ttw*20)+1,(th*20)+1,19,19);
					g.setColor(Color.BLACK);
				}else if(th == 2 && ttw ==3)
				{
					g.setColor(Color.RED);
					g.fillRect((ttw*20)+1,(th*20)+1,19,19);
					g.setColor(Color.BLACK);
				}else if(a[th][ttw][1])
				{
					g.setColor(Color.GREEN);
					g.fillRect((ttw*20)+1,(th*20)+1,19,19);
					g.setColor(Color.BLACK);
				}
				
				
			}
		}
	}
}
