package plugin;

import java.awt.List;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import javax.swing.JFileChooser;

public class PluginLoader {

	private static ArrayList<URL> urls = new ArrayList<URL>();
	
	private static ClassLoader classLoader;
	
	private static ArrayList<String> getModulClasses(){
		ArrayList<String> classes = new ArrayList<String>();
		
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfc.showDialog(jfc, null);
		
		File[] files = new File(jfc.getName()).listFiles(new ModuleFilter());
		
		for(File f: files){
			JarFile jf = null;
			
			try{
				jf = new JarFile(f);
				
				Manifest manifest = jf.getManifest();
				
				String modulClasseName = manifest.getMainAttributes().getValue("Module-Class");
				
				classes.add(modulClasseName);
				
				urls.add(f.toURI().toURL());
			}
			catch(IOException e){
				e.printStackTrace(); 
			}
			finally{
				if(jf != null){
					try{
						jf.close();
					}
					catch(IOException e){
						e.printStackTrace();
					}
				}
			}
		}
		return classes;
	}
	
	private static class ModuleFilter implements FileFilter { 
		@Override 
		public boolean accept(File file) { 
			return file.isFile() && file.getName().toLowerCase().endsWith(".jar"); 
		} 
	} 
	
	
	public static ArrayList loadModules(){
		
		ArrayList modules = new ArrayList<>();
		
		
		ArrayList<String> classes = getModulClasses();
		
		
		return modules;
	}
}
