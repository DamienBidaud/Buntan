package Vue;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.DefaultFullScreenStrategy;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
 

public class VideoPlayer extends JFrame{

    //private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
    
	
	 private final int rows = 1;
	    private final int cols = 4;

	    private final Frame mainFrame;

	    private final ArrayList<PlayerInstance> players = new ArrayList<PlayerInstance>();

	    private final MediaPlayerFactory factory;
	    
	    private String[] medias;
	    
	public VideoPlayer(String file){
		
		medias =  new String[]{file};
		 NativeLibrary.addSearchPath(
	                RuntimeUtil.getLibVlcLibraryName(), "c:/Program Files/VideoLAN/VLC/");
	       // Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
	        JPanel contentPane = new JPanel();
	        contentPane.setBackground(Color.black);
	        contentPane.setLayout(new GridLayout(rows, cols, 16, 16));
	        contentPane.setBorder(new EmptyBorder(16, 16, 16, 16));

	        mainFrame = new Frame("Buntan");
	       // mainFrame.setIconImage(new ImageIcon(getClass().getResource("/icons/vlcj-logo.png")).getImage());
	        mainFrame.setLayout(new BorderLayout());
	        mainFrame.setBackground(Color.black);
	        mainFrame.add(contentPane, BorderLayout.CENTER);
	        mainFrame.setBounds(100, 100, 1600, 300);
	        mainFrame.addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowClosing(WindowEvent evt) {
	                for(PlayerInstance pi : players) {
	                    pi.mediaPlayer().release();
	                }
	                factory.release();
	                mainFrame.dispose();
	                
	            }
	        });

	        mainFrame.addKeyListener(new KeyAdapter() {

	            @Override
	            public void keyPressed(KeyEvent e) {
	                for(int i = 0; i < players.size(); i ++ ) {
	                    players.get(i).mediaPlayer().pause();
	                }
	            }
	        });

	        factory = new MediaPlayerFactory();

	        FullScreenStrategy fullScreenStrategy = new DefaultFullScreenStrategy(mainFrame);

	        for(int i = 0; i < medias.length; i ++ ) {
	            EmbeddedMediaPlayer player = factory.newEmbeddedMediaPlayer();
	            PlayerInstance playerInstance = new PlayerInstance(player);
	            players.add(playerInstance);

	            JPanel playerPanel = new JPanel();
	            playerPanel.setLayout(new BorderLayout());
	           // playerPanel.setBorder(new LineBorder(Color.white, 2));
	            playerPanel.add(playerInstance.videoSurface());

	            contentPane.add(playerPanel);
	        }

	        mainFrame.setVisible(true);
	    }

	    public void start() {
	        Executors.newSingleThreadExecutor().execute(new Runnable() {
	            @Override
	            public void run() {
	                for(int i = 0; i < medias.length; i ++ ) {
	                    players.get(i).mediaPlayer().setVideoSurface(factory.newVideoSurface(players.get(i).videoSurface()));
	                    players.get(i).mediaPlayer().prepareMedia(medias[i]);
	                }

	                // There is a race condition somewhere when invoking libvlc_media_player_play()
	                // multiple times in quick succession that causes a hard-failure and a fatal
	                // VM crash.
	                //
	                // This is _not_ about _concurrently_ calling play multiple times, but the
	                // native play function call must be off-loading something to a separate
	                // thread and returning - then a subsequent call to play somehow interferes
	                // with that or fails because of that.
	                //
	                // When libvlc_media_player_play() is called, the video playback is kicked
	                // off asynchronously - so the API call will return before the video has
	                // started playing. If we invoke play and then wait (making this effectively
	                // a synchronous call) for the player to start playing, there is less chance
	                // for the hard VM crash to occur - but it still might
	                for(int i = 0; i < medias.length; i ++ ) {
	                    EmbeddedMediaPlayer mediaPlayer = players.get(i).mediaPlayer();
	                    mediaPlayer.start();
	                }
	            }
	        });
	    }

	}
	

