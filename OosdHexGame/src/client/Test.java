/*******************************************************************************
 * Laurence Ashdown
 * OOSD Assignment
 * RMIT Semester 1 2018
 ******************************************************************************/
package client;

import model.GameEngineImpl;
import model.interfaces.GameEngine;

public class Test {
	public static void main(String args[]) {
		GameEngine ge = new GameEngineImpl();
		ge.startGame(9);
	}
	
}
