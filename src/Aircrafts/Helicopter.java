package Aircrafts;

import Measures.Coordinates;
import Tower.WeatherTower;

public class Helicopter extends Aircraft implements Flyable{
	private WeatherTower weatherTower;
	public static String nameOfClass = "Helicopter";
	private String objectName = nameOfClass +
			"#" +
			this.name +
			"(" +
			this.id +
			")" +
			":" +
			" ";

	Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	public void updateConditions() {
		String newWeather = weatherTower.getWeather(this.coordinates);

		if (newWeather.equals("RAIN")) {
			rain();
		} else if (newWeather.equals("SNOW")) {
			snow();
		} else if (newWeather.equals("SUN")) {
			sun();
		}
		else if (newWeather.equals("FOG")) {
			fog();
		}
		checkHeightOfAircraft();
	}

	private void checkHeightOfAircraft() {
		int height = this.coordinates.getHeight();

		if (height > 100)
			this.coordinates = new Coordinates(
					this.coordinates.getLongtitude(),
					this.coordinates.getLatitude(),
					100);
		if (height < 0) {
			weatherTower.unregister(this);
		}
	}

	public void registerTower(WeatherTower weatherTower) {
		weatherTower.register(this);
		this.weatherTower = weatherTower;
	}

	public String getNameOfClass() {
		return "Helicopter";
	}

	public String getName() {
		return this.name;
	}

	public long getId() {
		return this.id;
	}


	private void rain() {
		this.coordinates = new Coordinates(
				this.coordinates.getLongtitude() + 5,
				this.coordinates.getLatitude(),
				this.coordinates.getHeight());
		this.writer.writeText(objectName +
				"Rain. Coming clup-clup, clup-clup");
	}

	private void snow() {
		this.coordinates = new Coordinates(
				this.coordinates.getLongtitude(),
				this.coordinates.getLatitude(),
				this.coordinates.getHeight() - 12);
		this.writer.writeText(objectName + "Snow time. Please be careful.");
	}

	private void sun() {
		this.coordinates = new Coordinates(
				this.coordinates.getLongtitude() + 10,
				this.coordinates.getLatitude(),
				this.coordinates.getHeight() + 2);
		this.writer.writeText(objectName + "Sun, time to explore territories!");
	}

	private void fog(){
		this.coordinates = new Coordinates(
				this.coordinates.getLongtitude() -1,
				this.coordinates.getLatitude(),
				this.coordinates.getHeight());
		this.writer.writeText(objectName + "Fog. Uh, I can't to see my goals!");
	}

}
