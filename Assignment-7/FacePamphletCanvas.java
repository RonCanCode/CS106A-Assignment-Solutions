/*
 * File: FacePamphletCanvas.java  *** UPDATED ***
 * -----------------------------
 * This class controls the graphics canvas.
 * It mostly communicates with FacePamphlet.java
 * 
 * Name: Ron Guglielmone
 * Class: CS106A, Stanford, Fall 2014.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas implements FacePamphletConstants {
		
	/* Constructor:
	 * 
	 * This is empty...  Because...
	 * I don't know what to put here.
	 */
	public FacePamphletCanvas() {
		//What am I supposed to put here?
	}
	
	/* Method: showMessage(String inputText)
	 * 
	 * This method is called from FacePamphlet.java.
	 * When sent a string, it will display the text
	 * as a message along the bottom of the canvas.
	 * 
	 */
	public void showMessage(String inputText) {
		//Creates a GLabel:
		GLabel newSystemMessage = new GLabel(inputText);
		newSystemMessage.setFont(MESSAGE_FONT);
		//Defines X,Y location for the label:
		double X = getWidth()/2 - newSystemMessage.getWidth() / 2;
		double Y = getHeight() - BOTTOM_MESSAGE_MARGIN;
		//Removes an old message:
		if(systemMessage != null) {
			remove(systemMessage);
		}
		//Adds the new message:
		add(newSystemMessage, X, Y);
		systemMessage = newSystemMessage;
	}
	
	/* Method: displayProfile(FacePamphletProfile inputProfile)
	 * 
	 * When called from FacePamphlet.java this method will build
	 * and display a profile on the canvas.  First it will clear
	 * the canvas, then it will add all of the profile elements.
	 */
	public void displayProfile(FacePamphletProfile inputProfile) {
		workingProfile = inputProfile;
		removeAll();
		addProfileNameLabel();
		addProfileImage();
		addProfileStatus();
		addProfileFriends();
	}
	
	/* Method: addNameLabel(String inputName)
	 * 
	 * This helper method adds a name label with 
	 * BLUE, PROFILE_NAME_FONT in the upper left-hand
	 * corner of the canvas.
	 */
	private void addProfileNameLabel() {
		String inputName = workingProfile.getName();
		nameLabel = new GLabel(inputName);
		nameLabel.setColor(Color.BLUE);
		nameLabel.setFont(PROFILE_NAME_FONT);
		nameLabelHeight = nameLabel.getHeight();
		add(nameLabel, LEFT_MARGIN, (TOP_MARGIN + nameLabelHeight));
	}
	
	/* Method: addProfileImage()
	 * 
	 * This method defines the bounds of an image for
	 * the workingProfile.  If there is no image in
	 * the FacePamphletProfile.java file, it will display
	 * a text of "No photo."
	 */
	private void addProfileImage() {
		//Initializes variables:
		GImage workingImage = workingProfile.getImage();
		double x = LEFT_MARGIN;
		double y = TOP_MARGIN + nameLabelHeight + IMAGE_MARGIN; 
		
		if(workingImage != null) {
			workingImage.setBounds(x, y, IMAGE_WIDTH, IMAGE_HEIGHT);
			add(workingImage, x, y);
		}
		//If there is no photo:
		else {
			addEmptyImage(x,y);
		}
	}
	
	/* Method: addEmptyImage(double x, double y)
	 * 
	 * Creates the default empty image.
	 */
	private void addEmptyImage(double x, double y) {
		GRect noImageImage = new GRect(x, y, IMAGE_WIDTH, IMAGE_HEIGHT);
		add(noImageImage);
		GLabel empty = new GLabel ("No Photo.");
		empty.setFont(PROFILE_IMAGE_FONT);
		add(empty, (x + (IMAGE_WIDTH / 2) - (empty.getWidth()/2)), (y + ((IMAGE_HEIGHT/2) + (empty.getHeight()/2))));
	}
	
	/* Method: addProfileStatus()
	 * 
	 * This method removes the old status if one existed
	 * and adds a new status label to the canvas.
	 */
	private void addProfileStatus() {
		workingStatus = workingProfile.getStatus();
		GLabel statusLabel = new GLabel(workingStatus);
		statusLabel.setFont(PROFILE_STATUS_FONT);
		double x = LEFT_MARGIN;
		double y = TOP_MARGIN + nameLabelHeight + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN + statusLabel.getHeight();
		if(previousStatusLabel != null) {
			remove(previousStatusLabel);
		}
		add(statusLabel, x, y);
		previousStatusLabel = statusLabel;
	}

	/* Method: addProfileFriends()
	 * 
	 * Runs through an iterator of the friends ArrayList
	 * and populates a GLabel with them.
	 */
	private void addProfileFriends() {
		addFriendsLabel();
		Iterator<String> iterator = workingProfile.getFriends();
		int count = 1;
		while(iterator.hasNext()) {
			GLabel names = new GLabel(iterator.next());
			names.setFont(PROFILE_FRIEND_FONT);
			double drop = TOP_MARGIN + nameLabelHeight + friendsHeading.getHeight() * count;
			add(names, getWidth()/2, drop);
			count++;
		}
	}
	
	/* Method: addFriendsLabel()
	 * 
	 * Just makes a stupid GLabel, nothing more...
	 */
	private void addFriendsLabel() {
		friendsHeading = new GLabel("Friends");
		friendsHeading.setFont(PROFILE_FRIEND_LABEL_FONT);
		double x = getWidth()/2;
		double y = TOP_MARGIN + nameLabelHeight;
		add(friendsHeading, x, y);
	}
	
	/*
	 * Variables:
	 */
	private FacePamphletProfile workingProfile;
	//Lazy way to pass this between methods:
	private GLabel friendsHeading;
	//These two keep track of the status:
	private String workingStatus;
	private GLabel previousStatusLabel;
	//Name label stuff...
	private GLabel nameLabel;
	private double nameLabelHeight;
	//Keeps track of the console printout:
	private GLabel systemMessage;
	
}
