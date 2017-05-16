/*
 * File: NameSurferDataBase.java  UPDATED.
 * -----------------------------
 * NameSurferDatabase.java reads a data file from text
 * and populates a HashMap with the entries.  The public
 * findEntry(String name) method allows a client to send
 * in a name (not case sensitive) and receive the related
 * entry line in the database if it exists.
 * 
 * Name: Ronald Guglielmone
 * Class:  CS106A, M/W/F, Stanford, Fall 2014.
 */

import java.io.IOException;
import acm.util.ErrorException;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;

public class NameSurferDataBase implements NameSurferConstants {

	/* Constructor: NameSurferDataBase(String filename)
	 * 
	 * Reads through a .txt database file and populates a
	 * HashMap with the data entries.
	 */
	public NameSurferDataBase(String filename) {
		try {
			fileReader = new BufferedReader(new FileReader(filename));
			hashDatabase = new HashMap <String, NameSurferEntry>();
			//Reads in the data from "filename":
			while (true) {
				String dataLine = fileReader.readLine();
				if (dataLine == null) {
					break;
				}
				NameSurferEntry workingEntry = new NameSurferEntry(dataLine);
				workingName = workingEntry.getName();
				workingName = workingName.toUpperCase();
				hashDatabase.put(workingName, workingEntry);
			}
			fileReader.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}

	/* Method: findEntry(String name)
	 * 
	 * Takes in a name string, and if it exists in the hashDatabase
	 * returns a NameSurferEntry.
	 */
	public NameSurferEntry findEntry(String name) {
		if (hashDatabase.containsKey(name.toUpperCase())) {
			NameSurferEntry entry = hashDatabase.get(name);
			return entry;
		}
		else {return null;}
	}

	/*
	 * Instance variables:
	 */
	private String workingName = "Bruno";
	private BufferedReader fileReader;
	private Map <String, NameSurferEntry> hashDatabase;
}
