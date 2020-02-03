package frame.panel.main.center;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class DinoRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;
	public DinoRenderer() {
		super();
	}
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        DinoModel model = (DinoModel) table.getModel();
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setBackground(model.getColour(column,value.toString()));
        
        
        if(c.getBackground() ==null||c.getBackground().equals(Color.WHITE))
        {
        	c.setForeground(Color.BLACK);
        	if(Dino.getDino().getSelectedRow() == row)
        		c.setBackground(new Color(150, 200, 250));
        }
        else if(c.getBackground().equals(Color.YELLOW))
        {
        	c.setForeground(Color.BLACK);
        }else 
        {
        	c.setForeground(c.getBackground());
        }
        
        
        
        return c;
    }
}
