package plugin;

import javax.swing.JMenuItem;
import javax.swing.JPanel;

public interface IModule { 
	  
	public JMenuItem itemP = new JMenuItem();
	
	public JPanel plug();  
	public String getName(); 
	public JMenuItem getItem();
	public void setIdUser(int iduser);
}