package WeatherProvider;

import Measures.Coordinates;

public class WeatherProvider {
	private static WeatherProvider weatherProvider = null;
	private String[] weather = {"FOG", "SUN", "RAIN", "SNOW"};

	private WeatherProvider(){

	}
	
	public static WeatherProvider getProvider(){
		if (weatherProvider == null){
			weatherProvider = new WeatherProvider();
		}
		else {
			return weatherProvider;
		}
		return weatherProvider;
	}

	private int randomWeather(Coordinates coordinates)
	{
		if (coordinates.getLongtitude() < 0
				|| coordinates.getHeight() < 0 ||
				coordinates.getLatitude() < 0)
			return 0;
		int response = (coordinates.getHeight() +
				coordinates.getLongtitude() +
				coordinates.getLatitude()) % 4;
		if (response > 4 || response < 0)
			return response;
		return response;

	}

	public String  getCurrentWeather(Coordinates coordinates){
		return weather[randomWeather(coordinates)];
	}
}
