package Aircrafts;

import Measures.Coordinates;
import Tower.WeatherTower;

public class Baloon extends Aircraft implements Flyable {
	private WeatherTower weatherTower;
	public static String nameOfClass = "Baloon";

	private String objectName = nameOfClass +
			"#" +
			this.name +
			"(" +
			this.id +
			")" +
			":" +
			" ";

	Baloon(String name, Coordinates coordinates) {
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
		return "Baloon";
	}

	public String getName() {
		return this.name;
	}

	public long getId() {
		return this.id;
	}


	private void rain() {
		this.coordinates = new Coordinates(
				this.coordinates.getLongtitude(),
				this.coordinates.getLatitude(),
				this.coordinates.getHeight() - 5);
		this.writer.writeText(objectName +
				"Oh, it's rain! Just sit and relax.");
	}

	private void snow() {
		this.coordinates = new Coordinates(
				this.coordinates.getLongtitude(),
				this.coordinates.getLatitude(),
				this.coordinates.getHeight() - 15);
		this.writer.writeText(objectName + "Let it snow! Let it snow! Let's go home...");
	}

	private void sun() {
		this.coordinates = new Coordinates(
				this.coordinates.getLongtitude() + 2,
				this.coordinates.getLatitude(),
				this.coordinates.getHeight() + 4);
		this.writer.writeText(objectName + "Sunny, yesterday my life was filled with rain. Yes, it's SUN!");
	}

	private void fog(){
		this.coordinates = new Coordinates(
				this.coordinates.getLongtitude(),
				this.coordinates.getLatitude(),
				this.coordinates.getHeight() - 3);
		this.writer.writeText(objectName + "Fog. I tried to catch some fog but i mist");
	}
}