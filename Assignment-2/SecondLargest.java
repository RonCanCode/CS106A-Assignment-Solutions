
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
// SecondLargest.java                                          //
//                                                             //
// Description:                                                //
//                                                             //
// Solves the Second Largest problem on Assignment 2, CS106A   //
// at StamFurd University.                                     //
//                                                             //
//                                            Created 11/16/16 //
// /////////////////////////////////////////////////////////// //

// Includes:
import acm.program.*;

public class SecondLargest extends ConsoleProgram {
	public void run() {
		giveInstructions();
		collectAndProcessValues();
	}
	
	
	
	/* giveInstructions() 
	 * writes a user-friendly description of how the program 
	 * functions conceptually.
	 */
	private void giveInstructions () {
		println ("This program finds the largest and smallest numbers.");
		println ("Enter multiple values greater than zero.");
		println ("To process the data, enter a value of \"0\".");
	}
	
	
	
	/* collectAndProcessValues()
	 * A "FirstValue" variable is built, and a test is run to 
	 * ensure that a non-zero number was entered. Then, two 
	 * variables are defined for "smallestValue" and "largestValue" 
	 * (they both start at "FirstValue"). Next, a while loop tests 
	 * each input against "smallestValue" and "largestValue", 
	 * replacing them if needed. FINALLY, the values are printed...
	 */
	private void collectAndProcessValues() {
		
		// Accounts for the case when a user doesn't enter any input.
		double FirstValue = readDouble("input:");
		if (FirstValue == SENTINEL){
			println ("You have not entered any values.");
		}
		
		// Everything "else" consists of a normal run.
		else{
			double secondLargest = FirstValue;
			double largestValue = FirstValue;  
			
			// FirstValue is printed twice at this point.
			
			while (true) {
				double workingValue = readDouble("input:");
				if (workingValue == SENTINEL) break;  //Exits the loop.
				
				// Replaces the smallestValue with current input.
				if (workingValue != 0 && workingValue >= largestValue){
					secondLargest = largestValue;
					largestValue = workingValue;
				}
				
				// Replaces the largestValue with current input.
				else {
					if (workingValue != 0 && workingValue > secondLargest) {
						secondLargest = workingValue;
					}
				}
			}
			
			// Prints the result:
			println ("The second largest value is: " +secondLargest );
			println ("The largest value is: " +largestValue );
		}	
	}
	

	
	// This is the value used to signal the end of user input.
	private static final int SENTINEL = 0;
}