package gestion;

import Model.Media;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Library <T> {
	private ArrayList library;
	
	public Library(){
		library = new <T>ArrayList<Media>();
	}
	
	public void addElement(String name, String path){
		library.add(new <T> Media(name, path));
	}
	
	public ArrayList<T> getLibrary(){
		return this.library;
	}
	
	public void initLibrary(int id){
		
	}
}
