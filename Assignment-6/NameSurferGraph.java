/*
 * File: NameSurferGraph.java  UPDATED.
 * ---------------------------
 * NameSurferGraph is responsible for drawing the graph on a 
 * GCanvas.  Every time update() is called, the graph will
 * be re-rendered.
 * 
 * Name: Ronald Guglielmone
 * Class:  CS106A, M/W/F, Stanford, Fall 2014.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.awt.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
    	inputNameEntries = new ArrayList <NameSurferEntry>();
        addComponentListener(this);
    }

    /* Method: clear()
     * 
     * Removes everything but the background of the graph.
     */
    public void clear() {
    	inputNameEntries.clear();
        update();
    }

    /* Method: addEntry(NameSurferEntry entry)
     * 
     * Adds a NameSurferEntry to the List "inputNameEntries".
     * Then updates the graph with the new List item.
     */
    public void addEntry(NameSurferEntry entry) {
    	inputNameEntries.add(entry);
        update();
    }

    /* Method: update()
     * 
     * Updates the GCanvas by removing everything
     * then adding the background grids, and finally
     * graphing the contents of the List.
     */
    public void update() {
        removeAll();
        backgroundGridConstructor();
        generalGrapher();
    }
    
    /* Method: generalGrapher()
     * 
     * Loops through all the entries and calls specificGrapher.
     */
    private void generalGrapher() {
        int localWidth = NDECADES / getWidth();
        int count = 0;
        int stop = inputNameEntries.size();
        while (count < stop) {
            NameSurferEntry entry = inputNameEntries.get(count);
            specificGrapher(entry, localWidth, count);
            count++;
        }
    }
    
    /* Method: specificGrapher(NameSurferEntry inputEntry, int localWidth, int i)
     * 
     * This is a terrible, messy, jumbled method that basically does all of the
     * graphing with just a little bit of help from zeroTester...  This should
     * have been decomposed more, and if I have time I will do so, but I'm already
     * way past due on this assignment.  
     */
    private void specificGrapher(NameSurferEntry inputEntry, int localWidth, int i) {
    	int count = 0;
        int stop = NDECADES;
    	while (count < stop) {
            //Declaring input values and relationships:
    		int verticalA = inputEntry.getRank(count);
            int verticalB = 0;
            localWidth = getWidth() / NDECADES;
            String workingRank = Integer.toString(inputEntry.getRank(count));
            //Checks for the zero case:
            verticalA = zeroTester(verticalA);
            workingRank = zeroTester(workingRank);
            double heightRatio = (double) (getHeight() - 2 * GRAPH_MARGIN_SIZE) / MAX_RANK;
            //Adds the rank labels:
            GLabel rank = new GLabel(" " + inputEntry.getName() + " " + workingRank);
            add(rank, (localWidth * count), ( (verticalA * heightRatio) + GRAPH_MARGIN_SIZE) );
            //Adds the connecting lines:
            if (count < NDECADES -1) {
                verticalB = inputEntry.getRank(count + 1); 
                verticalB = zeroTester(verticalB);
                GLine oneLine = new GLine((localWidth * count), 
                		((verticalA * heightRatio) + GRAPH_MARGIN_SIZE), 
                		(localWidth * (count + 1)),
                        ((verticalB * heightRatio) + GRAPH_MARGIN_SIZE));
                oneLine.setColor(lineColorChooser(i));
                add(oneLine);
            }
            count++;
        }
    }

    /* Method: zeroTester(String or int workingRank)
     * 
     * Tests for the condition that the rank value is 0.
     */
    //For string case:
    private String zeroTester(String workingRank) {
        if (workingRank.equals("0")) {
            return "*";
        }
        else { return workingRank; }
    }
    //For int case:
    private int zeroTester(int workingRank) {
        if (workingRank == 0) {
            return MAX_RANK;
        }
        else { return workingRank; }
    }
    
    /* Method: backgroundGridConstructor()
     * 
     * Builds the background grid that the data is displayed onto.
     */
    private void backgroundGridConstructor() {
    	constructVerticalAxis();
    	constructHorizontalAxis();
    }
    
    /* Method: constructVerticalAxis()
     * 
     * Builds the vertical lines and adds the year labels.
     */
    private void constructVerticalAxis() {
    	decadeWidth = getWidth() / NDECADES;
        for (int i = 1; i < NDECADES + 1; i++) {
            if (i < NDECADES) {
                GLine verticalLine = new GLine (i * decadeWidth, 0, i * decadeWidth, getHeight());
                add(verticalLine);
            }
            Integer workingYear = START_DECADE + (i - 1) * 10;
            GLabel yearLabel = new GLabel(workingYear.toString());
            add(yearLabel, (i - 1) * decadeWidth, getHeight() - (GRAPH_MARGIN_SIZE - yearLabel.getHeight()));
        }
    }
    
    /* Method: constructverticalAxis()
     * 
     * Builds the top and bottom margin lines.
     */
    private void constructHorizontalAxis() {
    	GLine topMarginLine = new GLine (0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
        GLine bottomMarginLine = new GLine (0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
        add(topMarginLine);
        add(bottomMarginLine);
    }
    
    /* Method: lineColorChooser(int j)
     * 
     * Assigns a color to the line being drawn.
     */
    private Color lineColorChooser(int j) {
        if (j > 3) {
        }
        if (j % 4 == 0) {
            return Color.MAGENTA;
        }
        if (j % 4 == 1) {
            return Color.RED;
        }
        if (j % 4 == 2) {
            return Color.BLUE;
        }
        if (j % 4 == 3) {
            return Color.BLACK;
        }
        else { return null; }
    }
    
    /*
     * Instance Variables:
     */
    private List <NameSurferEntry> inputNameEntries;
    int decadeWidth;
    
    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) { }
    public void componentMoved(ComponentEvent e) { }
    public void componentResized(ComponentEvent e) { update(); }
    public void componentShown(ComponentEvent e) { }
}
