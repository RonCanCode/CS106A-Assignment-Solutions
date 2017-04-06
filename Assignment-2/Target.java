
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
// Target.java                                                 //
//                                                             //
// Description:                                                //
//                                                             //
// Solution for Assignment 2, CS106A at StamFurd University.   //
//                                                             //
// Target.java will create a centered target with a radius     //
// defined by TARGET_RADIUS.  The outer ring is red, the       //
// middle ring is white, and the inner circle is red. The      //
// middle ring is 65% the size of the full target, the         //
// bull's-eye is 30% of 2 x TARGET_RADIUS.                     //
//                                                             //
//                                            Created 10/07/14 //
// /////////////////////////////////////////////////////////// //

// Includes:
import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	
	// This defines the radius of our target.
	private static final int TARGET_RADIUS = 72;
	
	public void run() {
		drawOuterCircle();
		drawMiddleCircle();
		drawInnerCircle();
	}
	
	
	
	/* drawOuterCircle() 
	 * makes a filled in red circle with a radius defined by 
	 * TARGET_RADIUS.
	 */
	private void drawOuterCircle() {
		double x = ( getWidth() / 2 ) - TARGET_RADIUS;
		double y = ( getHeight() / 2 ) - TARGET_RADIUS;
		GOval biggestcircle = new GOval (x, y, (TARGET_RADIUS * 2), (TARGET_RADIUS * 2) );
		biggestcircle.setFilled (true);
		biggestcircle.setFillColor (Color.RED);
		add (biggestcircle);
	}
	
	
	
	/* drawMiddleCircle() 
	 * makes a filled in white circle with a radius defined 
	 * by 65% of TARGET_RADIUS.
	 */
	private void drawMiddleCircle() {
		double x = ( getWidth() / 2 ) - ( TARGET_RADIUS * .65 );
		double y = ( getHeight() / 2 ) - ( TARGET_RADIUS * .65 );
		GOval middlecircle = new GOval (x, y, ( (TARGET_RADIUS * .65 ) * 2), ( (TARGET_RADIUS * .65 ) * 2) );
		middlecircle.setFilled (true);
		middlecircle.setFillColor (Color.WHITE);
		add (middlecircle);
	}
	
	
	
	/* drawInnerCircle()
	 * makes a filled in red circle with a radius defined by 30% 
	 * of TARGET_RADIUS.
	 */
	private void drawInnerCircle() {
		double x = ( getWidth() / 2 ) - ( TARGET_RADIUS * .3 );
		double y = ( getHeight() / 2 ) - ( TARGET_RADIUS * .3 );
		GOval innercircle = new GOval (x, y, ( (TARGET_RADIUS * .3 ) * 2), ( (TARGET_RADIUS * .3 ) * 2) );
		innercircle.setFilled (true);
		innercircle.setFillColor (Color.RED);
		add (innercircle);
	}		
}
