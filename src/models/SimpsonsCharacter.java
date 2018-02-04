package models;

import java.util.Random;

public class SimpsonsCharacter {

	int id;
	String name;
	int numberOfImages;
	
	static int count = 0;
	
	public SimpsonsCharacter() { }
	
	public SimpsonsCharacter( String name, int numberOfImages) {
		this.id = ++count;
		this.name = name;
		this.numberOfImages = numberOfImages;
	}
	
	public SimpsonsCharacter( int id, String name, int numberOfImages) {
		this.id = id;
		this.name = name;
		this.numberOfImages = numberOfImages;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getNumberofimages() {
		return numberOfImages;
	}

	public String getNamepath() {
		return this.name.toLowerCase().trim().replaceAll(" ", "_");
	}

	public String getFirstimageurl() {
		if ( this.numberOfImages > 0 ) return "http://albertcervantes.com/cs3220/cdn/simpsons/" + this.getNamepath() + "/pic_0000.jpg";
		else return "http://via.placeholder.com/150?text=N/A";
	}
	
	public String getRandomimageurl() {
		if ( this.numberOfImages > 0 )
			return "http://albertcervantes.com/cs3220/cdn/simpsons/" + this.getNamepath() + "/pic_" + String.format("%04d", (new Random()).nextInt(this.numberOfImages) ) + ".jpg";
		return "http://via.placeholder.com/150?text=N/A";
	}
	
}