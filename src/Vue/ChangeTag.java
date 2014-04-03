package Vue;

import gestion.ID3Tags;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Model.Music;
import Vue.Window.EcouteurAction;

public class ChangeTag extends JFrame{
	
	private ID3Tags music;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	
	class EcouteurModif implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String event = e.getActionCommand();
			
			if(event.equals("Save")){
				music.setSongName(t1.getText());
				music.setArtist(t2.getText());
				music.setAlbum(t3.getText());
				music.setGenre(t4.getText());
				dispose();
			}
			else{
				t1.setText(music.getSongName());
				t2.setText(music.getArtist());
				t3.setText(music.getAlbum());
				t4.setText(music.getGenre());
			}
		}
		
	}
	
	public ChangeTag(String m){
		music = new ID3Tags(m);
		this.setTitle(music.getSongName());
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);		
		this.initisalise();
	}

	public void initisalise() {
		// TODO Auto-generated method stub
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());		
		c.add(Formulaire(), BorderLayout.CENTER);
	}
	
	public JPanel Formulaire(){
		JPanel jPan = new JPanel();
		jPan.setLayout(new GridBagLayout());
		GridBagConstraints containt = new GridBagConstraints();
		jPan.setBorder(BorderFactory.createTitledBorder("Modification"));
		Border border = jPan.getBorder();
		
		JLabel labl1 = new JLabel("Song name:");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.gridx = 0;
		containt.gridy = 0;
		containt.weighty = 1.0;
		jPan.add(labl1, containt);
		t1 = new JTextField(music.getSongName());
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 1.0;
		containt.weighty = 1.0;
		containt.gridwidth = 1;
		containt.gridx = 1;
		containt.gridy = 0;
		jPan.add(t1, containt);
		
		JLabel label2 = new JLabel("Artist:");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 1;
		containt.gridx = 0;
		containt.gridy = 1;
		jPan.add(label2, containt);
		t2 = new JTextField(music.getArtist());
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 1;
		containt.gridx = 1;
		containt.gridy = 1;
		jPan.add(t2, containt);
		
		JLabel l3 = new JLabel("Album name:");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 1;
		containt.gridx = 0;
		containt.gridy = 2;
		jPan.add(l3, containt);
		t3 = new JTextField(music.getAlbum());
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 1;
		containt.gridx = 1;
		containt.gridy = 2;
		jPan.add(t3, containt);
		
		JLabel l4 = new JLabel("Genre");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 1;
		containt.gridx = 0;
		containt.gridy = 3;
		jPan.add(l4, containt);
		t4 = new JTextField(music.getGenre());
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 1;
		containt.gridx = 1;
		containt.gridy = 3;
		jPan.add(t4, containt);
		
		JButton b1 = new JButton("Save");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 1;
		containt.gridx = 0;
		containt.gridy = 4;
		b1.addActionListener(new EcouteurModif());
		jPan.add(b1, containt);
		JButton b2 = new JButton("Default value");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 1;
		containt.gridx = 2;
		containt.gridy = 4;
		b2.addActionListener(new EcouteurModif());
		jPan.add(b2, containt);
		return jPan;
	}
	
	

}
