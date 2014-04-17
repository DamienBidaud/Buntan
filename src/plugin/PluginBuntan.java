package plugin;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Model.MyTable;

import com.asprise.util.pdf.PDFReader;


public class PluginBuntan extends JPanel implements IModule {
	
	
	private JTable tablePdf;
	private int idUser;
	private pluginBDD pb = new pluginBDD();

	class ActionListener implements java.awt.event.ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(tablePdf.getSelectedRow() == -1){
				JOptionPane jOP = new JOptionPane();
				jOP.showMessageDialog(null, "Veuillez séléctionner un pdf", "Attention", JOptionPane.WARNING_MESSAGE);
			}
			else{
				tablePdf.getValueAt(tablePdf.getSelectedRow(), 0);
				pluginBDD pb = new pluginBDD();
				ArrayList<String[]> el = pb.selectPdf(1);
				String[] val =  (String[]) el.get(tablePdf.getSelectedRow());
				String name = val[0];
				name = name.substring(1, name.length());
				Desktop d = Desktop.getDesktop();
				try {
					d.open(new File(name));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			

		}
		
	}
	
	
	class MenuLister implements java.awt.event.ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFileChooser filChose = new JFileChooser();
			int retval = filChose.showDialog(filChose, null);
			String file = filChose.getSelectedFile().getAbsolutePath();
			File f = new File(file);
			pb.insertPdf(filChose.getSelectedFile().getName(), file, idUser);
			Object[] donne = new Object[]{tablePdf.getRowCount()+1, f.getName(), "0"};
			((MyTable) tablePdf.getModel()).addRow(donne);
		}
		
	}
	@Override
	public JPanel plug() {
		// TODO Auto-generated method stub
		
		ArrayList<String[]> al = pb.selectPdf(idUser);
		
		String[] title = new String[]{"number", "name"};
		String[][] data = new String[al.size()][title.length];
		for(int i = 0; i<data.length; i++){
			String[] pdf = al.get(i);
			
				File f = new File(pdf[0]);
				data[i][0] = i+"";
				data[i][1] = f.getName();
		}
		MyTable mt = new MyTable(data, title);
		tablePdf = new JTable(mt);
		JPanel jpan = new JPanel();
		JScrollPane  scroll = new JScrollPane(tablePdf);
		scroll.setSize(750, 600);
		jpan.add(scroll);
		JButton play = new JButton("Play");
		play.addActionListener(new ActionListener());
		jpan.add(play);
		return jpan;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "pdf";
	}

	@Override
	public JMenuItem getItem() {
		// TODO Auto-generated method stub
		itemP.setLabel("ajouter pdf");;
		itemP.addActionListener(new MenuLister());
		return itemP;
	}

	@Override
	public void setIdUser(int iduser) {
		// TODO Auto-generated method stub
		idUser = iduser;
	}

}
