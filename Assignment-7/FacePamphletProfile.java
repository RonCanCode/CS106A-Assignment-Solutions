/*
 * File: FacePamphletProfile.java
 * ------------------------------
 * Constructor class for a FacePamphletProfile.  ***UPDATED***
 * Used by FacePamphlet.java.
 */

import acm.graphics.*;
import java.util.*;

public class FacePamphletProfile implements FacePamphletConstants {
	
	/* Constructor: FacePamphletProfile
	 * 
	 * This just defines a new name...
	 */
	public FacePamphletProfile(String inputName) {
		profileName = inputName;
		friendsList = new ArrayList <String>();
	}

	/** This method returns the name associated with the profile. */ 
	public String getName() {
		return profileName;
	}

	/** This method returns the image associated with the profile.  */ 
	public GImage getImage() {
		if(profileImage == null) {
			return null;
		}
		else{
			return profileImage;
		}
	}

	/** This method sets the image associated with the profile. */ 
	public void setImage(GImage inputImage) {
		profileImage = inputImage;
	}
	
	/** 
	 * This method returns the status associated with the profile.
	 * If there is no status associated with the profile, the method
	 * returns the empty string ("").
	 */ 
	public String getStatus() {
		return profileStatus;
	}
	
	/** This method sets the status associated with the profile. */ 
	public void setStatus(String status) {
		profileStatus = status;
	}

	/* Method: addFriend(String friendInput)
	 * 
	 * Takes in a string, adds the entry to an array.
	 * 
	 */
	public boolean addFriend(String inputFriend) {
		if(friendsList.contains(inputFriend)) {
			return false;
		}
		else{
			friendsList.add(inputFriend);
			return true;
		}
	}

	/* Method: removeFriend(String inputFriend)
	 * 
	 * Deletes an entry from the friends array.
	 */
	public boolean removeFriend(String inputFriend) {
		if(friendsList.contains(inputFriend)) {
			friendsList.remove(friendsList.indexOf(inputFriend));
			return true;
		}
		else{
			return false;
		}
	}

	/* Friends list array iterator... */ 
	public Iterator<String> getFriends() {
		return friendsList.iterator();
	}
	
	/*
	 * Variables:
	 */
	//Explains itself:
	private String profileName = "";
	//Explains itself:
	private GImage profileImage = null;
	//Stupid default status:
	private String profileStatus = "";
	//Friends Array:
	private ArrayList <String> friendsList;
	
}
