/*******************************************************************************
 * Laurence Ashdown
 * OOSD Assignment
 * RMIT Semester 1 2018
 ******************************************************************************/
package client;

import model.GameEngineImpl;
import model.interfaces.GameEngine;

/**
 * This is the sample client file. Initializing the game engine and passing it a game
 * size. Future iterations will allow for modifying board size and amount of pieces, via
 * user input.
 * 
 * @author laurence
 *
 */
public class BoH {
	public static void main(String args[]) {
		GameEngine ge = new GameEngineImpl();
		ge.startGame(9);
	}
	
}
