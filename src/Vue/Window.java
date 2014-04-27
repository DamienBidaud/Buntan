package Vue;

import gestion.ID3Tags;
import it.sauronsoftware.jave.MultimediaInfo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import plugin.IModule;
import plugin.PluginLoader;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import BDD.BDD;
import Model.CelluleTable;
import Model.Music;
import Model.MyTable;

public class Window extends JFrame{

	private ArrayList pluginArray = new ArrayList();
	private JMenuBar menuBar = new JMenuBar();
	private JMenu test1 = new JMenu("Fichier");
	private JMenu test1_2 = new JMenu("Edition");
	private JMenu test2 = new JMenu("Utilisateur");

	private JMenuItem item1 = new JMenuItem("ajouter music");
	private JMenuItem item2 = new JMenuItem("Modifier Fichier");
	private JMenuItem item3 = new JMenuItem("Afficher paramètre utilisateur");
	private JMenuItem item4 = new JMenuItem("Option");
	private JMenuItem item5 = new JMenuItem("ajouter video");
	private JMenuItem item6 = new JMenuItem("Suprimer média");
	private JMenuItem item7 = new JMenuItem("Ajouter Plugin");
	
	private JTable table;
	private JTable tableV;
	private EcouteurAction tableListener = new EcouteurAction();
	private ID3Tags test;
	private LecteurAudio mp3;
	private BDD bd = new BDD();
	JPanel jPan2;
	private JTextArea albume;
	private JTextArea songN;
	private JTextArea genre;
	private JTextArea artiste;
	private JTabbedPane jTab;
	private JComboBox play;
	private JComboBox playlist;
	private int idUser;
	private String pluginFolder;
	
	private PluginLoader pl = new PluginLoader();
	
	
	class WindowCloss implements WindowListener{

		private String name;
		
		public WindowCloss(String name){
			super();
			this.name = name;
		}
		
		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
	
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			refresech(name);
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			refresech(name);
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class EcouteurAction implements ActionListener{
		private int row;
		boolean pauses = false;
		int pausedOnFrame = 0;

		public void setRow(int row){
			this.row = row;
		}
		
		public void setTrack(String track){
			mp3 = new LecteurAudio(track);

		}
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String event = e.getActionCommand();
			ArrayList el;
			String name;
			if(event.equals("Change")){
				if(table.getSelectedRow() == -1){
					JOptionPane jOP = new JOptionPane();
					jOP.showMessageDialog(null, "Veuillez séléctionner une music", "Attention", JOptionPane.WARNING_MESSAGE);
				}
				else{
					
					table.getValueAt(table.getSelectedRow(), 0);
					el = bd.selectMusic(idUser);
					String[] val =  (String[]) el.get(table.getSelectedRow());
					name = val[0];
					name = name.substring(1, name.length());
					ChangeTag  change = new ChangeTag(name);
					change.addWindowListener(new WindowCloss(name));
				
					
				}
				//refreshTabl(table.getSelectedRow(), m1);
			}
			
			if(jTab.getTitleAt(jTab.getSelectedIndex()).equals("Music")){

				 if(event.equals("Play")){
				
					if(table.getSelectedRow() == -1){
						JOptionPane jOP = new JOptionPane();
						jOP.showMessageDialog(null, "Veuillez séléctionner une music", "Attention", JOptionPane.WARNING_MESSAGE);
					}
					else{
						el = bd.selectMusic(idUser);
						for(int i = 0; i < el.size(); i++){
							String[] val =  (String[]) el.get(i);
							name = val[0];
							name = name.substring(1, name.length());
							if(name.contains((CharSequence) table.getValueAt(table.getSelectedRow(), 1))){
								break;
							}
						}
						
						System.out.println(name);
						if(pauses == false){
							setTrack(name);
					       
								mp3.go();
							
						}
						else{
								mp3.go();
							
						}
					}
				 }
				
				else if(event.equals("Pause")){
				
					pausedOnFrame = mp3.suspend();
					pauses = true;
				}
				else{
					mp3.close();
				}
			}
			if(jTab.getTitleAt(jTab.getSelectedIndex()).equals("Video")){
				if(event.equals("Play")){
					if(tableV.getSelectedRow() == -1){
						JOptionPane jOP = new JOptionPane();
						jOP.showMessageDialog(null, "Veuillez séléctionner une music", "Attention", JOptionPane.WARNING_MESSAGE);
					}
					else{
						
						SwingUtilities.invokeLater(new Runnable() {
					      	 
				
							@Override
							public void run() {
								// TODO Auto-generated method stub
								//VideoPlayer vp =  new VideoPlayer("C:\\Users\\Damien\\Videos\\[Ichi Fansub] Seitokai Yakuindomo - 01 VOSTFR HD.avi");
								ArrayList video = bd.selectVideo(idUser);
								String[] file = (String[]) video.get(tableV.getSelectedRow());
								String name = file[0];
								name = name.substring(1, name.length()-1);
								VideoPlayer vp = new VideoPlayer(name);
								vp.start();
							}
					      });
					}
				}
			}
			
		}
	}
	
