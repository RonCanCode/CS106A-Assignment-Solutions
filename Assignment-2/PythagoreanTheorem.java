
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
// PythagoreanTheorem.java                                     //
//                                                             //
// Description:                                                //
//                                                             //
// Solves Assignment 2 for CS106A at StamFurd University.      //
//                                                             // 
// This program will compute the square root of (a^2 + b^2)    //
// in terms of c.                                              //
//                                                             // 
//                                                             //
//                                            Created 10/11/14 //
// /////////////////////////////////////////////////////////// //

// Includes:
import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		println ("Enter values to compute the Pythagorean theorem.");
		double a = readDouble("a:");
		double b = readDouble("b:");
		double c = Math.sqrt((a * a) + (b * b));
		println ("c = " +c);
	}
}
