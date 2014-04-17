package Vue;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gestion.ID3Tags;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import BDD.BDD;
import Vue.ChangeTag.EcouteurModif;

public class CreatUser extends JFrame{

	private JTextField t1;
	private JPasswordField t2;
	private JPasswordField t3;
	private JTextField t4;
	
	public class creatListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			BDD bd = new BDD();
			if(t1.getText().equals("") || t2.getText().equals("")|| t3.getText().equals("")|| t4.getText().equals("")){
				JOptionPane jOP = new JOptionPane();
				jOP.showMessageDialog(null, "Toutes les information n'ont pas été remplis.", "Attention", JOptionPane.WARNING_MESSAGE);
			}
			else{
				if(!t2.getText().equals(t3.getText())){
					JOptionPane jOP = new JOptionPane();
					jOP.showMessageDialog(null, "Les mots de passes ne sont pas identique.", "Attention", JOptionPane.WARNING_MESSAGE);
				}
				else{
					bd.insertUser(t1.getText(), t2.getText(), t4.getText());
					dispose();
				}
			}
		}
		
	}
	
	public CreatUser(){
		this.setTitle("Buntan");
		this.setSize(600, 400);
		this.setIconImage(new ImageIcon("logoProjet.png").getImage());
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
		jPan.setBorder(BorderFactory.createTitledBorder("Create user"));
		Border border = jPan.getBorder();
		
		JLabel labl1 = new JLabel("Id user:");
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
		
		JLabel label2 = new JLabel("password:");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 1;
		containt.gridx = 0;
		containt.gridy = 1;
		jPan.add(label2, containt);
		t2 = new JPasswordField();
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 1;
		containt.gridx = 1;
		containt.gridy = 1;
		jPan.add(t2, containt);
		
		JLabel l3 = new JLabel("rewrite password:");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 1;
		containt.gridx = 0;
		containt.gridy = 2;
		jPan.add(l3, containt);
		t3 = new JPasswordField();
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 1;
		containt.gridx = 1;
		containt.gridy = 2;
		jPan.add(t3, containt);
		
		JLabel l4 = new JLabel("e-mail address");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 1;
		containt.gridx = 0;
		containt.gridy = 3;
		jPan.add(l4, containt);
		t4 = new JTextField();
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 1;
		containt.gridx = 1;
		containt.gridy = 3;
		jPan.add(t4, containt);
		
		JButton b1 = new JButton("Creat acount");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 1;
		containt.gridx = 0;
		containt.gridy = 4;
		b1.addActionListener(new creatListener());
		jPan.add(b1, containt);
		
		return jPan;
	}
}
