package Aircrafts;

import Measures.Coordinates;
import Tower.WeatherTower;

public class JetPlane extends Aircraft implements Flyable{
	private WeatherTower weatherTower;
	public static String nameOfClass = "JetPlane";
	private String objectName = nameOfClass +
			"#" +
			this.name +
			"(" +
			this.id +
			")" +
			":" +
			" ";

	JetPlane(String name, Coordinates coordinates) {
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
		return "JetPlane";
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
				this.coordinates.getLatitude() + 5,
				this.coordinates.getHeight());
		this.writer.writeText(objectName +
				"Please check your belt and keep and don't leave your seats. We are under rain.");
	}

	private void snow() {
		this.coordinates = new Coordinates(
				this.coordinates.getLongtitude(),
				this.coordinates.getLatitude(),
				this.coordinates.getHeight() - 7);
		this.writer.writeText(objectName + "Hey, look at this, New Year is closer! It's snow!");
	}

	private void sun() {
		this.coordinates = new Coordinates(
				this.coordinates.getLongtitude(),
				this.coordinates.getLatitude() + 10,
				this.coordinates.getHeight() + 2);
		this.writer.writeText(objectName + "Let me please you with awesome weather under sun!");
	}

	private void fog(){
		this.coordinates = new Coordinates(
				this.coordinates.getLongtitude(),
				this.coordinates.getLatitude() + 1,
				this.coordinates.getHeight());
		this.writer.writeText(objectName + "Fog. Uh, I can't to see my goals!");
	}

}
