package Vue;

import gestion.ID3Tags;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

import Vue.Window.menuListeneur;

public class CreatePlaylist extends JFrame{

	public CreatePlaylist(){
		this.setTitle("Buntan");
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(new ImageIcon("./src/images/icone.png").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initisalise();
		this.setVisible(true);
	}
	
	
	public void initisalise(){
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(this.getPanelNord(),BorderLayout.NORTH);
		c.add(this.getPanelWest(),BorderLayout.WEST);
		c.add(this.getPanelCenter(),BorderLayout.CENTER);
		c.add(this.getPanelEast(),BorderLayout.EAST);
		c.add(this.getPanelSouth(),BorderLayout.SOUTH);
		
	}
	
	
	public JPanel getPanelNord(){
		JPanel jPan = new JPanel();
		jPan.setLayout(new GridBagLayout());
		GridBagConstraints containt = new GridBagConstraints();
		
		JLabel labl1 = new JLabel("Playlist name:");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.gridx = 0;
		containt.gridy = 0;
		containt.weighty = 1.0;
		jPan.add(labl1, containt);
		JTextField t1 = new JTextField();
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 1.0;
		containt.weighty = 1.0;
		containt.gridwidth = 1;
		containt.gridx = 1;
		containt.gridy = 0;
		jPan.add(t1, containt);
		
		return jPan;
	}

	public JPanel getPanelWest(){
		JPanel jPan = new JPanel();
		jPan.setLayout(new GridBagLayout());
		GridBagConstraints containt = new GridBagConstraints();
		String[] data = {"Song 1", "Song 2", "Song 3", "Song 4"};
		JList b1 = new JList(data);
		b1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		b1.setLayoutOrientation(JList.VERTICAL);
		b1.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(b1);
		listScroller.setPreferredSize(new Dimension(250, 80));
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.gridx = 0;
		containt.gridy = 0;
		containt.weighty = 1.0;
		jPan.add(listScroller, containt);
	
		return jPan;
	}
	
	public JPanel getPanelEast(){
		JPanel jPan = new JPanel();
		jPan.setLayout(new GridBagLayout());
		GridBagConstraints containt = new GridBagConstraints();
		
		String[] data = { "Song 3"};
		JList b1 = new JList(data);
		b1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		b1.setLayoutOrientation(JList.VERTICAL);
		b1.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(b1);
		listScroller.setPreferredSize(new Dimension(250, 80));
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.gridx = 0;
		containt.gridy = 0;
		containt.weighty = 1.0;
		jPan.add(listScroller, containt);
		
			return jPan;
	}
	
	public JPanel getPanelSouth(){
		JPanel jPan = new JPanel();
		jPan.setLayout(new GridBagLayout());
		GridBagConstraints containt = new GridBagConstraints();
		
		JButton b1 = new JButton("Create Playliste");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.gridx = 0;
		containt.gridy = 0;
		containt.weighty = 1.0;
		jPan.add(b1, containt);
		
		
		return jPan;
	}
	
	public JPanel getPanelCenter(){
		JPanel jPan = new JPanel();
		jPan.setLayout(new GridBagLayout());
		GridBagConstraints containt = new GridBagConstraints();
		
		JButton b1 = new JButton("add");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.gridx = 0;
		containt.gridy = 0;
		containt.weighty = 1.0;
		jPan.add(b1, containt);
		
		return jPan;
	}
	
}
