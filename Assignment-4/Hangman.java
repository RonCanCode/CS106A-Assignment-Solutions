/*
 * File: Hangman.java
 * ------------------
 * 
 * Name:  Ron Guglielmone
 * 
 * Section Leader:  Bruno De Martino
 * 
 * This is the main controller for Hangman.
 * It sends to methods in the HangmanCanvas class
 * and receives words from the HangmanLexicon class.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

	//Random Number Generator:
	private RandomGenerator rgen = RandomGenerator.getInstance();
	//Source for the words:
	private HangmanLexicon wordSource = new HangmanLexicon();
	//Tracks the number of bad guesses:
    private int numberOfGuesses = 8;
    //Stores the length of a word:
    private int numberOfCharacters;
    //Stores the dashed version of a word:
    private String theSecretWord;
    //This is the answer:
    private String theAnswerWord;
    //The guessed Character:
    private char guessedCharacter;
    //All of the wrong guesses:
    private String wrongCharacters;
    //The canvas window:
    private HangmanCanvas canvas;
    
    public void init() {
        canvas = new HangmanCanvas();
        add(canvas);
    }
    
    public void run() {
    	setUpTheGame();
    	theMainLoop();
	}
    
    
    /*Method:  setUpTheGame()
     * 
     * Assigns a value to variable theAnswerWord.
     * Sends theAnswerWord to hideTheWord to create a dashed version.
     * Prints a "welcome" message.
     */
	private void setUpTheGame() {
    	theAnswerWord = wordSource.getWord (rgen.nextInt(0,(wordSource.getWordCount() - 1)) );
		theSecretWord = hideTheWord( theAnswerWord );
		println("Welcome to Hangman");
		canvas.reset();	
	}
	
	
	/*Method:  hideTheWord
	 * 
	 * Changes theAnswerWord to a series of dashes.
	 * i.e. --------
	 */
	private String hideTheWord(String theAnswerWord) {
		numberOfCharacters = theAnswerWord.length();
		String theSecretWord = "";
		for (int i = 0; i < numberOfCharacters; i++){
			theSecretWord += "-";	
		}
		return theSecretWord;
	}
	
	
	/*Method:  theMainLoop()
	 * 
	 * Checks the number of guesses left, and whether theAnswerWord has been found.
	 * Tells the user what their current guess looks like, and reads their next guess.
	 * Sends their guess to validateTheGuess and the guessCheck methods.
	 * Identifies a winning or losing condition and terminates the game.
	 */
	private void theMainLoop() {
		while (numberOfGuesses > 0 && !(theSecretWord.equals(theAnswerWord)) ) {
			println("The word now looks like this: " + theSecretWord);
			println("You have " + numberOfGuesses + " guesses left.");
			String guess = readLine("Your guess: ");
			//Makes sure a guess is valid
			guessedCharacter = validateTheGuess(guess);
			//Checks if the guess is part of theAnswerWord
			guessChecker(guessedCharacter, theAnswerWord);
		}
		if (numberOfGuesses == 0) {
			println("The word was " + theAnswerWord +".");
			println("Game Over...");
		}
		else{
			println("Yep, that's the word.");
		}
	}
	
	
	/*Method:  validateTheGuess
	 * 
	 *Insures that the guess is only one letter.
	 *Also insures that the guess is upper-cased.
	 */
	private char validateTheGuess(String str) {
		while (true) {
			if(str.length() > 1) {
				str = readLine("This is an invalid guess.  Try again: ");
			}
			if(str.length() == 1) break;
		}
		guessedCharacter = str.charAt(0);
		if(Character.isLowerCase(guessedCharacter)) {
			guessedCharacter = Character.toUpperCase(guessedCharacter);
		}
		return guessedCharacter;
	}
	
	
	/*Method:  guessChecker
	 * 
	 * Checks an individual guess against theAnswerWord.
	 * If the letter is part of theAnswerWord, theSecretWord is re-written.
	 */
	private void guessChecker(char guessedCharacter, String theAnswerWord) {
		if(theAnswerWord.indexOf(guessedCharacter) == -1) {
			println("There are no " + guessedCharacter + "'s in the word...");
			numberOfGuesses--;
			wrongCharacters += guessedCharacter;
			canvas.noteIncorrectGuess(guessedCharacter);
		}
		if(theAnswerWord.indexOf(guessedCharacter) != -1) {
			println("That guess is correct.");
		}
		for(int i = 0; i < theAnswerWord.length(); i++) {
			if (guessedCharacter == theAnswerWord.charAt(i)) {
				if (i > 0) {
					theSecretWord = theSecretWord.substring(0, i) + guessedCharacter + theSecretWord.substring(i + 1);
					canvas.displayWord(theSecretWord);
				}
				if(i == 0) {
					theSecretWord = guessedCharacter + theSecretWord.substring(1);
					canvas.displayWord(theSecretWord);
				}
			}
		}
	}
}
