package Tower;

import Measures.Coordinates;
import WeatherProvider.WeatherProvider;

public class WeatherTower extends Tower{

	WeatherProvider weatherProvider = WeatherProvider.getProvider();

	public String getWeather(Coordinates coordinates){
		String currentWeather = weatherProvider.getCurrentWeather(coordinates);
		return currentWeather;
	}
	
	public void changeWeather(){
		this.conditionsChanged();
	}
}
