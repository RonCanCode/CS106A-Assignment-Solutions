/*
 * File: NameSurfer.java  UPDATED.
 * ---------------------------
 * The NameSurfer program enables a user to search a US Census data file
 * defined by the NameSurferConstands.java NAMES_DATA_FILE string.  This
 * file ranks the popularity of names from 1900-2000.
 * 
 * Name: Ronald Guglielmone
 * Class:  CS106A, M/W/F, Stanford, Fall 2014.
 */

import java.awt.event.ActionEvent;
import javax.swing.*;
import acm.program.Program;

public class NameSurfer extends Program implements NameSurferConstants {

    /* Method: init()
     * 
     * Starts the program running.  Calls a series of helper methods to
     * create the objects, add the objects, and add action listeners.
     */
    public void init() {
    	createObjects();
    	addInteractors();
    	addListeners();
    }
    
    /* Method: createObjects()
     * 
     * Defines each of the interactors.
     * Also defines the graph and database objects.
     */
    private void createObjects() {
    	nameField = new JTextField(20);
        graphButton = new JButton("Graph");
        clearButton = new JButton("Clear");
        graphInstance = new NameSurferGraph();
        workingDatabase = new NameSurferDataBase(NAMES_DATA_FILE);
    }
    
    /* Method: addInteractors()
     * 
     * Adds all of the interactors.
     * Also adds the graph object and the JLabel for nameField.
     */
    private void addInteractors() {
        add(new JLabel("Name"), NORTH);
        add(nameField, NORTH);
        add(graphButton, NORTH);
        add(clearButton, NORTH);
        add(graphInstance);
    }
    
    /* Method: addListeners()
     * 
     * Adds action listeners.
     */
    private void addListeners() {
        addActionListeners();
        nameField.addActionListener(this);
    }
    
    /* Method: actionPerformed(ActionEvent e)
     * 
     * Responds to an action event via one of the interactors.
     * 
     */
    public void actionPerformed(ActionEvent e)  {
    	
        String action = e.getActionCommand();
        String nameFieldText = nameField.getText().toUpperCase();
        
        if (action.equals("Graph")) {
            graph(nameFieldText);
            }
        if (action.equals("Clear")) {
        	graphInstance.clear();
        }
    }
    
    /* Method: graph(String nameFieldText)
     * 
     * Takes in a text entry from nameFieldText and
     * calls addEntry in NameSurferGraph if the entry
     * exists in NameSurferDatabase.
     */
	private void graph(String nameFieldText) {
		NameSurferEntry workingEntry = workingDatabase.findEntry(nameFieldText);
        if (workingEntry != null) {
        	graphInstance.addEntry(workingEntry);
        }
	}
	
/*
 * Instance Variables:
 */
    private NameSurferDataBase workingDatabase;
    private NameSurferGraph graphInstance;
    
    //Interactors:
    private JTextField nameField;
    private JButton graphButton;
    private JButton clearButton;
}
