/*
 * File: NameSurferEntry.java  UPDATED.
 * --------------------------
 * This class reads one line from NameSurferDataBase.java and
 * builds a NameSurferEntry.  The single entry is stored in an
 * int array for the ranks and a string for the name.  The 
 * getName() and getRank() methods return these values.
 * 
 * Name: Ronald Guglielmone
 * Class:  CS106A, M/W/F, Stanford, Fall 2014.
 */

public class NameSurferEntry implements NameSurferConstants {

	/* Constructor:  NameSurferEntry(String line)
	 * 
	 * When given a line from the database, NameSurferEntry
	 * will cut the string along " " delimiters and then
	 * parse the strings back to integers. 
	 */
	public NameSurferEntry(String input) {

		//Defines rankArray and entryName:
		makeObjects(input);

		//Defines variables for the while loop:
		int leftHand = input.indexOf(" ") + 1;
		int rightHand = input.indexOf(" ", leftHand);
		int count = 0;
		int limit = rankArray.length - 1;

		//Iterates through the data line and populates rankArray:
		while (count < limit) {
			int workingRank = Integer.parseInt(input.substring(leftHand, rightHand));
			rankArray[count] = workingRank;
			leftHand = rightHand + 1;
			rightHand = input.indexOf(" ", leftHand);
			count ++;
		}
	}

	/* Method: makeObjects(String line)
	 * 
	 * This is a quick helper-method for NameSurferEntry.
	 * It defines the rankArray and entryName.
	 */
	private void makeObjects(String entryLine){
		rankArray = new int[NDECADES];    	
		entryName = entryLine.substring(0, entryLine.indexOf(" "));
	}

	/* Method: getName()
	 * 
	 * Returns the entryName.
	 */
	public String getName() {
		return entryName;
	}

	/* Method: getRank(int decade)
	 * 
	 * Returns the rank entry for a specified decade
	 * within the rankArray. 
	 */
	public int getRank(int decade) {
		return rankArray[decade];
	}

	/* Method: toStrong()
	 * 
	 * Overwrites the toString method, providing
	 * a clear string representation of an entry.
	 */
	public String toString() {
		String toStringPrint = entryName + " [";
		for (int i = 0; i < NDECADES; i++) {
			toStringPrint += " " + rankArray[i];
		}
		return (toStringPrint + "]");
	}

	/*
	 * Variables:
	 */
	private String entryName;
	private int[] rankArray;
}
