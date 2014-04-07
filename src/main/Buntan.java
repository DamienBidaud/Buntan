package main;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

import plugin.PluginLoader;
import Vue.UserIdentification;
import Vue.VideoPlayer;
public class Buntan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//UserIdentification id = new UserIdentification();
		
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfc.showDialog(jfc, null);
	}
}


