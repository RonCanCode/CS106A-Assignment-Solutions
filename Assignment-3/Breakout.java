/*
 * File: Breakout.java
 * -------------------
 * Name: Ron Guglielmone 
 * 
 * This is a slightly buggy version of BreakOut.
 * Along with the supplied assignment classes, 
 * this code implements a functional version of
 * the classic arcade game "Breakout".
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels.  On some platforms 
  * these may NOT actually be the dimensions of the graphics canvas. */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board.  On some platforms these may NOT actually
  * be the dimensions of the graphics canvas. */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Number of bricks */
	private static final int BRICKS_TOTAL = ( NBRICK_ROWS * NBRICKS_PER_ROW );
	
/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 20;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/** Refresh rate for frames */
	private static final int DELAY = 15;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		addMouseListeners();
		setUpTheGame();
		if (turnsLeft > 0 && bricksLeft > 0) {
		playTheGame();	
		}
		else {
			ball.setVisible(false);
		}
	}
	
	//OBJECTS:
	
	//This creates the paddle object.
	private GRect paddle;
	
	//This creates the ball object
	private GOval ball;
	

	private void setUpTheGame() {
		buildTheBricks();
		buildThePaddle();
	}
	
	
	/*Method:  buildTheBricks()
	 * 
	 * This is a double-nested loop.
	 * The outer loop counts rows against NBRICK_ROWS.
	 * The inner loop counts columns against NBRICKS_PER_ROW.
	 * The method builds all brick objects needed for gameplay.
	 */
	private void buildTheBricks() {
		for (int row = 0; row < NBRICK_ROWS; row++) {
			for (int column = 0; column < NBRICKS_PER_ROW; column++) {
				
				//This is the x-coordinate of each brick per loop.
				double xcordinate = ( (getWidth() / 2) - ((NBRICKS_PER_ROW * BRICK_WIDTH) / 2) 
						            - (((NBRICKS_PER_ROW - 1) * BRICK_SEP) / 2) + (column * BRICK_WIDTH) 
						            + (column * BRICK_SEP));
				
				//This is the y-coordinate of each brick per loop.
				double ycordinate = ( (BRICK_Y_OFFSET + (row * BRICK_HEIGHT)) + (row * BRICK_SEP));
				
				//This creates one new brick.
				GRect brick = new GRect (xcordinate, ycordinate, BRICK_WIDTH, BRICK_HEIGHT);
				add (brick);
				brick.setFilled(true);
				
				//This sets the colors of each row.
				switch(row) {
				case 0: brick.setColor(Color.RED); break;
				case 1: brick.setColor(Color.RED); break;
				case 2: brick.setColor(Color.RED); break;
				case 3: brick.setColor(Color.RED); break;
				case 4: brick.setColor(Color.YELLOW); break;
				case 5: brick.setColor(Color.YELLOW); break;
				case 6: brick.setColor(Color.YELLOW); break;
				case 7: brick.setColor(Color.YELLOW); break;
				case 8: brick.setColor(Color.YELLOW); break;
				case 9: brick.setColor(Color.RED); break;
				default: brick.setColor(Color.gray); break;
				}
				
			}
		}
	}
	
	
	/*Method: buildThePaddle()
	 * 
	 *This places the paddle object onto the canvas and fills it.
	 */
	private void buildThePaddle() {
		double x = ( (getWidth() / 2) - (PADDLE_WIDTH / 2) );
		double y = ( getHeight() - (PADDLE_Y_OFFSET + PADDLE_HEIGHT) );
		paddle = new GRect (x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setColor(Color.BLACK);
		add (paddle);
	}
	
	
	/*Method: mouseMoved()
	 * 
	 * Tracks the mouse position, and sets a new location for the paddle.
	 */
	public void mouseMoved(MouseEvent e) {
		double mousePosition = e.getX();
        //This is the case for when the mouse moves off screen to the left.
		if (mousePosition < ( PADDLE_WIDTH / 2 ) + 2) {
            paddle.setLocation(1, ( getHeight() - (PADDLE_Y_OFFSET + PADDLE_HEIGHT ) ) ); 
        } 
		//This is the case for then the mouse moves off screen to the right.
		else if (mousePosition > ( APPLICATION_WIDTH - PADDLE_WIDTH )) {
            paddle.setLocation( ( APPLICATION_WIDTH - PADDLE_WIDTH ) , ( getHeight() - ( PADDLE_Y_OFFSET + PADDLE_HEIGHT ) ) );
        } 
		//This is the case for normal mouse movement.
		else if ((mousePosition < getWidth() - PADDLE_WIDTH/2) && (mousePosition > PADDLE_WIDTH/2)) {
    			paddle.setLocation(mousePosition - PADDLE_WIDTH/2, 
    			getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
        }
	}
	
	
	/*Method:  buildTheBall()
	 * 
	 * Places a GOval of (BALL_RADIUS x 2) width and height at the center of the window.
	 */
	private void buildTheBall() {
		double xPosition = ( ( getWidth() / 2 ) - BALL_RADIUS );
		double yPosition = ( ( getHeight() / 2 ) - BALL_RADIUS );
		ball = new GOval (xPosition, yPosition, BALL_RADIUS*2, BALL_RADIUS*2);
		ball.setFilled(true);
		ball.setColor(Color.BLACK);
		add (ball);
	}
	
	
	/*Method:  ballVelocity()
	 * 
	 *Sets y-velocity to a constant, and randomly generates an x-velocity for game start.
	 */
	private void ballStartVelocity() {
		vy = 4.0;
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) vx = -vx;
	}
	
	
	/*Method: playTheGame()
	 * 
	 * This calls most of the functional methods.
	 */
	private void playTheGame() {
		while (true){
			buildTheBall();
			waitForClick();
			ballStartVelocity();
			bouncingBall();
		}
	}
	
	
	/*Method:  bouncingBall()
	 * 
	 *This method dictates how the ball bounces between the bounds of the game window.
	 *When the ball's VX or VY would move past an edge, the direction is inverted.
	 */
	private void bouncingBall() {
		AudioClip sound = MediaTools.loadAudioClip("bounce.au");
		while (ball.getY() <= getHeight()) {
			ball.move(vx, vy);
			//The case for bouncing off left and right walls.
			if ( ( ball.getX() <= 0 && vx < 0 ) || 
				( ball.getX()  >= ( getWidth() - BALL_RADIUS*2 ) && vx > 0)) {
				vx = -vx;
				sound.play();
			}
			//The case for bouncing off top wall.
			if (ball.getY() - vy <= 0 && vy < 0 ) {
				vy = -vy;
				sound.play();
			}
			//The case when ball runs into something.
			GObject collided = checkCollision();
			if (collided == paddle) {
				vy = -vy;
				sound.play();
			}
			else if (collided != null) {
				remove(collided); 
				vy = -vy;
				sound.play();
				bricksLeft--;
			}
			//Ball out of bounds.
			if ( ( ball.getY() - BALL_RADIUS * 2 )  >= getHeight()) {
					turnsLeft--;
					break;
			}
			//Pause causes the game to move at a playable pace.
			pause (DELAY);
		}
	}
	
	
	/*Method:  checkCollision()
	 * 
	 * This determines whether the ball has come into contact with anything.
	 * If so, it creates a new GObject "collided" and returns that object.
	 */
    private GObject checkCollision() {
        GObject collided = getElementAt( ball.getX(), ball.getY() );
        if (collided == null) {
        	collided = getElementAt( ball.getX() + BALL_RADIUS*2, ball.getY() );
        }
        if (collided == null) {
        	collided = getElementAt( ball.getX(), ball.getY() + BALL_RADIUS*2 );
        }
        if (collided == null) {
        	collided = getElementAt( ball.getX() + BALL_RADIUS*2, ball.getY() + BALL_RADIUS*2);
        }
        return collided;
    }
   
    
    //GLOBAL VARIABLES:
    
    /** Turns Left */
	private int turnsLeft = NTURNS;
	
    /** Number of bricks left */
	private int bricksLeft = BRICKS_TOTAL;
 
	/** Random Number Generator for x-velocity. */
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	/** Velocity variables. */
	private double vx, vy;
	
}
