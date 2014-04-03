package gestion;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.TagOptionSingleton;
import org.farng.mp3.id3.AbstractID3v2Frame;
import org.farng.mp3.id3.ID3v1;
import org.farng.mp3.id3.ID3v2_2;
import org.farng.mp3.object.ObjectLyrics3TimeStamp;


public class ID3Tags {

	
	private ID3v2_2  id3TagsV2;
	private ID3v1  id3TagsV1;
	private AbstractID3v2Frame frame;
	private String musicFile;
	
	private MP3File mp3File;
	
	
	public ID3Tags(String musicFile){
		TagOptionSingleton.getInstance().setOriginalSavedAfterAdjustingID3v2Padding(false);
		try {
			this.musicFile = musicFile;
			mp3File = new MP3File(musicFile);
			new ObjectLyrics3TimeStamp(musicFile);
			if(mp3File.hasID3v2Tag()){
				this.id3TagsV2 = (ID3v2_2) mp3File.getID3v2Tag();
				}
				else if(mp3File.hasID3v1Tag()){
				this.id3TagsV1 = mp3File.getID3v1Tag();

				}
			else{
				System.out.println("Erreur");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TagException e) {
			e.printStackTrace();
		}
	}
	
	public String getArtist(){
		return this.id3TagsV2.getLeadArtist();
	}
	
	public void setArtist(String artistName){
		this.id3TagsV2.setLeadArtist(artistName);
		try {
			this.mp3File.save();
		} catch (IOException | TagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	}
	
	public String getSongName(){
		return this.id3TagsV2.getSongTitle();
	}
	
	public void setSongName(String songName){
//		this.id3TagsV2.setSongTitle(songName);
//		this.mp3File.setID3v2Tag(id3TagsV2);		
		this.mp3File.getID3v2Tag().setSongTitle(songName);
		try {
			this.mp3File.save();
		} catch (IOException | TagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getAlbum(){
		return this.id3TagsV2.getAlbumTitle();
	}
	
	public void setAlbum(String album){
		this.id3TagsV2.setAlbumTitle(album);
		try {
			this.mp3File.save();
		} catch (IOException | TagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	}
	
	public String getTrackNumber(){
		return this.id3TagsV2.getTrackNumberOnAlbum();
	}
	
	public void setTrackNumber(String trackNumber){
		this.id3TagsV2.setTrackNumberOnAlbum(trackNumber);
		try {
			this.mp3File.save();
		} catch (IOException | TagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	}
	
	public String getGenre(){
		return this.id3TagsV2.getSongGenre();
		
	}
	
	public void setGenre(String genre){
		this.id3TagsV2.setSongGenre(genre);
		try {
			this.mp3File.save();
		} catch (IOException | TagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	}
	
	public String getTime(){
		return this.id3TagsV2.getSize()+"";
	}
	
	
}
