package plugin;

import java.awt.List;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import javax.swing.JFileChooser;

import com.sun.media.ModuleListener;

public class PluginLoader {

	private String files;
	
	private ArrayList classPlugin;
	
	public PluginLoader(){
		this.classPlugin = new ArrayList<>();
	}

	public PluginLoader(String files){
		this.files = files;
	}
	
	public void setFiles(String files){
		this.files = files;	
	}
	
	public PluginBuntan[] loadAllSimpleModules() throws Exception{
		this.initializeLoader();
		PluginBuntan[] tmpPlugin = new PluginBuntan[this.classPlugin.size()];
		
		for(int i = 0; i < tmpPlugin.length; i++){
			tmpPlugin[i] = (PluginBuntan) ((Class)this.classPlugin.get(i)).newInstance();
		}
		
		return tmpPlugin;
		
	}
	
	public void initializeLoader() throws Exception{
		if(this.files == null || this.files == null ){
			throw new Exception("Pas de fichier spécifié");
		}
		
		if(this.classPlugin.size() != 0 ){
			return ;
		}
		File f = new File(this.files);
		//Pour charger le .jar en memoire
		URLClassLoader loader;
		//Pour la comparaison de chaines
		String tmp = "";
		//Pour le contenu de l'archive jar
		Enumeration enumeration;
		//Pour déterminé quels sont les interfaces implémentées
		Class tmpClass = null;
		
			
						
			URL u = f.toURI().toURL();

			//On créer un nouveau URLClassLoader pour charger le jar qui se trouve ne dehors du CLASSPATH
			loader = new URLClassLoader(new URL[] {u}); 
			
			//On charge le jar en mémoire
			JarFile jar = new JarFile(f.getAbsolutePath());

			//On récupére le contenu du jar
			enumeration = jar.entries();
			
			do{
				
				tmp = enumeration.nextElement().toString();

				//On vérifie que le fichier courant est un .class (et pas un fichier d'informations du jar )
				if(tmp.length() > 6 && tmp.substring(tmp.length()-6).compareTo(".class") == 0) {
					
					tmp = tmp.substring(0,tmp.length()-6);
					tmp = tmp.replaceAll("/",".");
					
					tmpClass = Class.forName(tmp ,true,loader);
					
					for(int i = 0 ; i < tmpClass.getInterfaces().length; i ++ ){
						
						//Une classe ne doit pas appartenir à deux catégories de plugins différents. 
						//Si tel est le cas on ne la place que dans la catégorie de la première interface correct
						// trouvée
						//System.out.println(tmpClass.getInterfaces()[i].getName().toString());

						if(tmpClass.getInterfaces()[i].getName().toString().equals("plugin.IModule") ) {
							this.classPlugin.add(tmpClass);
						}
						
					}
					
				}
			
			
		
		}while(enumeration.hasMoreElements());
		
	}
}