	class EcouteurMedia implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class menuListeneur implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String event = e.getActionCommand();
			if(event.equals("ajouter music")){
				JFileChooser filChose = new JFileChooser();
				int retval = filChose.showDialog(filChose, null);
				String file = filChose.getSelectedFile().getAbsolutePath();
				bd.insertMusic(filChose.getSelectedFile().getName(), file, idUser);
				ID3Tags tag = new ID3Tags(file);
				Object[] donne = new Object[]{table.getRowCount()+1, tag.getSongName(), tag.getTime(), tag.getArtist(), tag.getAlbum(), tag.getGenre(), "0"};
				((MyTable) table.getModel()).addRow(donne);
			}
			if(event.equals("ajouter video")){
				JFileChooser filChose = new JFileChooser();
				int retval = filChose.showDialog(filChose, null);
				String file = filChose.getSelectedFile().getAbsolutePath();
				File f = new File(file);
				bd.insertVideo(f.getName(), file, idUser);
				Object[] donne = new Object[]{tableV.getRowCount()+1, f.getName(), "0"};
				((MyTable) tableV.getModel()).addRow(donne);
			}
			if(event.equals("Suprimer média")){
				if(jTab.getTitleAt(jTab.getSelectedIndex()).equals("Music")){
					if(table.getSelectedRow() == -1){
						JOptionPane jOP = new JOptionPane();
						jOP.showMessageDialog(null, "Veuillez séléctionner une music", "Attention", JOptionPane.WARNING_MESSAGE);
					}
					else{
						ArrayList el = bd.selectMusic(idUser);
						String[] val =  (String[]) el.get(table.getSelectedRow());
						File f = new File(val[0]);
						String name = f.getName();
						bd.deleteMusic(name, idUser);
						((MyTable) table.getModel()).removeRow(table.getSelectedRow());
					}
				}
				else{
					if(tableV.getSelectedRow() == -1){
						JOptionPane jOP = new JOptionPane();
						jOP.showMessageDialog(null, "Veuillez séléctionner une music", "Attention", JOptionPane.WARNING_MESSAGE);
					}
					else{
						ArrayList el = bd.selectVideo(idUser);
						String[] val =  (String[]) el.get(tableV.getSelectedRow());
						File f = new File(val[0]);
						String name = f.getName();
						bd.deleteMusic(name, idUser);
						((MyTable) tableV.getModel()).removeRow(tableV.getSelectedRow());					}
				}
			}
			if(event.equals("Ajouter Plugin")){
				JFileChooser filChose = new JFileChooser();
				int retval = filChose.showDialog(filChose, null);
				String file = filChose.getSelectedFile().getAbsolutePath();
				pl.setFiles(file);
				try {
					fillPlugin(pl.loadAllSimpleModules());
					Files.copy(new File(file).toPath(), new File(pluginFolder).toPath());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}
		
		public void fillPlugin(IModule[] plugin){
			for(int i = 0;i < plugin.length; i++){
				pluginArray.add(plugin[i]);
				plugin[i].setIdUser(idUser);
				test1.add(plugin[i].getItem());
				jTab.add(plugin[i].plug(), plugin[i].getName());
			}
		}
			
	}
	
