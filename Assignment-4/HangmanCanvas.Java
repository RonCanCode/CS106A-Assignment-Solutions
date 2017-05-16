/*
 * File: HangmanCanvas.java
 * ------------------------
 * 
 * Name:  Ron Guglielmone
 * 
 * This file creates a display to accompany Hangman.java.
 * Values for Public noteIncorrectGuess and Public displayWord
 * are sent from the main Hangman.java class.

 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	
	private GLabel guessedCharacters;
	private GLabel theHiddenWord;
	private int guessCounter;

/*Method:  reset
 * 
 * This resets the game
 */
	public void reset() {
		removeAll();
		drawTheScaffold();
		drawTheRope();
		buildTheLabels();
		guessCounter = 0;
	}
	
	//Draws the scaffold when reset() is called.
	private void drawTheScaffold() {
		double scaffoldCenterX = getWidth()/2;
		double scaffoldLeftX = getWidth()/2 - BEAM_LENGTH;
		double scaffoldTopY = getHeight()/16;
		double scaffoldBottomY = getHeight()/16 + SCAFFOLD_HEIGHT;
		GLine scaffoldPole = new GLine (scaffoldLeftX, scaffoldTopY, scaffoldLeftX, scaffoldBottomY);
		GLine scaffoldBeam = new GLine (scaffoldLeftX, scaffoldTopY, scaffoldCenterX, scaffoldTopY);
		add(scaffoldPole);
		add(scaffoldBeam);
	}
	
	//Draws the rope when reset() is called.
	private void drawTheRope() {
		double scaffoldCenterX = getWidth()/2;
		double scaffoldTopY = getHeight()/16;
		double ropeBottomY = getHeight()/16 + ROPE_LENGTH;
		GLine rope = new GLine (scaffoldCenterX, scaffoldTopY, scaffoldCenterX, ropeBottomY);
		add(rope);
	}
	
	//Resets the guess labels.
	private void buildTheLabels() {
		theHiddenWord = new GLabel("");
		double xPosition = getWidth()/2 - BEAM_LENGTH;
		double yPosition = getHeight() - (getHeight()/16);
		theHiddenWord.setLocation(xPosition, yPosition);
		theHiddenWord.setFont("Helvetica-28");
		add(theHiddenWord);
		
		guessedCharacters = new GLabel("");
		guessedCharacters.setLocation(xPosition, yPosition + theHiddenWord.getHeight());
		add(guessedCharacters);
	}
	
/*Method: displayWord
 * 
 * Sets a label with theHiddenWord.
 */
	public void displayWord(String word) {
		theHiddenWord.setLabel(word);	
	}

