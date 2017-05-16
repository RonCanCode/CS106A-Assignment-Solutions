/* 
 * File: FacePamphlet.java  *** UPDATED ***
 * -----------------------
 * The main FacePamphlet.java class creates the interactors,
 * then it reads in user input, and calls the other classes 
 * to update the database, profile, and canvas.
 * 
 * Name: Ron Guglielmone
 * Class:  CS106A, Stanford, Fall 2014.
 */

import acm.program.*;
import acm.util.*;
import acm.graphics.*;
import javax.swing.*;
import java.awt.event.*;


public class FacePamphlet extends Program implements FacePamphletConstants {

	/* Method: init()
	 * 
	 * This method defines all the variables and then
	 * adds interactors to the north and west fields.
	 * Then, action listeners are initialized and 
	 * finally the main canvas is added.
	 */
	public void init() {
		initializeInteractors();
		addNorthInteractors();
		addWestInteractors();
		actionListeners();
		add(mainCanvas);
//		mainCanvas.showMessage("Welcome to FacePamphlet.");
    }
	
	/* Method: initializeInteractors()
	 * 
	 * This method defines the interactors.
	 */
	private void initializeInteractors() {
		nameField = new JTextField(TEXT_FIELD_SIZE);
		statusField = new JTextField(TEXT_FIELD_SIZE);
		pictureField = new JTextField(TEXT_FIELD_SIZE);
		friendField = new JTextField(TEXT_FIELD_SIZE);
	}
	
	/* Method: addNorthInteractors()
	 * 
	 * This method adds the North-zone interactors.
	 */
	private void addNorthInteractors() {
		add(new JLabel("                                  "), NORTH);
		add(new JLabel("Name"), NORTH); 
		add(nameField, NORTH);
		add(new JButton("Add"), NORTH);
		add(new JButton("Delete"), NORTH);
		add(new JButton("Lookup"), NORTH);
	}
	
