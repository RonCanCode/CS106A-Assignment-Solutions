/*
 * File: Yahtzee.java
 * ------------------
 * Name:  Ron Guglielmone
 * 
 * This is the main .java file for Yahtzee, Assignment 5: CS106 A.
 * It uses YahtzeeConstants.java, and the Yahtzeelib.jar.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	//I have no clue what this thing does:
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		setUp();
		playGame();
		assignBonusPoints();
		calculateWhoWonTheGame();
		
	}

	/* Method:  setUp()
	 *
	 * Sets up the game.
	 */
	private void setUp() {
		getPlayerInfo();
		createScoreCards();
		buildYahtzeeDisplay();
	}
	
	/* Method:  getPlayerInfo()
	 * 
	 * Populates the playerNames array.
	 */
	private void getPlayerInfo() {
		nPlayers = getNumberOfPlayers();
		IODialog dialog = getDialog();
		playerNames = new String [nPlayers];   
		for (int i = 1; i <= nPlayers; i++) {
			playerNames [i - 1] = dialog.readLine("Enter name for player " + i + ":");
		}
	}

	/* Method:  getNumberOfPlayers()
	 * 
	 * Defines nPlayers variable and checks for acceptable input.
	 */
	private int getNumberOfPlayers() {
		IODialog dialog = getDialog();
		while (true) {
			int checkPlayerNumber = dialog.readInt("Enter number of players:");
			if (checkPlayerNumber > 0 && checkPlayerNumber <= MAX_PLAYERS)
				return checkPlayerNumber;
			dialog.println("This is not a valid number of players.");
		}
	}
	
	/* Method:  createScoreCards()
	 * 
	 * Does pretty much what the name says...
	 */
	private void createScoreCards() {
		mainScoreCard =  new int [nPlayers + 1] [N_CATEGORIES + 1];
		usedCategories = new boolean [nPlayers + 1] [N_CATEGORIES + 1];     
	}
	
	/* Method:  buildYahtzeeDisplay()
	 * 
	 * Makes a Yahtzee Display.  That's all...
	 */
	private void buildYahtzeeDisplay() {
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
	}
	
	/* Method:  playGame()
	 * 
	 * Builds the main loop around N_SCORING_CATEGORIES and nPlayers.
	 * Each player gets N_SCORING_CATEGORIES turns.
	 */
	private void playGame() {
		for (int turn = 0; turn < N_SCORING_CATEGORIES; turn++) {
			for (int player = 1; player <= nPlayers; player++) {
				rollThemDice(player);
				rollThemDiceAgain(player);
				rollThemDiceOneLastTime(player);
				makeYourChoice(player);
			}
		}
	}
	
	/* Method:  rollThemDice(int whoBeRolling)
	 * 
	 * Rolls N_DICE number of times, storing each result
	 * in a workingDiceValues array.
	 */
	private void rollThemDice(int whoBeRolling) {
		roll(true);
		display.printMessage(playerNames [whoBeRolling - 1] + "'s turn! Click " + "\"Roll Dice\" " + ".");
		display.waitForPlayerToClickRoll(whoBeRolling);
		display.displayDice(workingDiceValues);
	}
	
	/* Method:  rollThemDiceAgain(int sameFoolStillRolling)
	 * 
	 * Same basic thing as above, but for the second roll.
	 */
	private void rollThemDiceAgain(int sameFoolStillRolling) {
		display.printMessage("Select dice for a re-roll:");
		display.waitForPlayerToSelectDice();
		roll(false);
		display.displayDice(workingDiceValues);
	}

	/* Method:  rollThemDiceOneLastTime(int thisDudeWontQuit)
	 * 
	 * Same basic thing as above, but for the second roll.
	 */
	private void rollThemDiceOneLastTime(int thisDudeWontQuit) {
		display.printMessage("Select dice for one last re-roll:");
		display.waitForPlayerToSelectDice();
		roll(false);
		display.displayDice(workingDiceValues);
	}

	/* Method: roll(boolean rollAll)
	 * 
	 * Generic roll method, takes in a boolean.
	 * If boolean is false, selected dice are re-rolled.
	 * If boolean is true, all dice are rolled.
	 */
	private void roll(boolean rollAll) {
		for (int i = 0; i < N_DICE; i++) {
			if (rollAll || display.isDieSelected(i)) {
				workingDiceValues[i] = rgen.nextInt(1, N_DICE);
			}				
		}
	}

	/* Method:  makeYourChoice(int thatSameDude)
	 * 
	 * Method for selecting a category for scoring.
	 * Requires the category to be available, then
	 * switches usedCategories array to true.
	 */
	private void makeYourChoice(int thatSameDude) {
		while (true) {
			display.printMessage("Select a category.");
			workingCategoryValue = display.waitForPlayerToSelectCategory();
			if (!usedCategories[thatSameDude][workingCategoryValue]) break;			
		}
		usedCategories[thatSameDude][workingCategoryValue] = true;
		checkValidityOfCategorySelection(thatSameDude);
		
	}
	
	/* Method: checkValidityOfCategorySelection(int forWhomWeCalculate)
	 * 
	 * Tests whether a selection is a valid category match or not.
	 */
	private void checkValidityOfCategorySelection(int forWhomWeCalculate) {
		if(YahtzeeMagicStub.checkCategory(workingDiceValues, workingCategoryValue) == true) {
			assignValidCategoryPoints(forWhomWeCalculate);
		}
		else {
			assignInvalidCategoryPoints(forWhomWeCalculate);
		}
	}
	
	/* Method:  assignValidCategoryPoints(int theDude)
	 * 
	 * Takes in a player number and assigns the points to
	 * mainScoreCard via figureOutThePoints and figureOutSubScores.
	 */
	private void assignValidCategoryPoints(int theDude) {
		figureOutThePoints(theDude, workingCategoryValue);
		int score = mainScoreCard[theDude][workingCategoryValue];
		display.updateScorecard(workingCategoryValue, theDude, score);
		figureOutSubScores(theDude);
		int theTotalScore = mainScoreCard[theDude][TOTAL];
		display.updateScorecard(TOTAL, theDude, theTotalScore);
		int theTopScore = mainScoreCard[theDude][UPPER_SCORE];
		display.updateScorecard(UPPER_SCORE, theDude, theTopScore);
		int theBottomScore = mainScoreCard[theDude][LOWER_SCORE];
		display.updateScorecard(LOWER_SCORE, theDude, theBottomScore);
	}
	
	/* Method:  assignInvalidCategoryPoints(int theDude)
	 * 
	 * Takes in a player number and assigns zero points
	 * for when category is invalid.
	 */
	private void assignInvalidCategoryPoints(int theDude) {
		mainScoreCard[theDude][workingCategoryValue] = 0;
		display.updateScorecard(workingCategoryValue, theDude, 0);
		figureOutSubScores(theDude);
		int theScore = mainScoreCard[theDude][TOTAL];
		display.updateScorecard(TOTAL, theDude, theScore);
	}
	
	/* Method: figureOutThePoints(int playerNumber, int category)
	 * 
	 * Uses a switch and some helper methods to calculate the points.
	 */
	
	private void figureOutThePoints(int playerNumber, int category) {
		int points = 0; 
		switch (category) {
			case ONES:
			points = singleValueEquation(category);
			break;
			case TWOS:
			points = singleValueEquation(category);
			break;
			case THREES:
			points = singleValueEquation(category);
			break;
			case FOURS:
			points = singleValueEquation(category);
			break;
			case FIVES:
			points = singleValueEquation(category);
			break;
			case SIXES:
			points = singleValueEquation(category);
			break;
			case THREE_OF_A_KIND:
			points = ofAKindValueEquation(category);
			break;
			case FOUR_OF_A_KIND:
			points = ofAKindValueEquation(category);
			break;
			case CHANCE:
			points = ofAKindValueEquation(category);
			break;
			case FULL_HOUSE:
			points = 25;
			break;
			case SMALL_STRAIGHT:
			points = 30;
			break;
			case LARGE_STRAIGHT:
			points = 40;
			break;
			case YAHTZEE:
			points = 50;
			break;
			default:
			break;}
			mainScoreCard[playerNumber][category] = points;
		}
		
    /* Method: singleValueEquation(int category)
     * 
     * Calculates the score for categories ONES through SIXES.
     */
    private int singleValueEquation(int passedCategory) {
    	int points = 0;
    	for(int i = 0; i < N_DICE; i++) {
    		if(workingDiceValues[i] == passedCategory) {
    			points += passedCategory;
    		}
    	}
    	return points;
    }
    
    /* Method: ofAKindValueEquation(int passedCategory)
     * 
     * Calculates the score for categories THREE_OF_A_KIND,.
     * FOUR_OF_A_KIND and CHANCE.
     */
    private int ofAKindValueEquation(int passedCategory) {
    	int points = 0;
    	for(int i = 0; i<N_DICE; i++) {
    		points += workingDiceValues[i];
    	}
    	return points;
    }
    
    /* Method:  figureOutSubScores(int nPlayer)
     * 
     * Takes in a player number, first "for loop" cycles through upper score group,
     * bottom "for loop" cycles through lower score group.  Then, the mainScoreCard 
     * is updated.
     */
    private void figureOutSubScores(int nPlayer) {
    	int singlesSubScore = 0;
    	for(int category = 1; category <= 6; category++) {
    		singlesSubScore += mainScoreCard[nPlayer][category];
    	}
    	int bottomSubScore = 0;
    	for(int category = 9; category <= 15; category++) {
    		bottomSubScore += mainScoreCard[nPlayer][category];
    	}
    	int totalScore = singlesSubScore + bottomSubScore; 
    	mainScoreCard[nPlayer][UPPER_SCORE] = singlesSubScore; 
    	mainScoreCard[nPlayer][LOWER_SCORE] = bottomSubScore;
    	mainScoreCard[nPlayer][TOTAL] = totalScore; 
    }
    
    /* Method: assignBonusPoints()
     * 
     * At the end of all the rounds, this calculates the upper bonus
     * and then updates the total score...
     */
    private void assignBonusPoints() {
    	for(int player = 1; player <= nPlayers; player++) {
    		if(mainScoreCard[player][UPPER_SCORE] >= 63) {
    			mainScoreCard[player][UPPER_BONUS] = 35;
    		}
    		display.updateScorecard(UPPER_BONUS, player, mainScoreCard[player][UPPER_BONUS]);
    		mainScoreCard[player][TOTAL] = mainScoreCard[player][TOTAL] + mainScoreCard[player][UPPER_BONUS];
    		display.updateScorecard(TOTAL, player, mainScoreCard[player][TOTAL]);
    	}
    }
    
	/* Method: calculateWhoWonTheGame()
	 * 
	 * This method is run at the end of the game, and compares
	 * both total scores to see who won.
	 */
	private void calculateWhoWonTheGame() {
		String topDog = "";
		String otherWinners = " and ";
		int topScore = 0;
		for (int i = 0; i < nPlayers; i++) {	
			if (mainScoreCard[i][TOTAL] == topScore) {
				topDog += otherWinners + playerNames[i];
			} else if (mainScoreCard[i][TOTAL] > topScore) {
				topDog = playerNames[i];
				topScore = mainScoreCard[i][TOTAL];				
			} 
		}
		display.printMessage("Congratulations, " + topDog + ", you won with a score of " + topScore + ".");
	}

	/* Private variables */
	//Number of players:
	private int nPlayers;
	//Array with nPlayers entries of names:
	private String[] playerNames;
	//Stores the latest dice values in an array:
	private int[] workingDiceValues = new int [N_DICE];
   	 //Stores the latest category:
	private int workingCategoryValue;
	//The main score-card 2D array [player][category]:
	private int[][] mainScoreCard;
    	//Stores the used categories:
	private boolean[][] usedCategories;
    	//Used to build the display:
	private YahtzeeDisplay display;
	//Used for dice rolls:
	private RandomGenerator rgen = new RandomGenerator();
	

}