	class RechercheListeneur implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			ArrayList media = bd.selectMusic(idUser);
			String file;
			String title[] = {"number","name", "time", "artiste", "album", "genre", "Note"};
			ArrayList <ID3Tags>music = new ArrayList<ID3Tags>();
			ArrayList <ID3Tags>affiche = new ArrayList<ID3Tags>();
			String data[][] = new String[affiche.size()][title.length];
			for(int i = 0; i < media.size(); i++){
				String[] ch = (String[]) media.get(i);
				file = ch[0];
				file = file.substring(1,file.length() );
				music.add(new ID3Tags(file));
			}
			if(!albume.getText().equals("")){
				for(int i = 0; i <music.size(); i++){
					if(music.get(i).getAlbum().equals(albume.getText())){
						affiche.add(music.get(i));
					}
				}
				if(!affiche.isEmpty())
					refreshRescherche(affiche);
			}
			if(!songN.getText().equals("")){
				for(int i = 0; i <music.size(); i++){
					if(music.get(i).getSongName().equals(songN.getText())){
						affiche.add(music.get(i));
						System.out.println(music.get(i).getSongName());
					}
				}
				if(!affiche.isEmpty())
					refreshRescherche(affiche);
			}
			if(!genre.getText().equals("")){
				for(int i = 0; i <music.size(); i++){
					if(music.get(i).getGenre().equals(genre.getText())){
						affiche.add(music.get(i));
					}
				}
				if(!affiche.isEmpty())
					refreshRescherche(affiche);
			}
			if(!artiste.getText().equals("")){
				for(int i = 0; i <music.size(); i++){
					if(music.get(i).getArtist().equals(artiste.getText())){
						affiche.add(music.get(i));
					}
				}
				if(!affiche.isEmpty())
					refreshRescherche(affiche);
			}
			
		}
		
	}
	
	class NoteListeneur implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			bd.updateNote(Integer.parseInt((String) play.getSelectedItem()), idUser, table.getSelectedRow()+1);
		}

		
		
	}
	
	class RefreshListeneur implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String event = e.getActionCommand();
			if(event.equals("Refresh")){
				ArrayList media = bd.selectMusic(idUser);
				
				refresch(media);
			}
		}
		
	}

	class ChargePlaylist implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(!play.getSelectedItem().equals("Pas de playlist créer")){
				System.out.println(playlist.getSelectedItem().toString());
				ArrayList media = bd.selectPlaylistElement(idUser, playlist.getSelectedItem().toString());
				if(!media.isEmpty())
					refresch(media);
			}
		}
		
	}
	
	public Window(int idUser){
		this.setTitle("Buntan");
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(new ImageIcon("./src/images/icone.png").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.idUser = idUser;
		this.initisalise();
		this.checkPlugin();
		this.test1.add(item1);
		item1.addActionListener(new menuListeneur());
		this.test1.add(item5);
		item5.addActionListener(new menuListeneur());
		this.test1.add(item6);
		item6.addActionListener(new menuListeneur());
		this.test1.add(item2);
		this.test2.add(item3);
		this.menuBar.add(test1);
		this.menuBar.add(test2);
		this.setJMenuBar(menuBar);
		this.pluginFolder = "C:\\Users\\Damien\\Documents\\Développement\\Buntan\\Plugin";
		this.setVisible(true);
	}
	
	
	
	public void initisalise(){
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		//c.add(this.getPanelNord(),BorderLayout.NORTH);
		c.add(this.getPanelWest(),BorderLayout.WEST);
		c.add(this.getPanelCenter(),BorderLayout.CENTER);
		c.add(this.getPanelEast(),BorderLayout.EAST);
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
		jPan.setBorder(BorderFactory.createTitledBorder("Recherche"));
		Border border = jPan.getBorder();
		
		JLabel b1 = new JLabel("song name:");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.gridx = 0;
		containt.gridy = 0;
		containt.weighty = 1.0;
		jPan.add(b1, containt);
		songN = new JTextArea();
		songN.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 0.0;
		containt.gridwidth = 0;
		containt.gridx = 0;
		containt.gridy = 1;
		containt.weighty = 1.0;
		jPan.add(songN, containt);
		
		
		JLabel b2 = new JLabel("artiste:");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 3;
		containt.gridx = 0;
		containt.gridy = 2;
		jPan.add(b2, containt);
		artiste = new JTextArea();
		artiste.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 3;
		containt.gridx = 0;
		containt.gridy = 3;
		jPan.add(artiste, containt);
		
		
		
		JLabel b3 = new JLabel("album :");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 3;
		containt.gridx = 0;
		containt.gridy = 4;
		jPan.add(b3, containt);
		albume = new JTextArea();
		albume.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 3;
		containt.gridx = 0;
		containt.gridy = 5;
		jPan.add(albume, containt);
		
		JLabel b4 = new JLabel("genre:");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 3;
		containt.gridx = 0;
		containt.gridy = 6;
		jPan.add(b4, containt);
		genre = new JTextArea();
		genre.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 3;
		containt.gridx = 0;
		containt.gridy = 7;
		jPan.add(genre, containt);
		
		
		JButton b7 = new JButton("Recherche");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 3;
		containt.gridx = 0;
		containt.gridy = 8;
		b7.addActionListener(new RechercheListeneur());
		jPan.add(b7, containt);
		
		JButton b8 = new JButton("Refresh");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 3;
		containt.gridx = 0;
		containt.gridy = 9;
		b8.addActionListener(new RefreshListeneur());
		jPan.add(b8, containt);
		
		JLabel b5 = new JLabel("playlist:");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 3;
		containt.gridx = 0;
		containt.gridy = 10;
		jPan.add(b5, containt);
		playlist = new JComboBox();
		ArrayList pl = bd.selectPlaylist(idUser);
		if(pl.isEmpty())
			playlist.addItem("Pas de playlist créer");
		else{
			for(int i = 0; i < pl.size(); i++){
				playlist.addItem(pl.get(i));
			}
		}
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 3;
		containt.gridx = 0;
		containt.gridy = 11;
		jPan.add(playlist, containt);
		
		JButton b9 = new JButton("Afficher playlist");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 3;
		containt.gridx = 0;
		containt.gridy = 12;
		b9.addActionListener(new ChargePlaylist());
		jPan.add(b9, containt);		
		
		JButton b6 = new JButton("Create playlist");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 3;
		containt.gridx = 0;
		containt.gridy = 13;
		b6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new CreatePlaylist(idUser);
			}
		});
		jPan.add(b6, containt);
		
		return jPan;
	}
	
	public JPanel getPanelEast(){
		JPanel jPan = new JPanel();
		jPan.setLayout(new GridBagLayout());
		GridBagConstraints containt = new GridBagConstraints();
		jPan.setBorder(BorderFactory.createTitledBorder("Action"));
		Border border = jPan.getBorder();
		
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.gridx = 0;
		containt.gridy = 0;
		containt.weighty = 1.0;
		JButton b1 = new JButton("Play");
		b1.addActionListener(tableListener);
		jPan.add(b1, containt);
		
		JButton b4 = new JButton("Pause");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 3;
		containt.gridx = 0;
		containt.gridy = 1;
		b4.addActionListener(tableListener);
		jPan.add(b4, containt);
		
		JButton b2 = new JButton("Stop");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 3;
		containt.gridx = 0;
		containt.gridy = 2;
		b2.addActionListener(tableListener);
		jPan.add(b2, containt);

		JButton b3 = new JButton("Change");
		containt.fill = GridBagConstraints.HORIZONTAL;
		containt.weightx = 0.0;
		containt.weighty = 1.0;
		containt.gridwidth = 3;
		containt.gridx = 0;
		containt.gridy = 3;
		jPan.add(b3, containt);
		b3.addActionListener(tableListener);
		return jPan;
	}
	
	public JPanel getPanelSouth(){
		JPanel jPan = new JPanel();
		
		
		return jPan;
	}
	
	public JTabbedPane getPanelCenter(){
		ArrayList media = bd.selectMusic(idUser);
		jTab = new JTabbedPane();
		jPan2 = new JPanel();
		jTab.add(jPan2,"Music");
		String title[] = {"number","name", "time", "artiste", "album", "genre", "note"};
		Object data[][] = new String[media.size()][title.length];
		play = new JComboBox();
		play.addItem("0");
		play.addItem("1");
		play.addItem("2");
		play.addItem("3");
		play.addItem("4");
		play.addItem("5");
		play.addItemListener(new NoteListeneur());
		String file = "";
		String el;
		for(int i = 0; i < media.size(); i++ ){
			String[] fich = (String[]) media.get(i);
			file = fich[0];
			file = file.substring(1,file.length() );
			test = new ID3Tags(file);
			data[i][0] = i+1+"";
			data[i][1] = test.getSongName();
			data[i][2] = test.getTime();
			data[i][3] = test.getArtist();
			data[i][4] = test.getAlbum();
			data[i][5] = test.getGenre();
			data[i][6] = fich[1];
		}
		jPan2.setLayout(new GridLayout());
		MyTable mt = new MyTable(data, title);
		table = new JTable(mt);
		this.table.getColumn("note").setCellEditor(new DefaultCellEditor(play));
		table.setAutoCreateRowSorter(true);	
		JScrollPane  scroll = new JScrollPane(table);
		scroll.setSize(750, 600);
		jPan2.add(scroll);
		
		JPanel jPan4 = new JPanel();
		
		jTab.add(jPan4, "Video");
		ArrayList video = bd.selectVideo(idUser);
		String title2[] = {"number", "name", "note"};
		String data2[][] = new String[video.size()][title2.length];
		for(int i = 0; i < video.size(); i++){
			String[] vid = (String[]) video.get(i);
			File f = new File(vid[0]);
			data2[i][0] = i+1+"";
			data2[i][1] = f.getName();
			data2[i][2] = vid[1];
		}
		jPan4.setLayout(new GridLayout());
		MyTable mtV = new MyTable(data2, title2);
		tableV = new JTable(mtV);
		this.tableV.getColumn("note").setCellEditor(new DefaultCellEditor(play));
		table.setAutoCreateRowSorter(true);
		JScrollPane scroll2 = new JScrollPane(tableV);
		scroll2.setSize(750, 600);
		jPan4.add(scroll2);
		
		JPanel jPan3 = new JPanel();
		
		jTab.add(jPan3, "Calendar");
		jPan3.add(new Calandar());
		return jTab;
	}
	
	public void refreshTabl(){
		table.removeAll();
		ArrayList media = bd.selectMusic(idUser);
		String title[] = {"name", "time", "artiste", "album", "genre"};
		String data[][] = new String[media.size()][title.length];
		String file = "";
		String el;
		for(int i = 0; i < media.size(); i++ ){
			file = ((String) media.get(i));
			file = file.substring(1,file.length() );
			test = new ID3Tags(file);
			data[i][1] = test.getSongName();
			data[i][2] = test.getTime();
			data[i][3] = test.getArtist();
			data[i][4] = test.getAlbum();
			data[i][5] = test.getGenre();
		}
		table = new JTable(data, title);
		table.revalidate();
		table.repaint();
	}
	
	
	public void refresech(String name){
		ID3Tags tag = new ID3Tags(name);
		table.setValueAt(tag.getSongName(), table.getSelectedRow(), 1);
		table.setValueAt(tag.getTime(), table.getSelectedRow(), 2);
		table.setValueAt(tag.getArtist(), table.getSelectedRow(), 3);
		table.setValueAt(tag.getAlbum(), table.getSelectedRow(), 4);
		table.setValueAt(tag.getGenre(), table.getSelectedRow(), 5);
		table.revalidate();
		table.repaint();
	}
	
	public void refreshRescherche(ArrayList<ID3Tags> aff){
		while(table.getRowCount() != 1){
			for(int i = 0; i < table.getRowCount(); i++){
				((MyTable) table.getModel()).removeRow(i);
				table.revalidate();
				table.repaint();
			}
		}

		for(int i = 0; i < aff.size()-1; i++){
			Object[] donne = new Object[]{i+1, aff.get(i).getSongName(), aff.get(i).getTime(), aff.get(i).getArtist(), aff.get(i).getAlbum(), aff.get(i).getGenre()};
			((MyTable) table.getModel()).addRow(donne);
		}
		table.revalidate();
		table.repaint();
	}

	public void refresch(ArrayList aff){
		String file;
		ArrayList <ID3Tags>music = new ArrayList<ID3Tags>();
		while(table.getRowCount() != 1){
			for(int i = 0; i < table.getRowCount(); i++){
				((MyTable) table.getModel()).removeRow(i);
				table.revalidate();
				table.repaint();
			}
		}
		for(int i = 0; i < aff.size(); i++){
			String[] ch = (String[]) aff.get(i);
			file = ch[0];
			file = file.substring(1,file.length() );
			ID3Tags el = new ID3Tags(file);
			Object[] donne = new Object[]{i+1, el.getSongName(), el.getTime(), el.getArtist(), el.getAlbum(), el.getGenre(), ch[1]};
			((MyTable) table.getModel()).addRow(donne);
		}
		table.revalidate();
		table.repaint();
	}
	
	public void checkPlugin(){
		File dossier = new File("Plugin"); 
		if(!dossier.exists()){
			System.out.println("Existe pas");
		}
		else{
			File[] plug = dossier.listFiles();
			for(int i = 0; i<plug.length; i++){
				pl.setFiles(plug[i].getAbsolutePath());
				try {
					IModule[] plugin = pl.loadAllSimpleModules();
					for(int j = 0;j < plugin.length; j++){
						pluginArray.add(plugin[j]);
						plugin[j].setIdUser(idUser);
						test1.add(plugin[j].getItem());
						jTab.add(plugin[j].plug(), plugin[j].getName());
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
}