
// /////////////////////////////////////////////////////////// //
//        ___           ___           ___           ___        //
//       /  /\         /  /\         /  /\         /  /\       //
//      /  /::\       /  /::\       /  /::\       /  /::\      //
//     /  /:/\:\     /  /:/\:\     /  /:/\:\     /  /:/\:\     //
//    /  /:/  \:\   /  /:/  \:\   /  /:/  \:\   /  /:/  \:\    //
//   /__/:/_\_ \:\ /__/:/ \__\:\ /__/:/ \__\:\ /__/:/_\_ \:\   //
//   \  \:\__/\_\/ \  \:\ /  /:/ \  \:\ /  /:/ \  \:\__/\_\/   //
//    \  \:\ \:\    \  \:\  /:/   \  \:\  /:/   \  \:\ \:\     //
//     \  \:\/:/     \  \:\/:/     \  \:\/:/     \  \:\/:/     //
//      \  \::/       \  \::/       \  \::/       \  \::/      //
//       \__\/         \__\/         \__\/         \__\/       //
//                                                             //
//   ( G ) ( O ) ( O ) ( G ) ( i ) ( E ) ( @ ) stanford .edu   //
//                                                             //
// //////////////////////////////////////////////////////////  //
//                                                             //
// CS106ATiles.java                                            //
//                                                             //
// Description:                                                //
//                                                             //
// CS106ATiles.java solves the very specific problem of        //
// creating four centered labels inside of four tiles          //
// with tile dimensions defined by TILE_SPACE and TILE_WIDTH.  //
//                                                             //
// I could have (and should have) generalized the processes of //
// making a tile and making a label, but I didn't leave myself //
// enough time for this assignment, so I just solved it with   //
// some extra redundancy...                                    //
//                                                             //
//                                            Created 09/29/14 //
// /////////////////////////////////////////////////////////// //

// Includes:
import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class CS106ATiles extends GraphicsProgram {
	
	/** Amount of space (in pixels) between tiles */
	private static final int TILE_SPACE = 20;
	
	/** Width (in pixels) of one tile */
	private static final int TILE_WIDTH = 100;
	
	/** Height (in pixels) of one tile */
	private static final int TILE_HEIGHT = 50;

	public void run() {
		buildUpperLeftTile();
		buildUpperRightTile();
		buildLowerLeftTile();
		buildLowerRightTile();
		fillUpperLeftTile();
		fillUpperRightTile();
		fillLowerLeftTile();
		fillLowerRightTile();
	}
	
	
	/* The following four methods create four tiles,
	 * each spaced symmetrically around a center point
	 * by a distance defined in "TILE_SPACE", and with
	 * tile dimensions defined by "TILE_WIDTH" and "TILE_HEIGHT". 
	 */

	// Upper Left Tile:
	private void buildUpperLeftTile() {
		double x = ( (getWidth() / 2) - (TILE_SPACE / 2) - TILE_WIDTH );
		double y = ( (getHeight() / 2 ) - (TILE_SPACE / 2) - TILE_HEIGHT);
		GRect upperLeftOutline = new GRect ( x, y, TILE_WIDTH, TILE_HEIGHT );
		add (upperLeftOutline);
	}

	// Upper Right Tile:
	private void buildUpperRightTile() {
		double x = ( (getWidth() / 2) + (TILE_SPACE / 2) );
		double y = ( (getHeight() / 2 ) - (TILE_SPACE / 2) - TILE_HEIGHT);
		GRect upperRightOutline = new GRect ( x, y, TILE_WIDTH, TILE_HEIGHT );
		add (upperRightOutline);
	}

	// Lower Left Tile:
	private void buildLowerLeftTile() {
		double x = ( (getWidth() / 2) - (TILE_SPACE / 2) - TILE_WIDTH );
		double y = ( (getHeight() / 2 ) + (TILE_SPACE / 2) );
		GRect lowerLeftOutline = new GRect ( x, y, TILE_WIDTH, TILE_HEIGHT );
		add (lowerLeftOutline);
	}

	// Lower Right Tile:
	private void buildLowerRightTile() {
		double x = ( (getWidth() / 2) + (TILE_SPACE / 2) );
		double y = ( (getHeight() / 2 ) + (TILE_SPACE / 2) );
		GRect lowerRightOutline = new GRect ( x, y, TILE_WIDTH, TILE_HEIGHT );
		add (lowerRightOutline);
	}
	
	
	
	/* The next four methods place a label at
	 * the center of each tile which reads
	 * "CS 106A".
	 */

	// Upper Left Label:
	private void fillUpperLeftTile() {
		GLabel upperLeftLabel = new GLabel ("CS 106A");
		double x = ( (getWidth() / 2) - (TILE_SPACE / 2) - (TILE_WIDTH /2) - (.5 * upperLeftLabel.getWidth()) );
		double y = ( (getHeight() / 2) - (TILE_SPACE / 2) - (TILE_HEIGHT /2) + (.5 * upperLeftLabel.getAscent()) );
		upperLeftLabel.setLocation (x,y);
		add (upperLeftLabel);
	}

	// Upper Right Label:
	private void fillUpperRightTile() {
		GLabel upperRightLabel = new GLabel ("CS 106A");
		double x = ( (getWidth() / 2) + (TILE_SPACE / 2) + (TILE_WIDTH /2) - (.5 * upperRightLabel.getWidth()) );
		double y = ( (getHeight() / 2) - (TILE_SPACE / 2) - (TILE_HEIGHT /2) + (.5 * upperRightLabel.getAscent()) );
		upperRightLabel.setLocation (x,y);
		add (upperRightLabel);
	}

	// Lower Left Label:
	private void fillLowerLeftTile() {
		GLabel lowerLeftLabel = new GLabel ("CS 106A");
		double x = ( (getWidth() / 2) - (TILE_SPACE / 2) - (TILE_WIDTH /2) - (.5 * lowerLeftLabel.getWidth()) );
		double y = ( (getHeight() / 2) + (TILE_SPACE / 2) + (TILE_HEIGHT /2) + (.5 * lowerLeftLabel.getAscent()) );
		lowerLeftLabel.setLocation (x,y);
		add (lowerLeftLabel);
	}

	// Lower Right Label:
	private void fillLowerRightTile() {
		GLabel lowerRightLabel = new GLabel ("CS 106A");
		double x = ( (getWidth() / 2) + (TILE_SPACE / 2) + (TILE_WIDTH /2) - (.5 * lowerRightLabel.getWidth()) );
		double y = ( (getHeight() / 2) + (TILE_SPACE / 2) + (TILE_HEIGHT /2) + (.5 * lowerRightLabel.getAscent()) );
		lowerRightLabel.setLocation (x,y);
		add (lowerRightLabel);
	}
}

