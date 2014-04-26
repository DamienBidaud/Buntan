package Vue;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import Vue.UserIdentification.UserListener;
import BDD.BDD;


public class Event extends JFrame implements ActionListener {
	private String m = "                 ";
	private String p = "              ";
	private JTextArea jta1;
	private JTextField z1;
	private JButton b1, b2;
	private JLabel jlerror;
	private String date;
	private JLabel jl[];
	private JTextField jtf;

	public Event(String d){
		this.setTitle("All Events today");
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(new ImageIcon("./src/images/icone.png").getImage());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.initisalise(d);
		this.setVisible(true);
	}
	
	
	public void initisalise(String d){
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(this.getPanelNord(d),BorderLayout.NORTH);
		c.add(this.getPanelWest(),BorderLayout.WEST);
		c.add(this.getPanelCenter(),BorderLayout.CENTER);
		c.add(this.getPanelSouth(),BorderLayout.SOUTH);
		c.add(this.getPanelEast(),BorderLayout.EAST);
		
	}
	
	public JPanel getPanelNord(String d){
		this.date = d;
		JPanel jPan = new JPanel();
		jPan.setLayout(new GridLayout(2,1));
		
		JLabel titre = new JLabel("All Events Today", SwingConstants.CENTER);
		Font font = new Font("Arial",Font.BOLD,20);
		titre.setFont(font);
		jPan.add(titre);
		String dateJour = this.date;
		JLabel jl1 = new JLabel(dateJour, SwingConstants.CENTER);
		Font font2 = new Font("Arial",Font.BOLD,14);
		jl1.setFont(font2);
		jPan.add(jl1);
		
		return jPan;
	}
	
	public JPanel getPanelCenter(){
		JPanel jPan = new JPanel();
		jPan.setLayout(new GridLayout(3,1));
		
		JLabel jl1 = new JLabel("List of events :");
		Font font = new Font("Arial",Font.BOLD,14);
		jl1.setFont(font);
		jPan.add(jl1);
		
		JLabel jl2 = new JLabel("Number"+p+"Name"+m+m+"Description"+m+"Date");
		Font font1 = new Font("Arial",Font.BOLD,13);
		jl2.setFont(font1);
		jPan.add(jl2);
		
		// Nouveau JPanel
		JPanel jp = new JPanel();
		BDD bd = new BDD();
		ArrayList arrList = new ArrayList();
		arrList = bd.afficheEvent(UserIdentification.IDUSER, this.date);
		
		// x nombre d'événements à afficher
		int x = arrList.size();
		jp.setLayout(new GridLayout(x,1));
		
		jl = new JLabel[x];
		for(int i=0; i<x; i++) {
			String[] event = (String[]) arrList.get(i);
			jl[i] = new JLabel((i+1)+m+m+event[0]+m+m+event[1]+m+event[2]);
			jp.add(jl[i]);
		}
		
		jPan.add(jp);
		
		return jPan;
	}
	
	public JPanel getPanelSouth(){
		JPanel jPan = new JPanel();
		jPan.setLayout(new GridLayout(6,1));
		
		JLabel jl1 = new JLabel(p+"Create an event :");
		Font font = new Font("Arial",Font.BOLD,14);
		jl1.setFont(font);
		jPan.add(jl1);
		
		JPanel jp4 = new JPanel();
		jp4.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel jl4 = new JLabel(m+"Event name :", SwingConstants.LEFT);
		jp4.add(jl4);
		jtf = new JTextField();
		jtf.setPreferredSize(new Dimension(150, 20));
		jp4.add(jtf);
		
		jPan.add(jp4);
		
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel jl2 = new JLabel(m+"Event description :", SwingConstants.LEFT);
		jp.add(jl2);
		jta1 = new JTextArea();
		JScrollPane scroll = new JScrollPane(jta1); 
		scroll.setPreferredSize(new Dimension(400, 45));
		jp.add(scroll);
		
		jPan.add(jp);
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel jl3 = new JLabel(m+"Date event (format : yyyy/mm/dd) :", SwingConstants.LEFT);
		jp1.add(jl3);
		z1 = new JTextField(this.date);
		z1.setEditable(false);
		z1.setPreferredSize(new Dimension(100, 20));
		jp1.add(z1);
		
		jPan.add(jp1);
		
		// Error message
		JPanel jp3 = new JPanel();
		jp3.setLayout(new FlowLayout(FlowLayout.LEFT));
		jlerror = new JLabel("");
		jp3.add(jlerror);
		
		jPan.add(jp3);
		
		// Buttons submit and cancel
		JPanel jp2 = new JPanel();
		jp2.setLayout(new FlowLayout());
		b1 = new JButton("Submit");
		b1.addActionListener(this);
		jp2.add(b1);
		
		b2 = new JButton("Cancel");
		b2.addActionListener(this);
		jp2.add(b2);
		
		jPan.add(jp2);
		
		return jPan;
	}
	
	public JPanel getPanelWest(){
		JPanel jPan = new JPanel();
		JLabel jl = new JLabel(m);
		jPan.add(jl);
		return jPan;
	}
	
	public JPanel getPanelEast(){
		JPanel jPan = new JPanel();
		JLabel jl = new JLabel(m);
		jPan.add(jl);
		return jPan;
	}
	
	// Gestion des événements
	public void actionPerformed(ActionEvent arg0) {    
        if(arg0.getSource() == b2) {
        	jta1.setText("");
        	jtf.setText("");
        } else if (arg0.getSource() == b1) {
        	BDD bd = new BDD();
			if(jta1.getText().length() > 0) {
				if (z1.getText().length() > 0 && z1.getText().length() < 11) {
					bd.insertEvent(jtf.getText(), jta1.getText(), z1.getText());
					System.out.println("Event added OK");
					// User ID
					int id_user = UserIdentification.IDUSER;
					// User Event
					int id_event = bd.getIdEvent(jta1.getText(), z1.getText());
					bd.insertRelationEventToUser(id_user, id_event);
				} else {
					jlerror.setText(m+"Date format invalid !");
				}
			} else {
				jlerror.setText(m+"Incorrect syntax !");
			}
        }
    }
}