/*Method: noteIncorrectGuess
 * 
 * This switch calls the draw method for the appropriate
 * body part (corresponding to # of guessCounter).
 */
	public void noteIncorrectGuess(char letter) {
		guessCounter++;
		switch (guessCounter) {
		case 1:
			head();
			break;
		case 2:
			body();
			break;
		case 3:
			leftArm();
			break;
		case 4:
			rightArm();
			break;
		case 5:
			leftLeg();
			break;
		case 6:
			rightLeg();
			break;
		case 7:
			leftFoot();
			break;
		case 8:
			rightFoot();
			break;
		default:
			break;
		}
		updateGuessedCharacters(letter);
	}
	
	
	/*Method:  updateGuessedCharacters
	 * 
	 * Re-writes the string guessedCharacters.
	 * See:  references in Hangman.java
	 */
	private void updateGuessedCharacters(char character) {
		character = Character.toUpperCase(character);
		String updated = "";
		String toBeReplaced = guessedCharacters.getLabel();
		int length = toBeReplaced.length();
		boolean letterAdded = false;
		for (int i = 0; i < length; i++) {
			char ch = toBeReplaced.charAt(i);
			if (ch < character) {
				updated += ch;
			} else {
				if (ch > character) {
					updated += character;
				}
				updated += toBeReplaced.substring(i);
				letterAdded = true;
				break;
			}
		}
		if (!letterAdded) {
			updated += character;
		}		
		guessedCharacters.setLabel(updated);
	}

	/*Methods: head, body, leftArm, rightArm, leftLeg, rightLeg, leftFoot, rightFoot
	 * 
	 * The following methods draw each of the body parts separately.
	 */
	private void head() {
		double headX = getWidth()/2 - HEAD_RADIUS;
		double headY = getHeight()/16 + ROPE_LENGTH;
		GOval head = new GOval (headX, headY, HEAD_RADIUS * 2, HEAD_RADIUS * 2);
		add(head);
	}
	
	private void body() {
		double bodyX = getWidth()/2;
		double bodyTopY = getHeight()/16 + ROPE_LENGTH + HEAD_RADIUS * 2;
		double bodyBottomY = getHeight()/16 + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH;
		GLine body = new GLine (bodyX, bodyTopY, bodyX, bodyBottomY);
		add(body);
	}
	
	private void leftArm() {
		double upperArmLeftX = getWidth()/2 - UPPER_ARM_LENGTH;
		double upperArmRightX = getWidth()/2;
		double upperArmY = getHeight()/16 + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH/4;
		GLine upperArm = new GLine (upperArmLeftX, upperArmY, upperArmRightX, upperArmY);
		add(upperArm);
		double lowerArmBottomY = getHeight()/16 + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH/4 + LOWER_ARM_LENGTH;
		GLine lowerArm = new GLine (upperArmLeftX, upperArmY, upperArmLeftX, lowerArmBottomY);
		add(lowerArm);
	}
	
	private void rightArm() {
		double upperArmLeftX = getWidth()/2;
		double upperArmRightX = getWidth()/2 + UPPER_ARM_LENGTH;
		double upperArmY = getHeight()/16 + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH/4;
		GLine upperArm = new GLine (upperArmLeftX, upperArmY, upperArmRightX, upperArmY);
		add(upperArm);
		double lowerArmBottomY = getHeight()/16 + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH/4 + LOWER_ARM_LENGTH;
		GLine lowerArm = new GLine (upperArmRightX, upperArmY, upperArmRightX, lowerArmBottomY);
		add(lowerArm);
	}
	
	private void leftLeg() {
		double leftX = getWidth()/2 - HIP_WIDTH;
		double rightX = getWidth()/2;
		double topY = getHeight()/16 + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH;
		double bottomY = getHeight()/16 + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH;
		GLine leftUpper = new GLine(leftX, topY, rightX, topY);
		add(leftUpper);
		GLine leftLower = new GLine(leftX, topY, leftX, bottomY);
		add(leftLower);
		
	}
	
	private void rightLeg() {
		double leftX = getWidth()/2;
		double rightX = getWidth()/2 + HIP_WIDTH;
		double topY = getHeight()/16 + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH;
		double bottomY = getHeight()/16 + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH;
		GLine rightUpper = new GLine(leftX, topY, rightX, topY);
		add(rightUpper);
		GLine rightLower = new GLine(rightX, topY, rightX, bottomY);
		add(rightLower);
	}
	
	private void leftFoot() {
		double leftX = getWidth()/2 - HIP_WIDTH - FOOT_LENGTH;
		double rightX = getWidth()/2 - HIP_WIDTH;
		double footY = getHeight()/16 + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH;
		GLine leftFoot = new GLine(leftX, footY, rightX, footY);
		add(leftFoot);
	}
	
	private void rightFoot() {
		double leftX = getWidth()/2 + HIP_WIDTH;
		double rightX = getWidth()/2  + HIP_WIDTH + FOOT_LENGTH;
		double footY = getHeight()/16 + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH;
		GLine rightFoot = new GLine(leftX, footY, rightX, footY);
		add(rightFoot);
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
