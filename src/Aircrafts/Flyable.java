package Aircrafts;

import Tower.WeatherTower;

public interface Flyable {
	public void updateConditions();
	public void registerTower(WeatherTower weatherTower);
	public String getNameOfClass();
	public String getName();
	public long getId();
	
}
