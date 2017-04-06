
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
// Hailstone.java                                              //
//                                                             //
// Description:                                                //
//                                                             //
// Solution to Assignment 2 in CS106A at StamFurd University.  //
//                                                             //
// Computes a Hailstone Sequence for any input greater than 0. //
// A Hailstone Sequence follows the following rules:           //
//                                                             //
// 1. Start with a number.                                     //
// 2. If it is even, divide by 2.                              //
// 3. If it is odd, multiply by 3 and add 1.                   //
// 4. Repeat until 1 is reached.                               //
//                                                             //
// The Collatz conjecture suggests that every number           //
// eventually settles to 1, but it hasn't been proven.         //
//                                                             //
//                                                             //
//                                            Created 11/16/16 //
// /////////////////////////////////////////////////////////// //

// Includes:
import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		userInstructions();
		runTheFunction();
	}
	
	
	
	/*userInstructions is a description of what the program does.
	 */
	private void userInstructions() {
		println ("Enter a number below to see its Hailstone Sequence...");
	}
	
	
	
	private void runTheFunction() {
		
		//PrimaryValue is the user-provided input, which loops.
		double PrimaryValue = readDouble ("First number:");
		
		//NumberOfSteps is used to track the number of steps.
		int NumberOfSteps = 0;
		
		//This accounts for improper inputs.
		if (PrimaryValue <= 0) {
			println ("This is not an acceptable input.");
		}
		
		//This is the logic of both even and odd cases.
		else {
			while (PrimaryValue != 1) {
				//This computes the even function.
				if (PrimaryValue % 2 == 0) {
					println (PrimaryValue+ " is even so I take half: " +(PrimaryValue/2));
					PrimaryValue = (PrimaryValue / 2);
					NumberOfSteps = (NumberOfSteps + 1);
				}
				//This computes the odd function.
				else {
					println (PrimaryValue+ " is odd so I make 3n + 1: " +((PrimaryValue*3)+1));
					PrimaryValue = ( (PrimaryValue * 3) + 1);
					NumberOfSteps = (NumberOfSteps + 1);
				}
			}
			//This prints the number of steps
			println ("The process took " +NumberOfSteps +" steps to reach 1.");
		}
	}
}

