package Measures;

public class Coordinates {
	private int longitude;
	private int latitude;
	private int height;
	
	public Coordinates(int longtitude, int latitude, int height){
		this.longitude = longtitude;
		this.latitude = latitude;
		this.height = height;
	}
	
	public int getLongtitude(){
		return this.longitude;
	}
	
	public int getLatitude() {
		return this.latitude;
	}
	
	public int getHeight(){
		return this.height;
	}
	
}
