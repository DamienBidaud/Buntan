package Model;

import gestion.ID3Tags;

public class Music extends Media{
	
	private String artiste;
	private String gender;
	private ID3Tags tag;
	private String albumName;
	
	public Music(String name, String fileRoad){
		super(name, fileRoad);
		tag = new ID3Tags(fileRoad);
		this.artiste = tag.getArtist();
		this.gender = tag.getGenre();
		this.albumName = tag.getAlbum();
	}

	public String getArtiste() {
		return artiste;
	}

	public void setArtiste(String artiste) {
		this.artiste = artiste;
		tag.setArtist(artiste);
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
		tag.setGenre(gender);
	}

	public ID3Tags getTag() {
		return tag;
	}

	public void setTag(ID3Tags tag) {
		this.tag = tag;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
		tag.setAlbum(albumName);
	}

	public void setName(String name){
		super.setName(name);
		tag.setSongName(name);
	}

}
