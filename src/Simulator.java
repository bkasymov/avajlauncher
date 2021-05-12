import Aircrafts.AircraftFactory;
import Aircrafts.Flyable;
import Tower.Tower;
import Tower.WeatherTower;
import WriterSingleton.WriterSingleton;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * BufferReader assigned for record all values
 * to buffer than don't open the file again and again.
 */

public class Simulator {
	
	
	public static void main(String[] args) throws IncorrectTextOfPlanes {
		int countOfSimulations = 0;
		ArrayList<Flyable> flyableList = null;
		String readLine;
		Tower tower = new Tower();

		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
			countOfSimulations = checkAndRecordCountOfSimulation(bufferedReader);
			flyableList = parsePlanes(bufferedReader);
			WriterSingleton writerSingleton = WriterSingleton.getWriter();
			playGame(countOfSimulations, flyableList);
			writerSingleton.closeFile();
		} catch (IOException e) {
			System.out.println("Impossible to read the file " + args[0]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("The source file is not exist");
		} catch (ErrorCountOfSimulation errorCountOfSimulation) {
			errorCountOfSimulation.printStackTrace();
		}
	}
	
	private static void playGame(int countOfSimulations, ArrayList<Flyable> flyableList) {
		WeatherTower weatherTower = new WeatherTower();
		
		for (Flyable flyable : flyableList) {
			flyable.registerTower(weatherTower);
		}

		while(countOfSimulations > 0)
		{
			weatherTower.changeWeather();
			countOfSimulations--;
		}

	}
	
	private static ArrayList<Flyable> parsePlanes(BufferedReader bufferedReader) throws IncorrectTextOfPlanes, IOException {
		
		String readLine = null;
		String[] splitLine = null;
		ArrayList<Flyable> flyableObjects = new ArrayList<Flyable>();
		String typeOfAircraft = null;
		Flyable example = null;
		AircraftFactory aircraftFactory = new AircraftFactory();
		
		while ((readLine = bufferedReader.readLine()) != null) {
			splitLine = readLine.split(" ");
			if (splitLine.length == 1)
				throw new IncorrectTextOfPlanes("\nThe file have empty line. Please remove this empty line.\n");
			if (splitLine.length < 5)
				throw new IncorrectTextOfPlanes("\nThe line have less argument than should be.\n" + readLine);
			if (splitLine.length > 5)
				throw new IncorrectTextOfPlanes("\nThis line have extra argument:\n" + readLine +
						"\nOr have empty line after the line.");
			String exampleType = parseTypeOfAircraft(splitLine[0]);
			String exampleName = splitLine[1];
			Integer[] exampleCoordinates = parseCoordinatesOfAircraft(splitLine);
			example = aircraftFactory.newAircraft(
								exampleType,
								exampleName,
								exampleCoordinates[0],
								exampleCoordinates[1],
								exampleCoordinates[2]
						);
			flyableObjects.add(example);
		}
		return flyableObjects;
	}
	
	public static Integer[] parseCoordinatesOfAircraft(String[] splitLine) throws IncorrectTextOfPlanes {
		try {
			
			Integer[] coordinates = new Integer[3];
			coordinates[0] = Integer.parseInt(splitLine[2]);
			coordinates[1] = Integer.parseInt(splitLine[3]);
			coordinates[2] = Integer.parseInt(splitLine[4]);
			if (coordinates[2] < 0 || coordinates[1] < 0 || coordinates[0] < 0)
				throw new IncorrectTextOfPlanes("\nCoordinates can't be with minus value.");
			return coordinates;
		}catch (NumberFormatException numberFormatException) {
			throw new IncorrectTextOfPlanes("\nCoordinates of objects is not correct. Please, check files data.");
		}
}
	
	private static String parseTypeOfAircraft(String type) throws IncorrectTextOfPlanes {
		if (type.equals("Baloon")) return type;
		else if (type.equals("Helicopter")) return type;
		else if (type.equals("JetPlane")) return type;
		else throw new IncorrectTextOfPlanes("Type of flyable is not actual. Please, check files data.");
	}
	
	private static int checkAndRecordCountOfSimulation(BufferedReader bufferedReader) throws ErrorCountOfSimulation {
		try {
			String readLine = bufferedReader.readLine();
			int countOfSimulations = Integer.parseInt(readLine.split(" ")[0]);
			if (countOfSimulations < 0 || countOfSimulations == 0) throw new ErrorCountOfSimulation();
			else {
				return countOfSimulations;
			}
		} catch (NumberFormatException numberFormatException) {
			throw new ErrorCountOfSimulation();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Exceptions have serialVersion, because they are implementing Exception,
	 * which have
	 */

	public static class ErrorCountOfSimulation extends Exception {
		public ErrorCountOfSimulation() {
			super("The number of simulations in file is not correct. Please, check input file.");
		}
	}
	
	public static class IncorrectTextOfPlanes extends Exception {
		public IncorrectTextOfPlanes(String message) {
			super(message);
		}
	}
}