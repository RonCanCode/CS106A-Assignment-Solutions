/*
 * File: FacePamphletDatabase.java  ***UPDATED***
 * -------------------------------
 * Handles the data operations.
 * I'm feeling rather sleepy at this point...
 */

import java.util.*;

public class FacePamphletDatabase implements FacePamphletConstants {
	
	/* Constructor:
	 * 
	 * This defines the main hash map database.
	 */
	public FacePamphletDatabase() {
		profileDatabase = new HashMap <String, FacePamphletProfile>();
	}
	
	/* Method: addProfile(FacePamphletProfile inputProfile)
	 * 
	 * Just adds one entry to the main database.
	 */
	public void addProfile(FacePamphletProfile inputProfile) {
			profileDatabase.remove(inputProfile.getName());
			profileDatabase.put(inputProfile.getName(), inputProfile);
	}

	
	/* Method getProfile(String inputName)
	 * 
	 * Simply returns a database entry if its key exists.
	 */
	public FacePamphletProfile getProfile(String inputName) {
		if(profileDatabase.containsKey(inputName)) {
			return profileDatabase.get(inputName);
		}
		else{
			return null;
		}
		
	}
	
	
	/* Method: deleteProfile(String inputName)
	 * 
	 * Iterates through the friend list, removing input
	 * user from those databases.  Then, removes the entire
	 * entry for the input user.
	 */
	public void deleteProfile(String inputName) {
		if(profileDatabase.containsKey(inputName)) {
			Iterator <String> iterator = profileDatabase.get(inputName).getFriends();
			while(iterator.hasNext()) {
				String connection = iterator.next();
				FacePamphletProfile connectionProfile = profileDatabase.get(connection);
				connectionProfile.removeFriend(inputName);
			}
			profileDatabase.remove(inputName);
		}
	}

	
	/* Method: containsProfile(String name)
	 * 
	 * This just returns a boolean based on whether the
	 * key is in our database.
	 */
	public boolean containsProfile(String inputName) {
		if(profileDatabase.containsKey(inputName)) {
			return true;
		}
		else {
			return false;
		}
	}

	/*
	 * Variables:
	 */
	private Map <String, FacePamphletProfile> profileDatabase;
	
}
