package Vue;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

public class Window extends JFrame{

	
	public Window(){
		this.setTitle("Buntan");
		this.setSize(900, 800);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.initisalise();
		this.setVisible(true);
	}
	
	
	public void initisalise(){
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		//c.add(this.getPanelNord(),BorderLayout.NORTH);
		c.add(this.getPanelWest(),BorderLayout.WEST);
		c.add(this.getPanelCenter(),BorderLayout.CENTER);
		//c.add(this.getPanelEast(),BorderLayout.EAST);
		//c.add(this.getPanelSouth(),BorderLayout.SOUTH);
		
	}
	
	public JPanel getPanelNord(){
		JPanel jPan = new JPanel();
		
		return jPan;
	}
	public JPanel getPanelWest(){
		JPanel jPan = new JPanel();
		jPan.setLayout(new GridBagLayout());
		GridBagConstraints containt = new GridBagConstraints();
		jPan.setBorder(BorderFactory.createTitledBorder("Media"));
		Border border = jPan.getBorder();
		
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.gridx = 0;
		containt.gridy = 0;
		containt.weighty = 1.0;
		JButton b1 = new JButton("Music");
		jPan.add(b1, containt);
		
		JButton b2 = new JButton("Video");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 3;
		containt.gridx = 0;
		containt.gridy = 1;
		jPan.add(b2, containt);
		return jPan;
	}
	
	public JPanel getPanelEast(){
		JPanel jPan = new JPanel();
		
		
		return jPan;
	}
	
	public JPanel getPanelSouth(){
		JPanel jPan = new JPanel();
		
		
		return jPan;
	}
	
	public JPanel getPanelCenter(){
		JPanel jPan = new JPanel();
		jPan.setBorder(BorderFactory.createTitledBorder("Music"));
		Border border = jPan.getBorder();
		String title[] = {"name", "time", "artiste", "album"};
		String data[][] = {{"You are the best song ever", "", "", ""}, {"Hakunamatata", "", "", ""}};
		jPan.setLayout(new FlowLayout());
		JTable table = new JTable(data, title);
		JScrollPane  scroll = new JScrollPane(table);
		scroll.setSize(750, 600);
		jPan.add(scroll);
		return jPan;
	}
}
