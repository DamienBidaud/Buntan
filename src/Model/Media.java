package Model;

public class Media {
	private String name;
	private String fileRoad;
	
	public Media(String name, String fileRoad){
		this.name = name;
		this.fileRoad = fileRoad;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileRoad() {
		return fileRoad;
	}

	public void setFileRoad(String fileRoad) {
		this.fileRoad = fileRoad;
	}
	
	public String toString(){
		return "Name:"+this.name+", file:"+this.fileRoad;
	}
	
}
