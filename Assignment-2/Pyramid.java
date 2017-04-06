
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
// Pyramid.java                                                //
//                                                             //
// Description:                                                //
//                                                             //
// Solution to Assignment 2 in CS106A at StamFurd University.  //
//                                                             //
// Pyramid.java will solve the problem of building a centered  //
// pyramid with brick dimensions being defined by BRICK_WIDTH  //
// and BRICK_HEIGHT.  The base level of the pyramid will start //
// with a pre-determined number of BRICKS_IN_BASE and decrease //
// by one brick as each level ascends.                         //
//                                                             //
//                                            Created 11/16/16 //
// /////////////////////////////////////////////////////////// //

// Includes:
import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

	/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

	/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

	/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;

	
	
	public void run() {
		evenOddTest();
	}

	
	
	/* evenOddTest ()
	 * considers two cases:
	 * Either BRICKS_IN_BASE is an even number,
	 * or BRICKS_IN_BASE is an odd number.
	 */
	private void evenOddTest() {
		
		//Even:
		if ( BRICKS_IN_BASE % 2 == 0 ) {
			buildEvenBase();
			buildEvenRows();
		}
		
		//Odd:
		else {
			buildOddBase();
			buildOddRows();
			
		}
	}
	
	
	
	/* buildEvenBase() 
	 * creates the bottom row of a pyramid when BRICKS_IN_BASE 
	 * is an even number.  numberOfBricks increases by one until
	 * it is strictly less than BRICKS_IN_BASE. The variables 
	 * "positionX" and "positionY" define a location for each 
	 * brick and change with each iteration of the loop.
	 * The variable "numberOfBricks" must start at 0.
	 */

	private void buildEvenBase() {
		for (int numberOfBricksUsed = 0 ; numberOfBricksUsed < BRICKS_IN_BASE ; numberOfBricksUsed++) {
			double positionX = ( getWidth() / 2 ) - ( .5 * BRICKS_IN_BASE * BRICK_WIDTH ) + ( BRICK_WIDTH * numberOfBricksUsed );
			double positionY = getHeight() - BRICK_HEIGHT;
			GRect oneEvenBaseBrick = new GRect ( positionX , positionY , BRICK_WIDTH , BRICK_HEIGHT);
			add ( oneEvenBaseBrick );
		}
	} 
	
	
	
	/*buildEvenRows() 
	 * builds the rest of a pyramid when BRICKS_IN_BASE is an even number.
	 * While the rowNumber is less than BRICKS_IN_BASE, the outside loop runs.
	 * While oneBrick is less than bricksPerRow, the inside loop runs.
	 * Variables positionX and positionY define the position of each brick.
	 * Variable x grows as the value of oneBrick increases, y as the rowNumber increases.
	 * Variable rowNumber must start at 1, because the base has already been laid.
	 */
	private void buildEvenRows() {
		for (double rowNumber = 1 ; rowNumber < BRICKS_IN_BASE ; rowNumber++ ){
			double bricksPerRow = BRICKS_IN_BASE - rowNumber;
			for (double numberOfBricksUsed = 0 ; numberOfBricksUsed < bricksPerRow ; numberOfBricksUsed++){
				double positionX = ( getWidth() / 2 ) - ( .5*BRICKS_IN_BASE * BRICK_WIDTH ) + ( .5 * BRICK_WIDTH * rowNumber ) + ( numberOfBricksUsed * BRICK_WIDTH );
				double positionY = getHeight() - ( BRICK_HEIGHT * (rowNumber + 1 ));
				GRect oneEvenRowBrick = new GRect ( positionX , positionY , BRICK_WIDTH , BRICK_HEIGHT);
				add (oneEvenRowBrick);
			}
		}
	}
	
	
	
	/* buildOddBase()
	 * similar to buildEvenBase, except that half a brick width
	 * is added to the x position, to account for the offset caused by odd numbers of bricks /2.
	 */
	private void buildOddBase() {
		for (int numberOfBricksUsed = 0 ; numberOfBricksUsed < BRICKS_IN_BASE ; numberOfBricksUsed++) {
			double positionX = ( ( getWidth () / 2 ) - ( .5*BRICKS_IN_BASE * BRICK_WIDTH ) - ( BRICK_WIDTH * .5 ) + ( BRICK_WIDTH * numberOfBricksUsed) );
			double positionY = getHeight() - BRICK_HEIGHT;
			GRect oneOddBaseBrick = new GRect ( positionX , positionY , BRICK_WIDTH , BRICK_HEIGHT);
			add (oneOddBaseBrick);
		}
	}
	
	
	
	/*buildOddRows()
	 * similar to buildEvenRows, except again, half a brick width
	 * has been added to the x position to account for the odd number of bricks in the base.
	 */
	private void buildOddRows() {
		for (double rowNumber = 1 ; rowNumber < BRICKS_IN_BASE ; rowNumber++){
			double bricksPerRow = BRICKS_IN_BASE - rowNumber;
			for (double numberOfBricksUsed = 0 ; numberOfBricksUsed < bricksPerRow ; numberOfBricksUsed++){
				double positionX = ( getWidth() / 2 ) - ( .5*BRICKS_IN_BASE * BRICK_WIDTH ) - ( BRICK_WIDTH * .5 ) + ( .5 * BRICK_WIDTH * rowNumber ) + ( numberOfBricksUsed * BRICK_WIDTH );
				double positionY = getHeight() - ( BRICK_HEIGHT * (rowNumber + 1) );
				GRect oneOddRowBrick = new GRect ( positionX , positionY , BRICK_WIDTH , BRICK_HEIGHT);
				add (oneOddRowBrick);
			}
		}
	}
}