package Vue;

import gestion.ID3Tags;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.swing.JPanel;

import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public class LecteurAudio extends JPanel{
	
	  private String filename;
	  private AdvancedPlayer player; 
	  private int pausedOnFrame = 0;
	  private boolean pauses;
	  private int lastFrame;
	  
	    // constructor that takes the name of an MP3 file
	    public LecteurAudio(String filename) {
	        this.filename = filename;
	        pauses = false;
	        ID3Tags tag = new ID3Tags(filename);
	        this.lastFrame = Integer.parseInt(tag.getTime());
	    }
	    
	    public LecteurAudio(String filename, int pausedOnFrame, boolean pauses){
	    	this.filename = filename;
	    	this.pausedOnFrame = pausedOnFrame;
	    	this.pauses = pauses;
	    }

	    public void close() { 
	    	if (player != null){ 
	    		player.setPlayBackListener(new PlaybackListener() {
		            @Override
		            public void playbackFinished(PlaybackEvent event) {
		                pausedOnFrame = event.getFrame();
		            }
		        });
	    		player.stop(); 
	    	}
	    	pauses = false;
	    }

	    public int suspend(){
	    	if(player != null){			
				pauses = true;
				player.setPlayBackListener(new PlaybackListener() {
		            @Override
		            public void playbackFinished(PlaybackEvent event) {
		                pausedOnFrame = event.getFrame();
		            }
		        });
				player.stop();
	    	}
			return pausedOnFrame;
	    }
	    
	    // play the MP3 file to the sound card
	   
	    
	    public void go() {
		        try {
		            FileInputStream fis     = new FileInputStream(filename);
		            BufferedInputStream bis = new BufferedInputStream(fis);
		            player = new AdvancedPlayer(bis);
		         
		        }
		        catch (Exception e) {
		            System.out.println("Problem playing file " + filename);
		            System.out.println(e);
		        }
	
		        // run in new thread to play in background
		     
		    	
		    	if(pauses == false){
		        new Thread() {
		            public void run() {
		                try { player.play(); }
		                catch (Exception e) { System.out.println(e); }
		            }
		        }.start();
	    	}
	    	else{
	    			System.out.println(pausedOnFrame);
	    			pauses = false;
	    			new Thread(){
	    				public void run(){
	    					try{player.play(pausedOnFrame, lastFrame);}
	    					catch(Exception e){System.out.println(e);}
	    				}
	    			}.start();
	    	}
       }
}