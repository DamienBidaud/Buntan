package Vue;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Container;
import java.net.MalformedURLException;
import java.net.URL;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.RealizeCompleteEvent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.DirectAudioPlayerComponent;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
 

public class VideoPlayer extends JPanel {

    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

	
	public VideoPlayer(String file){

		
		 NativeLibrary.addSearchPath(
	                RuntimeUtil.getLibVlcLibraryName(), "c:/Program Files/VideoLAN/VLC/");
	        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
            JFrame frame = new JFrame("Player");

            DirectAudioPlayerComponent dapc = new DirectAudioPlayerComponent(file, ALLBITS, ABORT);
            mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
            frame.setContentPane(mediaPlayerComponent);

            frame.setLocation(100, 100);
            frame.setSize(1050, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            
            
            mediaPlayerComponent.getMediaPlayer().playMedia(file);

	}
	

}