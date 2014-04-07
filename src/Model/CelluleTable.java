package Model;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CelluleTable extends DefaultTableCellRenderer {

	 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		    if (value instanceof JButton)
		      return (JButton) value;
		    //Lignes ajoutées
		    else if(value instanceof JComboBox)
		      return (JComboBox) value;
		    else
		      return null;
		  }
}
