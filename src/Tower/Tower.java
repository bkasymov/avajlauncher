package Tower;

import Aircrafts.Flyable;
import WriterSingleton.WriterSingleton;

import java.util.ArrayList;
import java.util.List;

public class Tower {
	private List<Flyable> observers = new ArrayList<>();
	private List<Flyable> unregister = new ArrayList<>();
	WriterSingleton writer = WriterSingleton.getWriter();

	public void register(Flyable flyable){
		

		if (observers.contains(flyable))
		{
			return;
		}
		else {
			observers.add(flyable);
			writer.writeText("Tower says: " +
					flyable.getNameOfClass() +
					'#' + flyable.getName() +
					"(" + flyable.getId() +
					")" + " registered to weather tower.");
		}
	}
	
	public void unregister(Flyable flyable){
		writer.writeText(flyable.getNameOfClass() +
				"#" +
				flyable.getName() +
				'(' +
				flyable.getId() +
				')' +
				" landing.");
		writer.writeText("Tower says: " +
				flyable.getNameOfClass() +
				"#" +
				"(" +
				flyable.getId() +
				")" +
				" unregistered from weather tower.");
		unregister.add(flyable);
	}
	
	protected void conditionsChanged(){
		int     i = 0;

		for (Flyable flyable : observers)
		{
			flyable.updateConditions();
		}
		observers.removeAll(unregister);
	}
}
