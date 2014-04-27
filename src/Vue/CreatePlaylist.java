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

	private BDD bd;
	private int idUser;
	private JList l1;
	private JList l2;
	private JTextField t1;
	
	
	class ActionPlaylist implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String event = e.getActionCommand();
			
			if(event.equals("add")){
				String value = (String) l1.getSelectedValue();
				int idEl = l1.getSelectedIndex();
				if(idEl > -1)
					((DefaultListModel) l1.getModel()).remove(idEl);
				((DefaultListModel) l2.getModel()).addElement(value);
			}
			else{
				String value = (String) l2.getSelectedValue();
				int idEl = l2.getSelectedIndex();
				if(idEl > -1)
					((DefaultListModel) l2.getModel()).remove(idEl);
				((DefaultListModel) l1.getModel()).addElement(value);
			}
		}
		
	}
	
	
	class CreerPlaylist implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(t1.getText().isEmpty()){
				JOptionPane jOP = new JOptionPane();
				jOP.showMessageDialog(null, "Veuillez séléctionner une music", "Attention", JOptionPane.WARNING_MESSAGE);
			}
			else{
				ArrayList name = new ArrayList();
				for(int i = 0; i < l2.getModel().getSize(); i++){
					name.add(l2.getModel().getElementAt(i));
				}
				bd.creerPlaylist(name, idUser, t1.getText());
				JOptionPane jOP = new JOptionPane();
				jOP.showMessageDialog(null, "Playlist créer", "Attention", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		
	}
	
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
	
	
	public CreatePlaylist(int idUser){
		this.idUser = idUser;
		this.bd = new BDD();
		this.setTitle("Buntan");
		this.setSize(700, 500);
		this.setIconImage(new ImageIcon("logoProjet.png").getImage());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		t1 = new JTextField();
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
		ArrayList music = bd.selectMusic(idUser);
		l1 = new JList();
		DefaultListModel model = new DefaultListModel<>();
		for(int i = 0; i< music.size(); i++){
			String[] fich = (String[]) music.get(i);
			String file = fich[0];
			file = file.substring(1,file.length() );
			File f = new File(file); 
			model.addElement(f.getName());
		}
		l1.setModel(model);
		l1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		l1.setLayoutOrientation(JList.VERTICAL);
		l1.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(l1);
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
		l2 = new JList();
		l2.setModel(new DefaultListModel());
		l2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		l2.setLayoutOrientation(JList.VERTICAL);
		l2.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(l2);
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
		b1.addActionListener(new CreerPlaylist());
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
		b1.addActionListener(new ActionPlaylist());
		jPan.add(b1, containt);
	
		JButton b2 = new JButton("remove");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.gridx = 0;
		containt.gridy = 1;
		containt.weighty = 1.0;
		jPan.add(b2, containt);
		return jPan;
	}
	
}
