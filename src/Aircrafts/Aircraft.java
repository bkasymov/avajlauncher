package Aircrafts;

import Measures.Coordinates;
import WriterSingleton.WriterSingleton;

public class Aircraft {
	protected long id = 0;
	protected String name;
	protected Coordinates coordinates;
	private static long idCounter = 1;
	protected WriterSingleton writer = WriterSingleton.getWriter();

	protected Aircraft(String name, Coordinates coordinates){
		this.name = name;
		this.coordinates = coordinates;
		this.id = nextId();
	}
	
	private long nextId(){
		return idCounter++;
	}
}
