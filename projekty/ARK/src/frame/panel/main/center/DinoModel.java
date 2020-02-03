package frame.panel.main.center;

import java.awt.Color;

import javax.swing.table.DefaultTableModel;

public class DinoModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	private int[] maxValues= new int[6];
	private boolean single = false;
	
	public DinoModel() {
		super();
	}
    public Color getColour(int Column,String s) {
    	
    	if(3<Column)
    	{
    		if (single)
    		{
    			if(Integer.valueOf(s).equals(maxValues[Column-4]))
    			{
    				return Color.YELLOW;
    			}
    			
    		}
    	}else if (Column ==1)
    	{
    		if (s.equals("T")) {return Color.GREEN;}
    		else if (s.equals("F")) {return Color.RED;}
    	}else if(Column ==2)
    	{
    		if (s.equals("M")) {return Color.BLUE;}
    		else if (s.equals("F")) {return Color.MAGENTA;}
    		else if (s.equals("NA")) {return Color.GRAY;}
    	}
        return null;
    }
    public void eanble(int[] maxValues)
    {
    	this.maxValues = maxValues;
    	single = true;
    }
    public void diseanble()
    {
    	single = false;
    }
    
    
    @Override
    public boolean isCellEditable(int row, int column) {
       return false;
    }
}