	/* Method: addWestInteractors()
	 * 
	 * This method adds the West-zone interactors.
	 */
	private void addWestInteractors() {
		add(statusField, WEST);
		add(new JButton("Change Status"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(pictureField, WEST);
		add(new JButton("Change Picture"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(friendField, WEST);
		add(new JButton("Add Friend"), WEST);
	}
	
	/* Method: actionListeners()
	 * 
	 * This method adds the action listeners.
	 */
	private void actionListeners(){
		addActionListeners();
		statusField.addActionListener(this);
		pictureField.addActionListener(this);
		friendField.addActionListener(this);
	}
	
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */

    public void actionPerformed(ActionEvent e) {
    	
    	//Action inputs:
    	String workingCommand = e.getActionCommand();
    	Object otherCommand = e.getSource();
    	String workingName = nameField.getText();
    	
    	//"ADD" BUTTON CLICKED:
    	if(workingCommand.equals("Add")) {
    		if(!workingDatabase.containsProfile(workingName)) {
    			createNewProfile(workingName);
    		}
    		else{
    			profileAlreadyExists(workingName);
    		}
    	}
    	
    	//"DELETE" BUTTON CLICKED:
    	if (workingCommand.equals("Delete")) {
    		deleteThatUser(workingName);
    	}
    	
    	//"LOOKUP" BUTTON CLICKED:
    	if (workingCommand.equals("Lookup")) {
    		clearTheCanvas();
    		if(workingDatabase.containsProfile(workingName)) {
    			showTheProfile(workingName);
    		}
    		else{
    			profileDoesntExist();
    		}
    	}
    	
    	//"STATUS" BUTTON CLICKED:
    	if (workingCommand.equals("Change Status") || otherCommand == statusField) {
    		changeTheStatus();
    	}
    	
    	//CHANGE PICTURE CLICKED:
    	if (workingCommand.equals("Change Picture") || otherCommand == pictureField) {
    		changeThatPhoto();
    	}
    	
    	//"ADD FRIEND" CLICKED:
    	if (workingCommand.equals("Add Friend") || otherCommand == friendField) {
    		String whoYouAdding = friendField.getText();
    		if(workingProfile != null) {
    			addTheFriend(whoYouAdding);
    		}	
    		else{
    			mainCanvas.showMessage("Must be in a profile to do that.");
    		}	
    	}		
    }
    
    /* Method: clearTheCanvas()
     * 
     * Does exactly what it sounds like it should...
     * Completely unnecessary method...
     */
    private void clearTheCanvas() {
		mainCanvas.removeAll();
    }
    
	/* Method: createNewProfile(String workingName)
	 * 
	 * Takes in a user-chosen name and makes a new profile,
	 * then adds the profile to workingDatabase and displays
	 * it onto the canvas.  Finally a display message is shown.
	 */
	private void createNewProfile(String workingName) {
		FacePamphletProfile enteredProfile = new FacePamphletProfile(workingName);
		workingDatabase.addProfile(enteredProfile);
		mainCanvas.displayProfile(enteredProfile);
		mainCanvas.showMessage("Profile has been created.");
		workingProfile = enteredProfile;
	}
	
	/* Method: profileAlreadyExists(String workingName)
	 * 
	 * If a user tries to create a profile that already exists
	 * this shall be their fate...
	 */
	private void profileAlreadyExists(String workingName) {
		FacePamphletProfile enteredProfile = workingDatabase.getProfile(workingName);
		mainCanvas.displayProfile(enteredProfile);
		mainCanvas.showMessage("That profile already exists.");
		workingProfile = enteredProfile;
	}
	
	/* Method: profileDoesntExist()
	 * 
	 * This is called if a user tries to view a profile
	 * which does not exist.
	 */
	private void profileDoesntExist() {
		mainCanvas.showMessage("That profile does not exist.");
		workingProfile = null;
	}
	
	/* Method: deleteThatUser(String workingName)
	 * 
	 * This deletes a user from the canvas, the database
	 * and the profile class.
	 * 
	 */
	private void deleteThatUser(String workingName) {
		mainCanvas.removeAll();
		workingProfile = null;
		if(workingDatabase.containsProfile(workingName)) {
			workingDatabase.deleteProfile(workingName);
			mainCanvas.showMessage("Profile has been deleted.");
		}
		else{
			mainCanvas.showMessage("You can't delete what never was.");
		}
	}
	
	/* Method: showTheProfile(String workingName)
	 * 
	 * Shows a profile when the user looks up the name.
	 */
	private void showTheProfile(String workingName) {
		FacePamphletProfile requestedProfile = workingDatabase.getProfile(workingName);
		mainCanvas.displayProfile(requestedProfile);
		mainCanvas.showMessage("Here's that profile you asked for...");
		workingProfile = requestedProfile;
	}
	
	/* Method: changeTheStatus()
	 * 
	 * Updates a status when a user is selected.
	 */
	private void changeTheStatus() {
		String statusInput = statusField.getText();
		if(workingProfile != null) {
			FacePamphletProfile localProfile = workingDatabase.getProfile(workingProfile.getName());
			localProfile.setStatus(localProfile.getName() + " is " + statusInput);
			mainCanvas.displayProfile(localProfile);
			mainCanvas.showMessage("Status has been updated.");
		}
		else{
			mainCanvas.showMessage("Ain' nobody selected to status about.");
		}
	}
    
	/* Method: changeThatPhoto()
	 * 
	 * Changes a photo for the selected user. 
	 */
	private void changeThatPhoto() {
		String pictureFilename = pictureField.getText();
		GImage activeImage = null;
		if(workingProfile != null) {
			FacePamphletProfile activeProfile = workingDatabase.getProfile(workingProfile.getName());
			try {
				activeImage = new GImage(pictureFilename);
				activeProfile.setImage(activeImage);
				mainCanvas.displayProfile(workingProfile);
				mainCanvas.showMessage("Updated picture.");
			} catch (ErrorException ex) {
				mainCanvas.showMessage("Well, that didn't work...");
			}
		}
		else{
			mainCanvas.removeAll();
			mainCanvas.showMessage("Pick a person, first...");
		}
	}
	
	/* Method: addTheFriend(String whoYouAdding)
	 * 
	 * This takes in a string and tries to add that person as
	 * a friend to the active profile.  If it's the same person,
	 * they can't add them-self.  If they're already friends, they
	 * can't re-add.  If they're not already friends, they are, now.
	 */
	private void addTheFriend(String whoYouAdding) {
			FacePamphletProfile activeProfile = workingDatabase.getProfile(workingProfile.getName());
			String openProfileName = workingProfile.getName();
			//Normal add:
			if(workingDatabase.containsProfile(whoYouAdding) && !whoYouAdding.equals(openProfileName)) {
				FacePamphletProfile friendProfile = workingDatabase.getProfile(whoYouAdding);
				if(activeProfile.addFriend(whoYouAdding)) {
					//Adds you to them:
					friendProfile.addFriend(openProfileName);
					//Adds them to you:
					activeProfile.addFriend(whoYouAdding);
					mainCanvas.displayProfile(activeProfile);
					mainCanvas.showMessage("New friend has been added.");
				}
				else {
					mainCanvas.showMessage("They're already your friend.");
				}
			}
			//When they try to add themselves as a friend:
			if(activeProfile.getName().equals(whoYouAdding)) {
				mainCanvas.showMessage("Sorry, bro.  You need a real friend.");
			}
			//When the person you're adding doesn't exist:
			if(!workingDatabase.containsProfile(whoYouAdding)) {
				mainCanvas.showMessage("Can't be friends-- they not real.");
			}
	}
	
	/*
	 * Variables:
	 */
	//Instances of the other classes used by the program:
	private FacePamphletCanvas mainCanvas = new FacePamphletCanvas();
	private FacePamphletDatabase workingDatabase = new FacePamphletDatabase();
	private FacePamphletProfile workingProfile = null;
	//Data input fields:
	private JTextField nameField;
	private JTextField statusField;
	private JTextField pictureField;
	private JTextField friendField;
	
}
