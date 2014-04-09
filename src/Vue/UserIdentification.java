package Vue;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.text.PasswordView;

import BDD.BDD;
import Vue.ChangeTag.EcouteurModif;

public class UserIdentification extends JFrame{

	private JTextField t1;
	private JPasswordField t2;
	
	public class UserListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			BDD bd = new BDD();
			String event = e.getActionCommand();
			if(event.equals("Connection")){
				if(bd.idUser(t1.getText(), t2.getText())){
					Window w = new Window(bd.idUserExpo(t1.getText()));
					dispose();
				}
				else{
					JOptionPane jOP = new JOptionPane();
					jOP.showMessageDialog(null, "Le mot de passe ou l'id utilisateur n'est pas correcte.", "Attention", JOptionPane.WARNING_MESSAGE);
				}
			}
			else{
				CreatUser cu = new CreatUser();	
			}
		}
	}
	
	public UserIdentification(){
		this.setTitle("Buntan");
		this.setSize(600, 480);
		//this.setIconImage(new ImageIcon(this.getClass().getResource("logoProjet.png")).getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(new ImageIcon("./src/images/icone.png").getImage());
		this.initialise();
		this.setVisible(true);
	}
	
	public void initialise(){
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());		
		c.add(Formulaire(), BorderLayout.CENTER);
	}
	
	public JPanel Formulaire(){
		JPanel jPan = new JPanel();
		jPan.setLayout(new GridBagLayout());
		GridBagConstraints containt = new GridBagConstraints();
		
		containt.insets = new Insets(6, 3, 3, 3);
		
		JLabel image = new JLabel(new ImageIcon("./src/images/buntan.jpg"));
		JButton b = new JButton("Image");
		containt.gridx = 1;
		containt.gridy = 0;
		containt.gridwidth = 2;
		containt.fill = GridBagConstraints.HORIZONTAL;
		jPan.add(image, containt);
		
		JLabel labl1 = new JLabel("User name:");
		containt.gridx = 1;
		containt.gridy = 1;
		containt.gridwidth = 1;
		jPan.add(labl1, containt);
		t1 = new JTextField();
		containt.gridx = 2;
		containt.gridy = 1;
		containt.gridwidth = 1;
		jPan.add(t1, containt);
		
		JLabel label2 = new JLabel("Password:");
		containt.gridx = 1;
		containt.gridy = 2;
		containt.gridwidth = 1;
		jPan.add(label2, containt);
		t2 = new JPasswordField();
		containt.gridx = 2;
		containt.gridy = 2;
		containt.gridwidth = 1;
		jPan.add(t2, containt);
		
		JButton b1 = new JButton("Connection");
		containt.gridx = 1;
		containt.gridy = 3;
		containt.gridwidth = 1;
		b1.addActionListener(new UserListener());
		jPan.add(b1, containt);
		
		JButton b2 = new JButton("Create acount");
		containt.gridx = 2;
		containt.gridy = 3;
		containt.gridwidth = 1;
		b2.addActionListener(new UserListener());
		jPan.add(b2, containt);
		return jPan;
	}
}
