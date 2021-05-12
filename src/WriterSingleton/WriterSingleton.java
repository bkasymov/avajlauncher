package WriterSingleton;

import java.io.*;

public final class WriterSingleton{
	private static WriterSingleton writerSingleton = new WriterSingleton();
	private static File file = null;
	private FileWriter fileWriter = null;
	
	private WriterSingleton(){
		file = new File("simulation.txt");
		try {
			fileWriter = new FileWriter(file);
		} catch (IOException ioException) {
			System.err.println("Impossible to write text to the file\n");
		}
	}
	
	public static WriterSingleton getWriter(){
		return writerSingleton;
	}
	
	public void writeText(String text){
		try {
			this.fileWriter.write(text + "\n");
		} catch (IOException ioException) {
			System.err.println("Impossible to write text.");
		}
	}
	
	public void closeFile(){
		try {
			this.fileWriter.close();
		} catch (IOException ioException) {
			System.err.println("You are closing one file twice");
		}
	}
	
	
}
